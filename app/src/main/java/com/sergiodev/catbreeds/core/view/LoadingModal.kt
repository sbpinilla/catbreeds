package com.sergiodev.catbreeds.core.view

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.view.WindowManager
import com.sergiodev.catbreeds.R
import com.sergiodev.catbreeds.databinding.ModalLoaderBinding

/**
 * Esta clase modela el modal de cargando
 * @author sbpinilla@asesoftware.com
 * @version 1.0
 */
class LoadingModal(context: Context) {


    private val dialog: Dialog = Dialog(context)
    private val binding: ModalLoaderBinding = ModalLoaderBinding.inflate(dialog.layoutInflater)


    /**
     * Metodo inicializador de la clase, se encarga de validar que modal tenga tranparencia
     */
    init {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(binding.root)

        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window?.attributes)
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog.window?.attributes = layoutParams
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

    }

    /**
     * Metodo encargado de mostrar el modal
     */
    fun show() {
        dialog.show()
    }

    /**
     * Metodo encargado de ocultar el modal
     */
    fun hide() {
        dialog.dismiss()
    }
}