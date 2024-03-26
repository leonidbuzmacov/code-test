package com.lb.codetest.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.lb.codetest.R

enum class ClubTheme(
    @DrawableRes
    val topbarImage: Int,
    val accentColor: Color,
    val tabsContainerColor: Color,
    val primary: Color,
    val background: Color
) {
    UCL(
        topbarImage = R.drawable.ucl_header,
        accentColor = UclAccentColor,
        tabsContainerColor = UclTabsContainerColor,
        primary = UclPrimary,
        background = UclBackground
    ),
    UEL(
        topbarImage = R.drawable.uel_header,
        accentColor = UelAccentColor,
        tabsContainerColor = UelTabsContainerColor,
        primary = UelPrimary,
        background = UelBackground
    )
}
