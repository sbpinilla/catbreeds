package com.sergiodev.catbreeds.catDetail.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.sergiodev.catbreeds.R
import com.sergiodev.catbreeds.catList.ui.CatListActivity
import com.sergiodev.catbreeds.catList.ui.model.CatModel
import com.sergiodev.catbreeds.databinding.ActivityCatDetailBinding


/**
 * Esta clase modela la actividad de detalle de gato
 * @author spinilla
 * @version 1.0
 */
class CatDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatDetailBinding

    private lateinit var gCat: CatModel

    /**
     * Metodo llamado al momento de crear la actividad, necesario para inicializar los elementos de la interfaz,
     * el viewmodel, y para cargar la información.
     * @param savedInstanceState El estado previamente guardado de la actividad, si lo hay.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupToolBar()
        setupListeners()

    }

    /**
     * Metodo encargado de inicializar los componentes de la interfaz grafica
     */
    private fun setupView() {

        gCat = intent.getSerializableExtra(CatListActivity.BUNDLE_CAT) as CatModel
        Glide.with(this)
            .load(gCat.url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.drawable.pets_24)
            .into(binding.imgCat)

        binding.txtDescription.text = gCat.description
        binding.txtOrigin.text = gCat.origin
        binding.txtIntelligence.text = gCat.intelligence.toString()
        binding.txtLifeSpan.text = gCat.lifeSpan
        binding.txtAltNames.text = gCat.altNames.ifEmpty { getString(R.string.cat_detail_txt_not_fount) }


    }

    /**
     * Metodo encargado de inicializar la barra de navegación
     */
    private fun setupToolBar() {

        setSupportActionBar(binding.tbToolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = gCat.name
        binding.tbToolBar.setNavigationOnClickListener {

            onBackPressedDispatcher.onBackPressed()
        }

    }

    /**
     * Metodo utilizado para inicializar los eventos de la interfaz grafica
     */
    private fun setupListeners() {

        binding.lyVetsStreet.setOnClickListener {
            openUrl(gCat.urlVetstreet)
        }

        binding.LyWiki.setOnClickListener {
            openUrl(gCat.urlWiki)
        }

    }

    /**
     * Metodo utilizado abrir una url
     * @param url Url para abrir
     */
    private fun openUrl(url: String) {

        if (url.isEmpty()) {
            Snackbar.make(binding.imgCat, getString(R.string.cat_detail_url_not_fount), Snackbar.LENGTH_LONG).show()
            return
        }

        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}