package vn.root.data.local

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import vn.root.domain.model.ResultModel
import vn.root.domain.model.TypeException

abstract class LocalBoundResource<RequestType, ResultType>(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {
	
	fun build() = flow {
		emit(ResultModel.Loading)
		emit(
			fetchFromDatabase() ?: ResultModel.AppException(
				type = TypeException.Local, message = "LocalBoundResource somethings wrong"
			)
		)
		delay(200) // Small delay to ensure all of the value emitted by the flow is consumed
		emit(ResultModel.Done)
	}.flowOn(dispatcher)
	
	
	private suspend fun fetchFromDatabase(): ResultModel<ResultType>? {
		return try {
			val response = onDatabase()
			Timber.d("fetchFromDatabase ${if (response != null) "Success" else "Failure"}")
			ResultModel.Success(data = processResponse(response))
		} catch (e: Exception) {
			Timber.e("fetchFromDatabase Error: ${e.message}")
			ResultModel.AppException(
				type = TypeException.Local, message = e.message
			)
		}
	}
	
	abstract suspend fun onDatabase(): RequestType
	
	abstract suspend fun processResponse(request: RequestType?): ResultType?
}
