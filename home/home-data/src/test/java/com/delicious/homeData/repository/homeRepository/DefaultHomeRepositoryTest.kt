package com.delicious.homeData.repository.homeRepository

import com.delicious.base.domain.ResultState
import com.delicious.datastore.dataSource.FavoriteRecipeDataSource
import com.delicious.homeData.apiService.HomeApiService
import com.delicious.homeData.repository.homeRepository.fakeResponse.error401Response
import com.delicious.homeData.repository.homeRepository.fakeResponse.fakePopularRecipeSuccessResponse
import com.delicious.homeData.repository.homeRepository.fakeResponse.fakeRandomRecipeSuccessResponse
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class DefaultHomeRepositoryTest {
    private val testScope = TestScope(UnconfinedTestDispatcher())

    @Mock
    private lateinit var homeApiService: HomeApiService

    @Mock
    private lateinit var dataSource: FavoriteRecipeDataSource

    private lateinit var defaultHomeRepository: DefaultHomeRepository

    @Before
    fun setup() {
        defaultHomeRepository = DefaultHomeRepository(homeApiService, dataSource)
    }

    @Test
    fun `should return success when calling get popular recipes`() =
        testScope.runTest {
            // Given
            whenever(homeApiService.popularRecipes()).thenReturn(fakePopularRecipeSuccessResponse)

            // When
            val response = defaultHomeRepository.getPopularRecipe()

            // Assert
            assertThat(response).isInstanceOf(ResultState.Success::class.java)
        }

    @Test
    @Throws(RuntimeException::class)
    fun `should return exception when calling get popular recipes and exception happen`() =
        testScope.runTest {
            // Given
            val exception = RuntimeException("Error response body")

            whenever(homeApiService.popularRecipes()).thenAnswer { throw exception }

            // When
            val result = defaultHomeRepository.getPopularRecipe()

            // Then
            verify(homeApiService).popularRecipes()
            assertTrue(result is ResultState.Exception)
            val exceptionResult = result as ResultState.Exception
            assertEquals(exception.message, exceptionResult.error.message)
        }

    @Test
    fun `should return failure when calling get popular recipes and server return error`() =
        testScope.runTest {
            // Given
            whenever(homeApiService.popularRecipes()).thenReturn(error401Response)

            // When
            val result = defaultHomeRepository.getPopularRecipe()

            // Then
            verify(homeApiService).popularRecipes()
            assertTrue(result is ResultState.Failure)
            val errorResult = result as ResultState.Failure
            assertEquals(error401Response.code, errorResult.code)
            assertEquals(error401Response.message, errorResult.error)
        }

    @Test
    fun `should return success when calling get random recipes`() =
        testScope.runTest {
            // Given
            whenever(homeApiService.randomRecipes()).thenReturn(fakeRandomRecipeSuccessResponse)

            // When
            val response = defaultHomeRepository.getRandomRecipe()

            // Assert
            assertThat(response).isInstanceOf(ResultState.Success::class.java)
        }

    @Test
    fun `should return failure when calling get random recipes and server return error`() =
        testScope.runTest {
            // Given
            whenever(homeApiService.randomRecipes()).thenReturn(error401Response)

            // When
            val result = defaultHomeRepository.getRandomRecipe()

            // Then
            verify(homeApiService).randomRecipes()
            assertTrue(result is ResultState.Failure)
            val errorResult = result as ResultState.Failure
            assertEquals(error401Response.code, errorResult.code)
            assertEquals(error401Response.message, errorResult.error)
        }

    @Test
    fun `get meal types return success`() =
        testScope.runTest {
            // When
            val result = defaultHomeRepository.getMealType()

            assertTrue(result.isSuccess)
            assertEquals(6, (result as ResultState.Success).data.size)
        }
}
