package vn.root.domain.usecase

import kotlinx.coroutines.flow.Flow
import vn.root.domain.model.ResultModel
import vn.root.domain.model.TokenModel
import vn.root.domain.repository.AuthRepository
import javax.inject.Inject

interface LoginUseCase {
	
	suspend fun login(): Flow<ResultModel<TokenModel>>
}

@Deprecated("Migrating to Hilt")
class LoginUseCaseImpl(private var repository: AuthRepository) : LoginUseCase {
	override suspend fun login(): Flow<ResultModel<TokenModel>> = repository.login()
}

class LoginComposeUseCase @Inject constructor(private var repository: AuthRepository) :
	BaseUseCase<Any, ResultModel<TokenModel>>() {
	override fun execute(vararg params: Any?): Flow<ResultModel<TokenModel>> {
		return repository.login()
	}
}