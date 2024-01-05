package com.delicious.recipes.ui.bottomBar

import com.delicious.recipes.R
import com.delicious.ui.R as uiR

enum class BottomNavBarDestination(
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    HOME(
        selectedIcon = uiR.drawable.ic_home_selected,
        unselectedIcon = uiR.drawable.ic_home_unselected,
        iconTextId = uiR.string.home,
        titleTextId = R.string.app_name,
    ),
    FAVORITE(
        selectedIcon = uiR.drawable.ic_favorite_selected,
        unselectedIcon = uiR.drawable.ic_favorite_unselected,
        iconTextId = uiR.string.favorites,
        titleTextId = uiR.string.favorites,
    ),
//    CONTACTS(
//        selectedIcon = uiR.drawable.ic_bottombar_contacts_selected,
//        unselectedIcon = uiR.drawable.ic_bottombar_contacts_unselected,
//        iconTextId = appR.string.bottom_navigation_contacts,
//        titleTextId = appR.string.top_bar_title_contacts,
//    ),
//    PAYMENTCALENDER(
//        selectedIcon = uiR.drawable.ic_bottombar_paymentcalender_selected,
//        unselectedIcon = uiR.drawable.ic_bottombar_paymentcalender_unselected,
//        iconTextId = appR.string.bottom_navigation_payment_calender,
//        titleTextId = appR.string.top_bar_title_payment_calender,
//    )
}
