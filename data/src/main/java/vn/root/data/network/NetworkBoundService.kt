package vn.root.data.network

import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import retrofit2.Response
import timber.log.Timber
import vn.root.data.model.ObjectResponse
import vn.root.domain.model.ResultModel
import vn.root.domain.model.TypeException
import java.net.HttpURLConnection.HTTP_BAD_GATEWAY

abstract class NetworkBoundService<RequestType, ResultType>(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    private val tag = NetworkBoundService::class.java.name

    /**
     * Follow Best Practice
     * https://github.com/android/architecture-components-samples/blob/main/GithubBrowserSample/app/src/main/java/com/android/example/github/repository/NetworkBoundResource.kt
     * */
    fun build() = flow {
        emit(fetchFromNetwork())
        delay(200) // Small delay to ensure all of the value emitted by the flow is consumed
    }.flowOn(dispatcher).onStart {
        emit(ResultModel.Loading)
    }.onCompletion {
        emit(ResultModel.Done)
    }

    /**
     * This Func handle response from Network [fetchFromNetwork].
     */
    private suspend fun fetchFromNetwork(): ResultModel<ResultType> {
        Timber.i(tag, "Fetch data from network")
        val apiResponse = onApi()
        val result = if (apiResponse.isSuccessful) {
            val body = apiResponse.body()
            processResponse(body)
        } else {
            try {
                val obj = Gson().fromJson(
                    apiResponse.errorBody()?.string(), ObjectResponse::class.java
                )
                ResultModel.AppException(
                    type = TypeException.Network(httpCode = apiResponse.code()),
                    message = obj.metadata?.message
                )
            } catch (e: Exception) {
                ResultModel.AppException(
                    type = TypeException.Network(httpCode = HTTP_BAD_GATEWAY),
                    message = "Network Somethings wrong! -- ${e.message}"
                )
            }
        }
        return result
    }

    abstract suspend fun onApi(): Response<ObjectResponse<RequestType>>

    abstract suspend fun processResponse(request: ObjectResponse<RequestType>?): ResultModel.Success<ResultType>
}