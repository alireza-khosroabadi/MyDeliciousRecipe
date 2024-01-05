package com.delicious.homeDomain.useCase

import com.delicious.base.domain.ResultState
import com.delicious.homeDomain.repository.homeRepository.HomeRepository
import com.delicious.homeDomain.useCase.fakeRepositoryResponse.fakeMealTypesSuccessResult
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MealTypeUseCaseTest {

    val testScope = TestScope(UnconfinedTestDispatcher())

    @Mock
    lateinit var homeRepository: HomeRepository

    lateinit var mealTypeUseCase: MealTypeUseCase

    @Before
    fun setUp() {
        mealTypeUseCase = MealTypeUseCase(homeRepository)
    }


    @Test
    fun `when get meal types call should return 1 item`() = testScope.runTest {
        //Given
        whenever(homeRepository.getMealType()).thenReturn(fakeMealTypesSuccessResult)

        //When
        val result = mealTypeUseCase.invoke()

        //Assert
        assertTrue(result.isSuccess)
        assertSame(fakeMealTypesSuccessResult.data , (result as ResultState.Success).data)
    }
}