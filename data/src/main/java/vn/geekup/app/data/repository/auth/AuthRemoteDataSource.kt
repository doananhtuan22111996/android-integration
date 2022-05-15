package vn.geekup.app.data.repository.auth

import kotlinx.coroutines.flow.*
import retrofit2.Response
import vn.geekup.app.data.model.general.ObjectResponse
import vn.geekup.app.data.model.user.OTableVO
import vn.geekup.app.data.remote.auth.AuthApiService
import vn.geekup.app.data.di.remote.NetworkBoundService
import vn.geekup.app.domain.dto.OTableRequestBody
import vn.geekup.app.domain.model.general.ResultModel
import vn.geekup.app.domain.model.user.OTableModel
import vn.geekup.app.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val authApiService: AuthApiService) :
    AuthRepository {

    override fun logout(): Flow<ResultModel<Boolean>> =
        object : NetworkBoundService<Nothing, Boolean>() {
            override suspend fun onApi(): Response<ObjectResponse<Nothing>> =
                authApiService.logout()

            override suspend fun processResponse(request: ObjectResponse<Nothing>?): ResultModel.Success<Boolean>? =
                ResultModel.Success(data = true)
        }.build()

    override suspend fun saveToken(token: String): Flow<ResultModel<Unit>> = emptyFlow()

    override suspend fun loginOTable(otableBody: OTableRequestBody): Flow<ResultModel<OTableModel>> =
        object : NetworkBoundService<OTableVO, OTableModel>() {
            override suspend fun onApi(): Response<ObjectResponse<OTableVO>> =
                authApiService.loginOTable(otableBody.token, otableBody.currentAuthority)


            override suspend fun processResponse(request: ObjectResponse<OTableVO>?): ResultModel.Success<OTableModel>? {
                return ResultModel.Success(data = request?.data?.vo2Model() ?: OTableModel())
            }
        }.build()
}