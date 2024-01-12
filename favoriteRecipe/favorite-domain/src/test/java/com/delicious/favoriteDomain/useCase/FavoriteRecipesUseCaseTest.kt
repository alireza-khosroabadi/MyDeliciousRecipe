package com.delicious.favoriteDomain.useCase

import app.cash.turbine.test
import com.delicious.base.domain.ResultState
import com.delicious.favoriteDomain.fakeData.fakeFavoriteRecipeList
import com.delicious.favoriteDomain.fakeData.fakeFavoriteRecipeListAsFlow
import com.delicious.favoriteDomain.model.FavoriteRecipe
import com.delicious.favoriteDomain.repository.FavoriteRecipeRepository
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
import kotlin.test.assertIs

@RunWith(MockitoJUnitRunner::class)
class FavoriteRecipesUseCaseTest {
    private val testScope = TestScope(UnconfinedTestDispatcher())

    @Mock
    private lateinit var repository: FavoriteRecipeRepository

    private lateinit var useCase: FavoriteRecipesUseCase

    @Before
    fun setUp() {
        useCase = FavoriteRecipesUseCase(repository)
    }

    @Test
    fun `invoke function should return ResultState Success when it call`() =
        testScope.runTest {
            // Given
            whenever(repository.favoriteRecipes()).thenReturn(fakeFavoriteRecipeListAsFlow)

            // WHen
            val result = useCase.invoke()

            // Assert
            result.test {
                val state = awaitItem()
                assertIs<ResultState.Success<List<FavoriteRecipe>>>(state)
                assertEquals(fakeFavoriteRecipeList.size, state.data.size)
                awaitComplete()
            }
        }
}
