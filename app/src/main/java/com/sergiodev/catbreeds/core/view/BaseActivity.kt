package com.sergiodev.catbreeds.core.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sergiodev.catbreeds.R
import com.sergiodev.catbreeds.splash.ui.SplashActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Esta clase modela la actividad base de la aplicacion,
 * encargada de definir los comportamiento en comun para todas las interfaces
 * @author sbpinilla
 * @version 1.0
 */
@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {

    lateinit var loadingModal: LoadingModal

    /**
     * Metodo llamado al momento de crear la actividad, necesario para inicializar los elementos de la interfaz,
     * el viewmodel, y para cargar la información.
     * @param savedInstanceState El estado previamente guardado de la actividad, si lo hay.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    /**
     * Metodo del ciclo de vida de la interfaz, es llamado cuando la interfaz entra o sale de reposo
     */
    override fun onResume() {
        super.onResume()
        hideNavigationBar()


    }

    /**
     * Metodo encargado de inicializar los componentes de la interfaz grafica
     */
    private fun setupView() {
        loadingModal = LoadingModal(this)
        hideNavigationBar()

    }

    /**
     * Metodo utilizado realizar la navegacion entre pantallas
     * @param source Pantalla en la que estamos
     * @param destiny Pantalla a la que vamos
     * @param isFinish Se debe destruir la pantalla origen
     */
    fun <T : Activity> navigateToOption(
        source: Activity,
        destiny: Class<T>,
        isFinish: Boolean = true
    ) {
        val intent = Intent(source, destiny)
        startActivity(intent)
        if (isFinish) {
            source.finish()
        }

    }

    /**
     * Metodo encargado de esconder la barra de navegacion
     */
    private fun hideNavigationBar() {

        if (this is SplashActivity) {

            val decorView = window.decorView
            val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            decorView.systemUiVisibility = uiOptions

        }

        window.navigationBarColor = resources.getColor(R.color.catbreedsPrincipal)
    }


}
