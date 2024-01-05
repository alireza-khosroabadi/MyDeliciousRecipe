package com.delicious.recipeDomain.model.recipeInformation

import com.delicious.base.model.domain.baseDomainModel.DomainModel
import com.delicious.base.model.domain.measure.Measure

data class ExtendedIngredients(
    val aisle        : String,
    val amount       : Int,
    val consitency   : String,
    val id           : Int,
    val image        : String,
    val measures     : Measure,
    val meta         : ArrayList<String>,
    val name         : String,
    val original     : String,
    val originalName : String,
    val unit         : String
):DomainModel
