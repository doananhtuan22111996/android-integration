package vn.root.data.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import vn.root.domain.model.ResultModel
import vn.root.domain.model.TypeException

abstract class LocalBoundResource<RequestType, ResultType> {
	
	fun build() = flow {
		emit(ResultModel.Loading)
		emit(
			fetchFromDatabase() ?: ResultModel.AppException(
				type = TypeException.Local, message = "LocalBoundResource somethings wrong"
			)
		)
		emit(ResultModel.Done)
	}.flowOn(Dispatchers.IO)
	
	
	private suspend fun fetchFromDatabase(): ResultModel<ResultType>? {
		return try {
			val response = onDatabase()
			Timber.d("Data fetched from Database ${if (response != null) "Success" else "Failure"}")
			ResultModel.Success(data = processResponse(response))
		} catch (e: Exception) {
			Timber.e("Data fetched from Database Error: ${e.message}")
			ResultModel.AppException(
				type = TypeException.Local, message = e.message
			)
		}
	}
	
	abstract suspend fun onDatabase(): RequestType
	
	abstract suspend fun processResponse(request: RequestType?): ResultType?
}
