package vn.root.domain.usecase

import kotlinx.coroutines.flow.Flow
import vn.root.domain.model.ResultModel
import vn.root.domain.model.TokenModel
import vn.root.domain.repository.AuthRepository

interface LoginUseCase {

    suspend fun login(): Flow<ResultModel<TokenModel>>
}

class LoginUseCaseImpl(private var repository: AuthRepository) : LoginUseCase {
    override suspend fun login(): Flow<ResultModel<TokenModel>> = repository.login()
}
