package vn.root.app.data.repository

import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import vn.root.data.local.PagingByLocalDataSource
import vn.root.data.local.dao.ItemDao
import vn.root.data.model.ItemRaw
import vn.root.data.model.ListResponse
import vn.root.data.network.PagingByNetworkDataSource
import vn.root.data.service.ApiService
import vn.root.domain.model.ItemModel

class PagingRepositoryImplTest {

    @Mock
    lateinit var service: ApiService

    @Mock
    lateinit var itemDao: ItemDao

    private lateinit var dataSource: PagingByNetworkDataSource<ItemRaw, ItemModel>

    private lateinit var localDataSource: PagingByLocalDataSource<ItemRaw, ItemModel>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        dataSource = object : PagingByNetworkDataSource<ItemRaw, ItemModel>() {
            override suspend fun onApi(page: Int?): Response<ListResponse<ItemRaw>> {
                return service.getPaging(page = 1)
            }

            override suspend fun processResponse(request: ListResponse<ItemRaw>?): ListResponse<ItemModel> {
                itemDao.insertAll(request?.data ?: listOf())
                return ListResponse(data = request?.data?.map {
                    it.raw2Model() as ItemModel
                }, metadata = request?.metadata)
            }
        }
        localDataSource = object : PagingByLocalDataSource<ItemRaw, ItemModel>() {
            override suspend fun onDatabase(offset: Int?): List<ItemRaw> {
                return itemDao.getPagingItems(limit = 15, offset = 1)
            }

            override suspend fun processResponse(request: List<ItemRaw>?): List<ItemModel>? {
                return request?.map { it.raw2Model() as ItemModel }
            }
        }
    }

    @Test
    fun getPagingNetwork_onApi_return_correct_with_api_success() = runTest {
        val request = Response.success(ListResponse<ItemRaw>())
        Mockito.`when`(service.getPaging(page = 1)).thenReturn(request)
        val result = dataSource.onApi(page = 1)
        Mockito.verify(service).getPaging(page = 1)
        Assert.assertEquals(request, result)
    }

    @Test
    fun getPagingNetwork_onApi_return_correct_with_api_failure() = runTest {
        val mError =
            "{\n" + "  \"metadata\": {\n" + "    \"status\": false,\n" + "    \"message\": \"onApi_return_correct_with_api_failure\"\n" + "  }\n" + "}"
        val request = Response.error<ListResponse<ItemRaw>>(
            401, mError.toResponseBody("application/json".toMediaTypeOrNull())
        )
        Mockito.`when`(service.getPaging(page = 1)).thenReturn(request)
        val result = dataSource.onApi(page = 1)
        Mockito.verify(service).getPaging(page = 1)
        Assert.assertEquals(request, result)
    }

    @Test
    fun getPagingNetwork_processResponse_return_correct() = runTest {
        val request = ListResponse<ItemRaw>()
        val result: ListResponse<ItemModel> = dataSource.processResponse(request) ?: ListResponse()
        Mockito.verify(itemDao).insertAll(ArgumentMatchers.anyList())
        Assert.assertTrue(request.data!!.size == result.data!!.size)
    }

    @Test
    fun getPagingLocal_onDatabase_return_correct() = runTest {
        val request = listOf<ItemRaw>()
        Mockito.`when`(itemDao.getPagingItems(limit = 15, offset = 1)).thenReturn(request)
        val result = localDataSource.onDatabase(offset = 1)
        Mockito.verify(itemDao).getPagingItems(limit = 15, offset = 1)
        Assert.assertArrayEquals(request.toTypedArray(), result.toTypedArray())
    }

    @Test
    fun getPagingLocal_processResponse_return_correct() = runTest {
        val request = listOf<ItemRaw>()
        val result = localDataSource.processResponse(request)
        Assert.assertArrayEquals(request.toTypedArray(), result?.toTypedArray())
    }
}