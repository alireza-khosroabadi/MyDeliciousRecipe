package com.delicious.favoriteData.repository

import app.cash.turbine.test
import com.delicious.base.domain.ResultState
import com.delicious.datastore.dataSource.FavoriteRecipeDataSource
import com.delicious.datastore.model.favoriteRecipe.FavoriteRecipePreferences
import com.delicious.favoriteData.fakeResults.fakeFavoriteRecipesList
import com.delicious.favoriteData.fakeResults.fakeFavoriteRecipesListAsFlow
import com.delicious.favoriteData.mapper.toDomainModel
import com.delicious.favoriteDomain.model.FavoriteRecipe
import com.delicious.favoriteDomain.repository.FavoriteRecipeRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.test.assertIs

@RunWith(MockitoJUnitRunner::class)
class DefaultFavoriteRecipeRepositoryTest {
    private val testScope = TestScope(UnconfinedTestDispatcher())

    @Mock
    private lateinit var dataSource: FavoriteRecipeDataSource

    private lateinit var favoriteRecipeRepository: FavoriteRecipeRepository

    @Before
    fun setUp() {
        favoriteRecipeRepository = DefaultFavoriteRecipeRepository(dataSource)
    }

    @Test
    fun `favorite recipe return favorite recipes list success`() =
        testScope.runTest {
            // Given
            whenever(dataSource.favoriteRecipes()).thenReturn(fakeFavoriteRecipesListAsFlow)

            // When
            val response = favoriteRecipeRepository.favoriteRecipes()

            // Assert
            response.test {
                val result = awaitItem()
                assertIs<ResultState.Success<List<FavoriteRecipe>>>(result)
                assertEquals(2, result.data.size)
                awaitComplete()
            }
        }

    @Test
    fun `favorite recipe should return Exception when exception happened`() =
        testScope.runTest {
            // Given
            val exception = RuntimeException("Test")
            whenever(dataSource.favoriteRecipes()).thenAnswer { flow<List<FavoriteRecipePreferences>> { error(exception) } }

            // When
            val response = favoriteRecipeRepository.favoriteRecipes()

            // Assert
            response.test {
                val result = awaitItem()
                assertIs<ResultState.Exception>(result)
                assertEquals("java.lang.RuntimeException: ${exception.message}", result.error.localizedMessage)
                awaitComplete()
            }
        }

    @Test
    fun `dataSource removeRecipe function call when remove function call`() =
        testScope.runTest {
            // When
            favoriteRecipeRepository.deleteRecipe(fakeFavoriteRecipesList[0].toDomainModel())

            // Assert
            verify(dataSource, times(1)).removeRecipe(any())
        }
}
