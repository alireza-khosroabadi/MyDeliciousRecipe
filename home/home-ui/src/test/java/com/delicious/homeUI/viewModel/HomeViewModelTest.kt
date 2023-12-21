package com.delicious.homeUI.viewModel

import com.delicious.base.testing.MainDispatcherRule
import com.delicious.homeDomain.useCase.RandomRecipeUseCase
import com.delicious.homeUI.viewModel.fake.exception
import com.delicious.homeUI.viewModel.fake.fakeRandomRecipeExceptionResult
import com.delicious.homeUI.viewModel.fake.fakeRandomRecipesFailureResult
import com.delicious.homeUI.viewModel.fake.fakeRandomRecipesSuccessResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var randomRecipeUseCase: RandomRecipeUseCase

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(randomRecipeUseCase)
    }


    @Test
    fun `testing default values of ui state`()=runTest {

        assertIs<HomeUiState.Loading>(homeViewModel.randomRecipeUiState.value)
        assertTrue(homeViewModel.randomRecipeUiState.value.isLoading)
    }

    @Test
    fun `should return list of random recipe if successful`() = runTest {
        // Given
        whenever(randomRecipeUseCase.invoke()).thenReturn(fakeRandomRecipesSuccessResult)

        val collectJob = launch(UnconfinedTestDispatcher()){homeViewModel.randomRecipeUiState.collect()}

        //Collect
        val item = homeViewModel.randomRecipeUiState.value

        //Assert
        assertIs<HomeUiState.RandomRecipes>(item)
        assertEquals(item.data.size , fakeRandomRecipesSuccessResult.data.size)

        collectJob.cancel()
    }

    @Test
    fun `should return list of random recipe if failure`() = runTest {
        // Given
        whenever(randomRecipeUseCase.invoke()).thenReturn(fakeRandomRecipesFailureResult)

        val collectJob = launch(UnconfinedTestDispatcher()){homeViewModel.randomRecipeUiState.collect()}

        //Collect
        val item = homeViewModel.randomRecipeUiState.value

        //Assert
        assertIs<HomeUiState.Error>(item)
        assertTrue(item.hasError)
        assertEquals(item.errorMessage , fakeRandomRecipesFailureResult.error)

        collectJob.cancel()
    }


    @Test
    fun `should return error of random recipe if exception`() = runTest {
        // Given
        whenever(randomRecipeUseCase.invoke()).thenReturn(fakeRandomRecipeExceptionResult)

        val collectJob = launch(UnconfinedTestDispatcher()){homeViewModel.randomRecipeUiState.collect()}

        //Collect
        val item = homeViewModel.randomRecipeUiState.value

        //Assert
        assertIs<HomeUiState.Error>(item)
        assertTrue(item.hasError)
        assertEquals(item.errorMessage , exception.message)

        collectJob.cancel()
    }
}