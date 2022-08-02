package com.sema.composeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sema.composeapp.ui.view.BeerDetail
import com.sema.composeapp.ui.view.BeerList
import com.sema.composeapp.ui.view.beer.BeerViewModel
import com.sema.composeapp.ui.view.beerdetail.BeerDetailViewModel

@Preview
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }

    NavHost(navController, startDestination = Screen.Dashboard.route) {

        composable(Screen.Dashboard.route) {
            val homeViewModel: BeerViewModel = hiltViewModel()
            BeerList(actions, homeViewModel)
        }

        composable(Screen.BeerDetail.route) {
            val beerDetailViewModel: BeerDetailViewModel = hiltViewModel()
            BeerDetail(beerDetailViewModel, actions)
        }
    }
}

class MainActions(private val navController: NavHostController) {

    val popBackStack: () -> Unit = {
        navController.popBackStack()
    }

    val upPress: () -> Unit = {
        navController.navigateUp()
    }

    val goToHome: () -> Unit = {
        navController.popBackStack()
        navController.navigate(Screen.Dashboard.route)
    }

    val gotoBeerDetail: (Int) -> Unit = {
        navController.navigate(Screen.BeerDetail.createRoute(it))
    }
}