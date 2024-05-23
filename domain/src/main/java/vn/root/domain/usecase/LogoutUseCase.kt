package vn.root.domain.usecase

import kotlinx.coroutines.flow.Flow
import vn.root.domain.model.ResultModel
import vn.root.domain.repository.AuthRepository
import javax.inject.Inject

interface LogoutUseCase {
	
	suspend fun logout(): Flow<ResultModel<Nothing>>
}

@Deprecated("Migrating to Hilt")
class LogoutUseCaseImpl(private var repository: AuthRepository) : LogoutUseCase {
	
	override suspend fun logout(): Flow<ResultModel<Nothing>> = repository.logout()
}

class LogoutComposeUseCase @Inject constructor(private var repository: AuthRepository) :
	BaseUseCase<Any, ResultModel<Nothing>>() {
	override fun execute(vararg params: Any?): Flow<ResultModel<Nothing>> {
		return repository.logout()
	}
}
