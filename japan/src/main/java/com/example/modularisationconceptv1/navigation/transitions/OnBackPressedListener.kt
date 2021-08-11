package com.example.modularisationconceptv1.navigation.transitions

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
