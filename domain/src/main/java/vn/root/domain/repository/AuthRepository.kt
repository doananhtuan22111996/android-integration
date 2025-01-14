package vn.root.domain.repository

import kotlinx.coroutines.flow.Flow
import vn.core.domain.ResultModel
import vn.root.domain.model.TokenModel

interface AuthRepository {

    fun login(): Flow<ResultModel<TokenModel>>

    fun logout(): Flow<ResultModel<Nothing>>
}
