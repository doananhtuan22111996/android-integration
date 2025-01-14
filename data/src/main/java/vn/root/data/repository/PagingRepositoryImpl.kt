package vn.root.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import timber.log.Timber
import vn.core.data.di.AnoRetrofitApiService
import vn.core.data.local.PagingByLocalDataSource
import vn.core.data.model.ListResponse
import vn.core.data.network.PagingByNetworkDataSource
import vn.root.data.local.ItemDao
import vn.root.data.model.ItemRaw
import vn.root.data.service.ApiService
import vn.root.domain.model.ItemModel
import vn.root.domain.repository.PagingRepository
import javax.inject.Inject

class PagingRepositoryImpl @Inject constructor(
    @AnoRetrofitApiService private val apiService: ApiService,
    private val itemDao: ItemDao,
) : PagingRepository {

    override fun getPagingNetwork(lang: String): Flow<PagingData<ItemModel>> = Pager(
        config = PagingConfig(15),
    ) {
        object : PagingByNetworkDataSource<ItemRaw, ItemModel>() {
            override suspend fun onApi(page: Int?): Response<ListResponse<ItemRaw>> = apiService.getPaging(page = page ?: 1, lang = lang)

            override suspend fun processResponse(request: ListResponse<ItemRaw>?): ListResponse<ItemModel> {
                // Save Data to Room
                itemDao.insertAll(request?.data ?: listOf())
                Timber.d("processResponse: ${itemDao.getItems()}")
                return ListResponse(
                    data = request?.data?.map {
                        it.raw2Model() as ItemModel
                    },
                    metadata = request?.metadata,
                )
            }
        }
    }.flow

    override fun getPagingLocal(): Flow<PagingData<ItemModel>> = Pager(
        config = PagingConfig(15),
    ) {
        object : PagingByLocalDataSource<ItemRaw, ItemModel>() {
            override suspend fun onDatabase(offset: Int?): List<ItemRaw> = itemDao.getPagingItems(limit = 15, offset = offset)

            override suspend fun processResponse(request: List<ItemRaw>?): List<ItemModel>? = request?.map {
                it.raw2Model() as ItemModel
            }
        }
    }.flow
}
