package vn.root.app_compose.pages.login

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import vn.core.domain.ResultModel
import vn.root.domain.usecase.LoginUseCase

class LoginViewModelTest {

    @Mock
    lateinit var useCase: LoginUseCase

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this) // Initialize mocks
        viewModel = LoginViewModel(useCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun settingMainDispatcher() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        Mockito.`when`(useCase.execute()).thenReturn(flowOf(ResultModel.Done))
        try {
            viewModel.onLogin() // Uses testDispatcher, runs its coroutine eagerly
            assertEquals(viewModel.loginState.value, ResultModel.Done)
        } finally {
            Dispatchers.resetMain()
        }
    }
}
