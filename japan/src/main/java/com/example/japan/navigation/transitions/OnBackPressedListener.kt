package com.example.japan.navigation.transitions

import android.view.MenuItem

interface OnBackPressedListener {
  fun onBackPressed(): Boolean
}

fun handleBackPressed(item: MenuItem, callback: VoidCallback): Boolean {
  if (item.itemId == android.R.id.home) {
    callback()
    return true
  }
  return false
}
