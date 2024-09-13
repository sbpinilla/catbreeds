package com.sergiodev.catbreeds.core.utils.extensionFunction

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 *  Metodo que nos permite ocultar el teclado
 */
fun View.hideKeyboard() {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}
