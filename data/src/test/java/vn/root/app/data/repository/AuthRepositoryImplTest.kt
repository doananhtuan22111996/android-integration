package vn.root.app.data.repository

import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import retrofit2.Response
import vn.root.data.Config.SharePreference.KEY_AUTH_REFRESH_TOKEN
import vn.root.data.Config.SharePreference.KEY_AUTH_TOKEN
import vn.root.data.local.PreferenceWrapper
import vn.root.data.model.ObjectResponse
import vn.root.data.model.TokenRaw
import vn.root.data.repository.AuthRepositoryImpl
import vn.root.data.service.ApiService
import vn.root.domain.model.ResultModel
import vn.root.domain.model.TokenModel
import vn.root.domain.model.TypeException
import vn.root.domain.repository.AuthRepository

class AuthRepositoryImplTest {

    @Mock
    lateinit var service: ApiService

    @Mock
    lateinit var preferenceWrapper: PreferenceWrapper

    private lateinit var repository: AuthRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this) // Initialize mocks
        repository = AuthRepositoryImpl(service, preferenceWrapper)
    }

    @Test
    fun testLogin_Return_ResultModel_Success() = runBlocking {
        val mTokenRaw = TokenRaw(token = "This is token", refreshToken = "This is refreshToken")
        Mockito.`when`(service.login()).thenReturn(
            Response.success(ObjectResponse(data = mTokenRaw))
        )
        val values = mutableListOf<ResultModel<TokenModel>>()
        repository.login().collect {
            values.add(it)
        }
        verify(preferenceWrapper).saveString(KEY_AUTH_TOKEN, mTokenRaw.token ?: "")
        verify(preferenceWrapper).saveString(KEY_AUTH_REFRESH_TOKEN, mTokenRaw.refreshToken ?: "")
        Assert.assertEquals(ResultModel.Loading, values.first())
        Assert.assertEquals(
            ResultModel.Success(
                TokenModel(
                    "This is token", "This is refreshToken"
                )
            ), values[1]
        )
        Assert.assertEquals(ResultModel.Done, values.last())
    }

    @Test
    fun testLogin_Return_ResultModel_Exception() = runBlocking {
        val mError =
            "{\n" + "  \"metadata\": {\n" + "    \"status\": false,\n" + "    \"message\": \"testLogin_Return_ResultModel_Exception\"\n" + "  }\n" + "}"
        val mAppException = ResultModel.AppException(
            type = TypeException.Network(httpCode = 401),
            message = "testLogin_Return_ResultModel_Exception"
        )
        Mockito.`when`(service.login()).thenReturn(
            Response.error(401, mError.toResponseBody("application/json".toMediaTypeOrNull()))
        )
        val values = mutableListOf<ResultModel<TokenModel>>()
        repository.login().collect {
            values.add(it)
        }
        Assert.assertEquals(ResultModel.Loading, values.first())
        Assert.assertEquals(
            ResultModel.AppException(type = mAppException.type, message = mAppException.message),
            values[1]
        )
        Assert.assertEquals(ResultModel.Done, values.last())
    }

    @Test
    fun testLogin_Return_ResultModel_Exception_SomethingsWrong() = runBlocking {
        val mError = ""
        val mAppException = ResultModel.AppException(
            type = TypeException.Network(httpCode = 502), message = "Network Somethings wrong!"
        )
        Mockito.`when`(service.login()).thenReturn(
            Response.error(501, mError.toResponseBody("application/json".toMediaTypeOrNull()))
        )
        val values = mutableListOf<ResultModel<TokenModel>>()
        repository.login().collect {
            values.add(it)
        }
        Assert.assertEquals(ResultModel.Loading, values.first())
        Assert.assertEquals(mAppException.type, (values[1] as ResultModel.AppException).type)
        Assert.assertTrue((values[1] as ResultModel.AppException).message?.contains(mAppException.message!!) == true)
        Assert.assertEquals(ResultModel.Done, values.last())
    }
}