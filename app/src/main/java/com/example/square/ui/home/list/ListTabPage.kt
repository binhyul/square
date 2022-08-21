package com.example.square.ui.home.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.square.REFRESH
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ListTabPage(
    navController: NavController,
    refresh: StateFlow<Boolean>,
    viewModel: ListPageViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val pageState = rememberPagerState()
    val tabs by viewModel.tabs.collectAsState()

    LaunchedEffect(Unit) {
        refresh.collectLatest {
            if (it) {
                navController.currentBackStackEntry?.savedStateHandle?.remove<Boolean>(REFRESH)
                viewModel.refreshProduct()
            }
        }
    }

    Column(modifier = Modifier.padding(top = 10.dp)) {
        if (tabs.isNotEmpty()) {
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
                            Text(text = tabType.name)
                        })
                }
            }
            HorizontalPager(count = tabs.size, state = pageState) { page ->
                ListTab(
                    viewModel = viewModel,
                    navController = navController,
                    categoryId = tabs[page].id
                )
            }
        }
    }
}