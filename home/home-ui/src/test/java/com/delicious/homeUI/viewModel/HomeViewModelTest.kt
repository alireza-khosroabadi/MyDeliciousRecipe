package com.delicious.homeUI.viewModel

import com.delicious.base.testing.MainDispatcherRule
import com.delicious.homeDomain.useCase.MealTypeUseCase
import com.delicious.homeDomain.useCase.PopularRecipeUseCase
import com.delicious.homeDomain.useCase.RandomRecipeUseCase
import com.delicious.homeUI.viewModel.fake.exception
import com.delicious.homeUI.viewModel.fake.fakeMealTypesSuccessResult
import com.delicious.homeUI.viewModel.fake.fakePopularRecipesSuccessResult
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

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var popularRecipeUseCase: PopularRecipeUseCase

    @Mock
    private lateinit var mealTypeUseCase: MealTypeUseCase

    @Mock
    private lateinit var randomRecipeUseCase: RandomRecipeUseCase

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(popularRecipeUseCase, mealTypeUseCase, randomRecipeUseCase)
    }


    @Test
    fun `testing default values of ui states`() = runTest {
        assertIs<PopularRecipeUiState.Loading>(homeViewModel.popularRecipeUiState.value)
        assertIs<MealTypeUiState.Loading>(homeViewModel.mealTypeUiState.value)
        assertIs<RandomRecipeUiState.Loading>(homeViewModel.randomRecipeUiState.value)
    }

    @Test
    fun `should return list of popular recipe if successful`() = runTest {
        // Given
        whenever(popularRecipeUseCase.invoke()).thenReturn(fakePopularRecipesSuccessResult)

        val collectJob =
            launch(UnconfinedTestDispatcher()) { homeViewModel.popularRecipeUiState.collect() }

        //Collect
        val item = homeViewModel.popularRecipeUiState.value

        //Assert
        assertIs<PopularRecipeUiState.PopularRecipes>(item)
        assertEquals(item.data.size, fakePopularRecipesSuccessResult.data.size)

        collectJob.cancel()
    }

    @Test
    fun `should return list of popular recipe if failure`() = runTest {
        // Given
        whenever(popularRecipeUseCase.invoke()).thenReturn(fakeRandomRecipesFailureResult)

        val collectJob =
            launch(UnconfinedTestDispatcher()) { homeViewModel.popularRecipeUiState.collect() }

        //Collect
        val item = homeViewModel.popularRecipeUiState.value

        //Assert
        assertIs<PopularRecipeUiState.Error>(item)
        assertEquals(item.message, fakeRandomRecipesFailureResult.error)

        collectJob.cancel()
    }


    @Test
    fun `should return error of popular recipe if exception`() = runTest {
        // Given
        whenever(popularRecipeUseCase.invoke()).thenReturn(fakeRandomRecipeExceptionResult)

        val collectJob =
            launch(UnconfinedTestDispatcher()) { homeViewModel.popularRecipeUiState.collect() }

        //Collect
        val item = homeViewModel.popularRecipeUiState.value

        //Assert
        assertIs<PopularRecipeUiState.Error>(item)
        assertEquals(item.message, exception.message)

        collectJob.cancel()
    }

    @Test
    fun `should return list of random recipe if successful`() = runTest {
        // Given
        whenever(randomRecipeUseCase.invoke()).thenReturn(fakeRandomRecipesSuccessResult)

        val collectJob =
            launch(UnconfinedTestDispatcher()) { homeViewModel.randomRecipeUiState.collect() }

        //Collect
        val item = homeViewModel.randomRecipeUiState.value

        //Assert
        assertIs<RandomRecipeUiState.RandomRecipes>(item)
        assertEquals(item.randomRecipes.size, fakeRandomRecipesSuccessResult.data.size)

        collectJob.cancel()
    }

    @Test
    fun `should return list of random recipe if failure`() = runTest {
        // Given
        whenever(randomRecipeUseCase.invoke()).thenReturn(fakeRandomRecipesFailureResult)

        val collectJob =
            launch(UnconfinedTestDispatcher()) { homeViewModel.randomRecipeUiState.collect() }

        //Collect
        val item = homeViewModel.randomRecipeUiState.value

        //Assert
        assertIs<RandomRecipeUiState.Error>(item)
        assertEquals(item.message, fakeRandomRecipesFailureResult.error)

        collectJob.cancel()
    }


    @Test
    fun `should return error of random recipe if exception`() = runTest {
        // Given
        whenever(randomRecipeUseCase.invoke()).thenReturn(fakeRandomRecipeExceptionResult)

        val collectJob =
            launch(UnconfinedTestDispatcher()) { homeViewModel.randomRecipeUiState.collect() }

        //Collect
        val item = homeViewModel.randomRecipeUiState.value

        //Assert
        assertIs<RandomRecipeUiState.Error>(item)
        assertEquals(item.message, exception.message)

        collectJob.cancel()
    }


    @Test
    fun `should return list of meal types if successful`() = runTest {
        // Given
        whenever(mealTypeUseCase.invoke()).thenReturn(fakeMealTypesSuccessResult)

        val collectJob =
            launch(UnconfinedTestDispatcher()) { homeViewModel.mealTypeUiState.collect() }

        //Collect
        val item = homeViewModel.mealTypeUiState.value

        //Assert
        assertIs<MealTypeUiState.MealTypes>(item)
        assertEquals(item.mealTypes.size, fakeMealTypesSuccessResult.data.size)

        collectJob.cancel()
    }
}