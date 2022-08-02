package com.sema.composeapp.ui.navigation

import androidx.annotation.StringRes
import com.sema.composeapp.R

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object BeerDetail : Screen("beerdetail/{beerId}") {
        fun createRoute(beerId: Int) = "beerdetail/$beerId"
    }
}