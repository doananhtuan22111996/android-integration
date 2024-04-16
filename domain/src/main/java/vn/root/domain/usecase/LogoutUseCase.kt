package vn.root.domain.usecase

import kotlinx.coroutines.flow.Flow
import vn.root.domain.model.ResultModel
import vn.root.domain.repository.AuthRepository

interface LogoutUseCase {

    suspend fun logout(): Flow<ResultModel<Nothing>>
}

class LogoutUseCaseImpl(private var repository: AuthRepository) : LogoutUseCase {

    override suspend fun logout(): Flow<ResultModel<Nothing>> = repository.logout()
}
