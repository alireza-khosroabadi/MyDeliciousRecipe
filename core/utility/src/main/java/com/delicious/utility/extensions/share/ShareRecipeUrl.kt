package com.delicious.utility.extensions.share

import android.content.Context
import android.content.Intent
 fun Context.shareString(
    string: String
) {
    val sendIntent =
        Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, string)
            type = "text/plain"
        }
    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent, null)
}
