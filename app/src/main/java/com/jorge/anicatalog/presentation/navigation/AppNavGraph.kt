package com.jorge.anicatalog.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jorge.anicatalog.data.local.AniCatalogDatabase
import com.jorge.anicatalog.data.repository.AnimeRepository
import com.jorge.anicatalog.presentation.ViewModelFactory
import com.jorge.anicatalog.presentation.catalog.CatalogScreen
import com.jorge.anicatalog.presentation.catalog.CatalogViewModel
import com.jorge.anicatalog.presentation.home.HomeScreen
import com.jorge.anicatalog.presentation.mylist.MyListScreen
import com.jorge.anicatalog.presentation.mylist.MyListViewModel

sealed class Screen(val route: String) {
    object Home    : Screen("home")
    object Catalog : Screen("catalog")
    object MyList  : Screen("mylist")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    val context = LocalContext.current
    val db = AniCatalogDatabase.getInstance(context)
    val repository = AnimeRepository(db.animeDao())
    val factory = ViewModelFactory(repository)

    NavHost(navController = navController, startDestination = Screen.Home.route) {

        composable(Screen.Home.route) {
            val viewModel: MyListViewModel = viewModel(factory = factory)
            HomeScreen(
                viewModel = viewModel,
                onNavigateToCatalog = { navController.navigate(Screen.Catalog.route) },
                onNavigateToMyList  = { navController.navigate(Screen.MyList.route) }
            )
        }

        composable(Screen.Catalog.route) {
            val viewModel: CatalogViewModel = viewModel(factory = factory)
            CatalogScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.MyList.route) {
            val viewModel: MyListViewModel = viewModel(factory = factory)
            MyListScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}