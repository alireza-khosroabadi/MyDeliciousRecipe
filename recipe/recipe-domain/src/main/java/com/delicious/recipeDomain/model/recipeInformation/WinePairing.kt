package com.delicious.recipeDomain.model.recipeInformation

import com.delicious.base.model.domain.baseDomainModel.DomainModel

data class WinePairing(
    val pairedWines    : ArrayList<String>,
    val pairingText    : String,
    val productMatches : ArrayList<ProductMatches>
): DomainModel
