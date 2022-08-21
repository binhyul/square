package com.example.square.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.square.ui.home.favorite.FavoriteTabPage
import com.example.square.ui.home.list.ListTabPage
import com.example.square.ui.home.model.TabType
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Home(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    val tabs = TabType.values()
    val pageState = rememberPagerState(
        initialPage = 0
    )

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
                0 -> ListTabPage(navController = navController)
                else -> FavoriteTabPage(navController = navController)
            }
        }
    }
}