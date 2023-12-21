package com.delicious.homeDomain.useCase

import com.delicious.base.domain.ResultState
import com.delicious.homeDomain.repository.homeRepository.HomeRepository
import com.delicious.homeDomain.useCase.fakeRepositoryResponse.fakeRandomRecipeErrorResult
import com.delicious.homeDomain.useCase.fakeRepositoryResponse.fakeRandomRecipeSuccessResult
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class RandomRecipeUseCaseTest {

    private val testScope = TestScope(UnconfinedTestDispatcher())

    @Mock
    lateinit var homeRepository : HomeRepository

    lateinit var randomRecipeUseCase: RandomRecipeUseCase

    @Before
    fun setUp() {
        randomRecipeUseCase = RandomRecipeUseCase(homeRepository)
    }


    @Test
    fun `fetch home random recipe should return success`() = testScope.runTest {
        //Given
        whenever(homeRepository.getRandomRecipe()).thenReturn(fakeRandomRecipeSuccessResult)

        // When
        val result = randomRecipeUseCase()

        //Assert
        assertTrue(result is ResultState.Success)
        assertTrue(result.isSuccess)

    }


    @Test
    fun `fetch home random recipe should return Failure when call server return error`() = testScope.runTest {

        //Given
        whenever(homeRepository.getRandomRecipe()).thenReturn(fakeRandomRecipeErrorResult)

        // When
        val result = randomRecipeUseCase()

        //Assert
        assertFalse(result.isSuccess)
        assertTrue(result is ResultState.Failure)
    }

    @Test
    fun `fetch home random recipe should return Exception when exception happen`() = testScope.runTest {

        //GIven
        val exception = java.lang.NullPointerException()
        whenever(homeRepository.getRandomRecipe()).thenReturn(ResultState.Exception(exception))

        //When
        val result = randomRecipeUseCase()

        //Assert
        assertFalse(result.isSuccess)
        assertEquals((result as ResultState.Exception).error , exception)
    }
}