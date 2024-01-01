package com.delicious.recipeDomain.model.recipeInformation

import com.delicious.base.model.domain.baseDomainModel.DomainModel


data class ProductMatches(
    val id            : Int,
    val title         : String,
    val description   : String,
    val price         : String,
    val imageUrl      : String,
    val averageRating : Double,
    val ratingCount   : Int,
    val score         : Double,
    val link          : String
): DomainModel
