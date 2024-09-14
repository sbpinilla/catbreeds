package com.sergiodev.catbreeds.splash.ui

import android.animation.Animator
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.sergiodev.catbreeds.R
import com.sergiodev.catbreeds.catList.ui.CatListActivity
import com.sergiodev.catbreeds.core.view.BaseActivity
import com.sergiodev.catbreeds.databinding.ActivitySplashBinding

/**
 * Esta clase modela la actividad de splash,
 * @author sbpinilla
 * @version 1.0
 */
class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val delaySplash: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    /**
     * Metodo encargado de inicializar los componentes de la interfaz grafica
     */
    private fun setupView() {

        binding.lavAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) = Unit

            override fun onAnimationEnd(animation: Animator) {
                gotoCatList()
            }

            override fun onAnimationCancel(animation: Animator) = Unit

            override fun onAnimationRepeat(animation: Animator) = Unit

        })
    }


    private fun gotoCatList() {
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToOption(this@SplashActivity, CatListActivity::class.java)

        }, delaySplash)

    }


}