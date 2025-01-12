package vn.root.data.repository

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import vn.core.data.di.AnoRetrofitApiService
import vn.core.data.local.PreferenceWrapper
import vn.core.data.model.ObjectResponse
import vn.core.data.network.NetworkBoundService
import vn.core.domain.ResultModel
import vn.root.data.Config
import vn.root.data.model.TokenRaw
import vn.root.data.service.ApiService
import vn.root.domain.model.TokenModel
import vn.root.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    @AnoRetrofitApiService private val apiService: ApiService,
    private val preferenceWrapper: PreferenceWrapper,
) : AuthRepository {
    override fun login(): Flow<ResultModel<TokenModel>> = object : NetworkBoundService<TokenRaw, TokenModel>() {
        override suspend fun onApi(): Response<ObjectResponse<TokenRaw>> = apiService.login()

        override suspend fun processResponse(request: ObjectResponse<TokenRaw>?): ResultModel.Success<TokenModel> {
            if (request?.data != null) {
                preferenceWrapper.saveString(
                    Config.SharePreference.KEY_AUTH_TOKEN,
                    request.data?.token ?: "",
                )
                preferenceWrapper.saveString(
                    Config.SharePreference.KEY_AUTH_REFRESH_TOKEN,
                    request.data?.refreshToken ?: "",
                )
            }
            return ResultModel.Success(data = request?.data?.raw2Model() as? TokenModel)
        }
    }.build()

    override fun logout(): Flow<ResultModel<Nothing>> = object : NetworkBoundService<Nothing, Nothing>() {
        override suspend fun onApi(): Response<ObjectResponse<Nothing>> = apiService.logout()

        override suspend fun processResponse(request: ObjectResponse<Nothing>?): ResultModel.Success<Nothing> = ResultModel.Success()
    }.build()
}
