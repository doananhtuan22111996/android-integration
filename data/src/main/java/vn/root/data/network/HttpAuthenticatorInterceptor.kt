package vn.root.data.network

import com.google.gson.Gson
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.Route
import okio.use
import timber.log.Timber
import vn.root.data.Config
import vn.root.data.local.PreferenceWrapper
import vn.root.data.model.ObjectResponse
import vn.root.data.model.TokenRaw
import java.net.HttpURLConnection
import javax.inject.Inject

class TokenAuthenticator(
	@Inject private val preferenceWrapper: PreferenceWrapper
) : Authenticator {
	
	override fun authenticate(route: Route?, response: Response): Request? {
		Timber.d("Request URL: ${response.request.url}")
		val requestUrl = response.request.url.toString()
		val ignored = requestUrl.contains("login") || requestUrl.contains("refreshToken")
		if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED && !ignored) {
			try {
				val refreshToken =
					preferenceWrapper.getString(Config.SharePreference.KEY_AUTH_REFRESH_TOKEN, "")
				refreshToken(refreshToken)
				val newToken =
					preferenceWrapper.getString(Config.SharePreference.KEY_AUTH_TOKEN, "")
				val builder = response.request.newBuilder()
				if (newToken.isNotEmpty()) {
					// TODO change Author method depend by context
//        builder.addHeader("Authorization", "Bearer $newToken")
//					builder.addHeader("Authorization", basicAuth(newToken))
				}
				return builder.build()
			} catch (e: Exception) {
				Timber.e("Refresh Token Somethings Wrong: ${e.message}")
				return null
			}
		}
		return null
	}
	
	private fun refreshToken(refreshToken: String) {
		val request = Request.Builder().url("${Config.mainDomain}/refreshToken")
			.post(refreshToken.toRequestBody()).build()
		OkHttpClient().newBuilder().build().newCall(request).execute().use { response ->
			if (response.isSuccessful && response.code == HttpURLConnection.HTTP_OK) {
				val objResponse =
					Gson().fromJson(response.body.string(), ObjectResponse::class.java)
				val tokenRaw = Gson().fromJson(
					Gson().toJson(objResponse.data), TokenRaw::class.java
				) ?: null
				Timber.e("Refresh Token Success: ${tokenRaw?.refreshToken}")
				preferenceWrapper.saveString(
					Config.SharePreference.KEY_AUTH_TOKEN, tokenRaw?.token ?: ""
				)
				preferenceWrapper.saveString(
					Config.SharePreference.KEY_AUTH_REFRESH_TOKEN, tokenRaw?.refreshToken ?: ""
				)
			} else {
				Timber.e("Refresh Token Failure: ${response.message}")
			}
		}
	}
}