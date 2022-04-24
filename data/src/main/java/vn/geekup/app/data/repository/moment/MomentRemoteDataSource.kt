package vn.geekup.app.data.repository.moment

import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber
import vn.geekup.app.data.Config
import vn.geekup.app.data.services.MiddleWareService
import vn.geekup.app.domain.dto.*
import vn.geekup.app.domain.model.general.BaseModelListResponse
import vn.geekup.app.domain.model.general.ResultModel
import vn.geekup.app.domain.model.moment.MomentModel
import vn.geekup.app.domain.repository.MomentRepository
import javax.inject.Inject

class MomentRemoteDataSource @Inject constructor(
    private val middleWareService: MiddleWareService
) : MomentRepository {

    override fun getMomentFeeds(momentFeedRequestBody: MomentFeedRequestBody): Single<BaseModelListResponse<MomentModel>> {
        val requestFunc = middleWareService.aliaApiService.getMomentFeeds(
            cursor = momentFeedRequestBody.cursor,
            limit = momentFeedRequestBody.limit,
            sort = when (momentFeedRequestBody.sort) {
                MomentSort.DESC() -> MomentSort.DESC().sortName
                else -> MomentSort.ASC().sortName
            }, dates = momentFeedRequestBody.dates
        )
        return middleWareService.requestSingleApi(requestFunc = requestFunc) {
            val results: BaseModelListResponse<MomentModel> =
                BaseModelListResponse(limit = it.data?.limit, nextCursor = it.data?.nextCursor)
            val items: ArrayList<MomentModel> = arrayListOf()
            it.data?.items?.forEach { item ->
                items.add(item.vo2Model())
            }
            results.items = items
            Single.just(results)
        } as Single<BaseModelListResponse<MomentModel>>
    }

    override suspend fun getFlowMomentFeeds(momentFeedRequestBody: MomentFeedRequestBody): Flow<ResultModel<ArrayList<MomentModel>>> =
        flow {
            val response = middleWareService.aliaApiService.getFlowMomentFeeds(
                cursor = momentFeedRequestBody.cursor,
                limit = momentFeedRequestBody.limit,
                sort = when (momentFeedRequestBody.sort) {
                    MomentSort.DESC() -> MomentSort.DESC().sortName
                    else -> MomentSort.ASC().sortName
                }, dates = momentFeedRequestBody.dates
            )
            if (response.meta?.statusCode != Config.ErrorCode.CODE_200) {
                emit(
                    ResultModel.ServerErrorException(
                        response.meta?.statusCode,
                        response.meta?.message
                    )
                )
            } else {
                val items: ArrayList<MomentModel> = arrayListOf()
                response.data?.items?.forEach { item ->
                    items.add(item.vo2Model())
                }
                val results: ResultModel.Success<ArrayList<MomentModel>> =
                    ResultModel.Success(
                        limit = response.data?.limit,
                        nextCursor = response.data?.nextCursor,
                        data = items
                    )
                emit(results)
            }

        }.flowOn(Dispatchers.IO)

    override suspend fun getFlowMomentDetail(id: Int): Flow<ResultModel<MomentModel>> =
        flow {
            val response = middleWareService.aliaApiService.getFlowMomentDetail(momentId = id)
            if (response.meta?.statusCode != Config.ErrorCode.CODE_200) {
                emit(
                    ResultModel.ServerErrorException(
                        response.meta?.statusCode,
                        response.meta?.message
                    )
                )
            } else {
                emit(ResultModel.Success(data = response.data?.vo2Model()))
            }
        }.flowOn(Dispatchers.IO)
}
