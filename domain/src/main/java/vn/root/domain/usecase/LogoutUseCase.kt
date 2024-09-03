package vn.root.domain.usecase

import kotlinx.coroutines.flow.Flow
import vn.core.domain.ResultModel
import vn.core.usecase.BaseUseCase
import vn.root.domain.repository.AuthRepository
import javax.inject.Inject

interface LogoutUseCase {

    suspend fun logout(): Flow<ResultModel<Nothing>>
}

class LogoutComposeUseCase @Inject constructor(private var repository: AuthRepository) :
    BaseUseCase<Any, ResultModel<Nothing>>() {
    override fun execute(vararg params: Any?): Flow<ResultModel<Nothing>> {
        return repository.logout()
    }
}
