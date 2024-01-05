package com.delicious.recipeData.model.recipeInformation

import com.delicious.base.model.data.baseDataModel.DataModel
import com.delicious.recipeDomain.model.recipeInformation.RecipeInformation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeInformationResponse(
    @SerialName("id"                       ) var id                       : Int?                           = null,
    @SerialName("title"                    ) var title                    : String?                        = null,
    @SerialName("image"                    ) var image                    : String?                        = null,
    @SerialName("imageType"                ) var imageType                : String?                        = null,
    @SerialName("servings"                 ) var servings                 : Int?                           = null,
    @SerialName("readyInMinutes"           ) var readyInMinutes           : Int?                           = null,
    @SerialName("license"                  ) var license                  : String?                        = null,
    @SerialName("sourceName"               ) var sourceName               : String?                        = null,
    @SerialName("sourceUrl"                ) var sourceUrl                : String?                        = null,
    @SerialName("spoonacularSourceUrl"     ) var spoonacularSourceUrl     : String?                        = null,
    @SerialName("healthScore"              ) var healthScore              : Int?                           = null,
    @SerialName("spoonacularScore"         ) var spoonacularScore         : Int?                           = null,
    @SerialName("pricePerServing"          ) var pricePerServing          : Double?                        = null,
    @SerialName("analyzedInstructions"     ) var analyzedInstructions     : ArrayList<String>              = arrayListOf(),
    @SerialName("cheap"                    ) var cheap                    : Boolean?                       = null,
    @SerialName("creditsText"              ) var creditsText              : String?                        = null,
    @SerialName("cuisines"                 ) var cuisines                 : ArrayList<String>              = arrayListOf(),
    @SerialName("dairyFree"                ) var dairyFree                : Boolean?                       = null,
    @SerialName("diets"                    ) var diets                    : ArrayList<String>              = arrayListOf(),
    @SerialName("gaps"                     ) var gaps                     : String?                        = null,
    @SerialName("glutenFree"               ) var glutenFree               : Boolean?                       = null,
    @SerialName("instructions"             ) var instructions             : String?                        = null,
    @SerialName("ketogenic"                ) var ketogenic                : Boolean?                       = null,
    @SerialName("lowFodmap"                ) var lowFodmap                : Boolean?                       = null,
    @SerialName("occasions"                ) var occasions                : ArrayList<String>              = arrayListOf(),
    @SerialName("sustainable"              ) var sustainable              : Boolean?                       = null,
    @SerialName("vegan"                    ) var vegan                    : Boolean?                       = null,
    @SerialName("vegetarian"               ) var vegetarian               : Boolean?                       = null,
    @SerialName("veryHealthy"              ) var veryHealthy              : Boolean?                       = null,
    @SerialName("veryPopular"              ) var veryPopular              : Boolean?                       = null,
    @SerialName("whole30"                  ) var whole30                  : Boolean?                       = null,
    @SerialName("weightWatcherSmartPoints" ) var weightWatcherSmartPoints : Int?                           = null,
    @SerialName("dishTypes"                ) var dishTypes                : ArrayList<String>              = arrayListOf(),
    @SerialName("extendedIngredients"      ) var extendedIngredients      : ArrayList<ExtendedIngredientsResponse> = arrayListOf(),
    @SerialName("summary"                  ) var summary                  : String?                        = null,
    @SerialName("winePairing"              ) var winePairing              : WinePairingResponse?           = WinePairingResponse()
):DataModel{
    override fun toDomainModel(): RecipeInformation {
        TODO("Not yet implemented")
    }

}
