package com.jorge.animeboxd.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jorge.animeboxd.data.local.AniCatalogDatabase
import com.jorge.animeboxd.data.repository.AnimeRepository
import com.jorge.animeboxd.presentation.ViewModelFactory
import com.jorge.animeboxd.presentation.catalog.CatalogScreen
import com.jorge.animeboxd.presentation.catalog.CatalogViewModel
import com.jorge.animeboxd.presentation.detail.DetailScreen
import com.jorge.animeboxd.presentation.home.HomeScreen
import com.jorge.animeboxd.presentation.mylist.MyListScreen
import com.jorge.animeboxd.presentation.mylist.MyListViewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.jorge.animeboxd.ui.theme.OledBlack

sealed class Screen(val route: String) {
    object Home    : Screen("home")
    object Catalog : Screen("catalog")
    object MyList  : Screen("mylist")
    object Detail  : Screen("detail/{animeId}") {
        fun pass(id: Int) = "detail/$id"
    }
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    val context = LocalContext.current
    val db = AniCatalogDatabase.getInstance(context)
    val repository = AnimeRepository(db.animeDao())
    val factory = ViewModelFactory(repository)

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        enterTransition = {
            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300))
        },
        exitTransition = {
            slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(300))
        },
        popEnterTransition = {
            slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300))
        }
    ) {
        composable(Screen.Home.route) {
            val viewModel: MyListViewModel = viewModel(factory = factory)
            HomeScreen(
                viewModel = viewModel,
                onNavigateToCatalog = { navController.navigate(Screen.Catalog.route) },
                onNavigateToMyList = { navController.navigate(Screen.MyList.route) },
                onAnimeClick = { animeId -> navController.navigate(Screen.Detail.pass(animeId)) }
            )
        }

        composable(Screen.Catalog.route) {
            val viewModel: CatalogViewModel = viewModel(factory = factory)
            CatalogScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToMyList = { navController.navigate(Screen.MyList.route) }
            )
        }

        composable(Screen.MyList.route) {
            val viewModel: MyListViewModel = viewModel(factory = factory)
            MyListScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToCatalog = { navController.navigate(Screen.Catalog.route) },
                onAnimeClick = { animeId -> navController.navigate(Screen.Detail.pass(animeId)) }
            )
        }

        composable(Screen.Detail.route) { backStackEntry ->
            val animeId = backStackEntry.arguments?.getString("animeId")?.toIntOrNull() ?: 0
            val viewModel: MyListViewModel = viewModel(factory = factory)
            val animes by viewModel.watchedAnimes.collectAsState()
            val anime = animes.find { it.id == animeId }
            if (anime != null) {
                DetailScreen(
                    viewModel = viewModel,
                    anime = anime,
                    onNavigateBack = { navController.popBackStack() }
                )
            } else {
                Text(
                    text = "Anime não encontrado",
                    modifier = Modifier.background(OledBlack)
                )
            }
        }
    }
}