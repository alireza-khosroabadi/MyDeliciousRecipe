package com.delicious.favoriteDomain.useCase

import com.delicious.favoriteDomain.fakeData.fakeFavoriteRecipeList
import com.delicious.favoriteDomain.repository.FavoriteRecipeRepository
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class RemoveFavoriteRecipeUseCaseTest {
    private val testScope = TestScope(UnconfinedTestDispatcher())

    @Mock
    private lateinit var repository: FavoriteRecipeRepository

    private lateinit var useCase: RemoveFavoriteRecipeUseCase

    @Before
    fun setUp() {
        useCase = RemoveFavoriteRecipeUseCase(repository)
    }

    @Test
    fun `when invoke call then should deleteRecipe fun from repository should call`() =
        testScope.runTest {
            // Given
            val recipe = fakeFavoriteRecipeList[0]

            // When
            useCase.invoke(recipe)

            // Assert
            verify(repository, times(1)).deleteRecipe(recipe)
        }
}
