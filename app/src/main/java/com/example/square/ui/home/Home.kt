package com.example.square.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.square.DETAIL_PAGE_LIKE_REFRESH
import com.example.square.ui.home.favorite.FavoritePageViewModel
import com.example.square.ui.home.favorite.FavoriteTabPage
import com.example.square.ui.home.list.ListPageViewModel
import com.example.square.ui.home.list.ListTabPage
import com.example.square.ui.home.model.TabType
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Home(
    navController: NavController,
    detailPageLikeRefresh: StateFlow<Boolean>
) {
    val coroutineScope = rememberCoroutineScope()
    val tabs = TabType.values()
    val pageState = rememberPagerState(
        initialPage = 0
    )

    val listPageViewModel: ListPageViewModel = hiltViewModel()
    val favoritePageViewModel: FavoritePageViewModel = hiltViewModel()
    val refreshAction: () -> Unit = object : () -> Unit {
        override fun invoke() {
            listPageViewModel.refreshProduct()
            favoritePageViewModel.refreshProduct()
        }
    }

    LaunchedEffect(Unit) {
        detailPageLikeRefresh.collectLatest {
            if (it) {
                navController.currentBackStackEntry?.savedStateHandle?.remove<Boolean>(
                    DETAIL_PAGE_LIKE_REFRESH
                )
                refreshAction()
            }
        }
    }

    Column {
        TabRow(selectedTabIndex = pageState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pageState, tabPositions)
                )
            }) {
            tabs.forEachIndexed { index, tabType ->
                Tab(selected = pageState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pageState.scrollToPage(index)
                        }
                    },
                    text = {
                        Text(text = stringResource(id = tabType.tabText))
                    })
            }
        }
        HorizontalPager(count = tabs.size, state = pageState) { page ->
            when (pageState.currentPage) {
                0 -> ListTabPage(
                    navController = navController,
                    viewModel = listPageViewModel,
                    refresh = refreshAction
                )
                else -> FavoriteTabPage(
                    navController = navController,
                    viewModel = favoritePageViewModel,
                    refresh = refreshAction
                )
            }
        }
    }
}