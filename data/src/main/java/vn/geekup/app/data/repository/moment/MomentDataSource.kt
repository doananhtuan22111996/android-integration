package vn.geekup.app.data.repository.moment

import kotlinx.coroutines.flow.Flow
import vn.geekup.app.data.di.qualifier.source.SourceLocal
import vn.geekup.app.data.di.qualifier.source.SourceRemote
import vn.geekup.app.domain.dto.MomentFeedRequestBody
import vn.geekup.app.domain.model.general.ResultModel
import vn.geekup.app.domain.model.moment.MomentModel
import vn.geekup.app.domain.repository.MomentRepository
import javax.inject.Inject

class MomentDataSource @Inject constructor(
    @SourceLocal
    private val local: MomentRepository,

    @SourceRemote
    private val remote: MomentRepository
) : MomentRepository {

    override suspend fun getFlowMomentFeeds(momentFeedRequestBody: MomentFeedRequestBody): Flow<ResultModel<ArrayList<MomentModel>>> {
        return remote.getFlowMomentFeeds(momentFeedRequestBody)
    }

    override suspend fun getFlowMomentDetail(id: Int): Flow<ResultModel<MomentModel>> {
        return remote.getFlowMomentDetail(id)
    }

}