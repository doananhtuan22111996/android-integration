package vn.root.data.repository

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import vn.root.data.Config
import vn.root.data.local.PreferenceWrapper
import vn.root.data.model.ObjectResponse
import vn.root.data.model.TokenRaw
import vn.root.data.network.NetworkBoundService
import vn.root.data.service.ApiService
import vn.root.domain.model.ResultModel
import vn.root.domain.model.TokenModel
import vn.root.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val apiService: ApiService, private val preferenceWrapper: PreferenceWrapper
) : AuthRepository {
    override suspend fun login(): Flow<ResultModel<TokenModel>> =
        object : NetworkBoundService<TokenRaw, TokenModel>() {
            override suspend fun onApi(): Response<ObjectResponse<TokenRaw>> = apiService.login()

            override suspend fun processResponse(request: ObjectResponse<TokenRaw>?): ResultModel.Success<TokenModel> {
                if (request?.data != null) {
                    preferenceWrapper.saveString(
                        Config.SharePreference.KEY_AUTH_TOKEN, request.data.token ?: ""
                    )
                    preferenceWrapper.saveString(
                        Config.SharePreference.KEY_AUTH_REFRESH_TOKEN,
                        request.data.refreshToken ?: ""
                    )
                }
                return ResultModel.Success(data = request?.data?.raw2Model() as? TokenModel)
            }
        }.build()

    override suspend fun logout(): Flow<ResultModel<Nothing>> =
        object : NetworkBoundService<Nothing, Nothing>() {
            override suspend fun onApi(): Response<ObjectResponse<Nothing>> = apiService.logout()

            override suspend fun processResponse(request: ObjectResponse<Nothing>?): ResultModel.Success<Nothing> =
                ResultModel.Success()
        }.build()

//    override suspend fun saveToken(
//        token: String, refreshToken: String
//    ): Flow<ResultModel<Nothing>> {
//        preferenceWrapper.saveString(Config.SharePreference.KEY_AUTH_TOKEN, token)
//        preferenceWrapper.saveString(Config.SharePreference.KEY_AUTH_REFRESH_TOKEN, refreshToken)
//        return emptyFlow()
//    }
}
