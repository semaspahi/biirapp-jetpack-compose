package com.sema.base.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Beer(
    @SerializedName("abv")
    val abv: Double?,
    @SerializedName("attenuation_level")
    val attenuationLevel: Double?,
    @SerializedName("boil_volume")
    val boilVolume: BoilVolume?,
    @SerializedName("brewers_tips")
    val brewersTips: String?,
    @SerializedName("contributed_by")
    val contributedBy: String?,
    @SerializedName("description")
    val description: String,
    @SerializedName("ebc")
    val ebc: Double?,
    @SerializedName("first_brewed")
    val firstBrewed: String?,
    @SerializedName("food_pairing")
    val foodPairing: List<String?>?,
    @SerializedName("ibu")
    val ibu: Double?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("ingredients")
    val ingredients: Ingredients?,
    @SerializedName("method")
    val method: Method?,
    @SerializedName("name")
    val name: String,
    @SerializedName("ph")
    val ph: Double?,
    @SerializedName("srm")
    val srm: Double?,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("target_fg")
    val targetFg: Double?,
    @SerializedName("target_og")
    val targetOg: Double?,
    @SerializedName("volume")
    val volume: Volume?
) : Serializable {

    data class BoilVolume(
        @SerializedName("unit")
        val unit: String?,
        @SerializedName("value")
        val value: Int?
    ) : Serializable


    data class Ingredients(
        @SerializedName("hops")
        val hops: List<Any?>?,
        @SerializedName("malt")
        val malt: List<Malt?>?,
        @SerializedName("yeast")
        val yeast: String?
    ) : Serializable {

        data class Malt(
            @SerializedName("amount")
            val amount: Amount?,
            @SerializedName("name")
            val name: String?
        ) : Serializable {

            data class Amount(
                @SerializedName("unit")
                val unit: String?,
                @SerializedName("value")
                val value: Double?
            ) : Serializable
        }
    }


    data class Method(
        @SerializedName("fermentation")
        val fermentation: Fermentation?,
        @SerializedName("mash_temp")
        val mashTemp: List<MashTemp?>?,
        @SerializedName("twist")
        val twist: Any?
    ) : Serializable {

        data class Fermentation(
            @SerializedName("temp")
            val temp: Temp?
        ) : Serializable {

            data class Temp(
                @SerializedName("unit")
                val unit: String?,
                @SerializedName("value")
                val value: Double?
            ) : Serializable
        }


        data class MashTemp(
            @SerializedName("duration")
            val duration: Int?,
            @SerializedName("temp")
            val temp: Temp?
        ) : Serializable {

            data class Temp(
                @SerializedName("unit")
                val unit: String?,
                @SerializedName("value")
                val value: Int?
            ) : Serializable
        }
    }


    data class Volume(
        @SerializedName("unit")
        val unit: String?,
        @SerializedName("value")
        val value: Int?
    ) : Serializable
}