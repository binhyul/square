package com.example.square.ui.home.model

import androidx.annotation.StringRes
import com.example.square.R

enum class TabType(@StringRes val tabText: Int) {
    LIST(R.string.list),
    FAVORITE(R.string.favorite)
}