package com.sergiodev.catbreeds.catList.ui

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.doOnNextLayout
import androidx.core.widget.NestedScrollView
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sergiodev.catbreeds.R
import com.sergiodev.catbreeds.catDetail.ui.CatDetailActivity
import com.sergiodev.catbreeds.catList.ui.model.CatModel
import com.sergiodev.catbreeds.core.network.ApiResponse
import com.sergiodev.catbreeds.core.utils.extensionFunction.hideKeyboard
import com.sergiodev.catbreeds.core.view.BaseActivity
import com.sergiodev.catbreeds.databinding.ActivityCatListBinding
import com.sergiodev.catbreeds.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Esta clase modela la actividad de Lista de gatos
 * @author spinilla
 * @version 1.0
 */
@AndroidEntryPoint
class CatListActivity : BaseActivity() {

    private val catListViewModel: CatListViewModel by viewModels()

    private lateinit var binding: ActivityCatListBinding

    private var gCardsAdapter: CatListAdapter = CatListAdapter(listOf())
    private var gCats: List<CatModel> = listOf()

    companion object {

        const val BUNDLE_CAT = "catModel"
    }

    /**
     * Metodo llamado al momento de crear la actividad, necesario para inicializar los elementos de la interfaz,
     * el viewmodel, y para cargar la información.
     * @param savedInstanceState El estado previamente guardado de la actividad, si lo hay.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCatListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupViewModel()
        setupListeners()

    }

    /**
     * Metodo encargado de inicializar los componentes de la interfaz grafica
     */
    private fun setupView() {

        binding.rvCats.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }
        gCardsAdapter.onSelectCat { catModel, viewImage ->

            goToCatDetail(catModel, viewImage)
        }

        binding.rvCats.adapter = gCardsAdapter

    }

    /**
     * Metodo utlizado para observar el llamado al web service de gatos
     */
    private fun setupViewModel() {

        catListViewModel.catList.observe(this) { apiResponse ->

            when (apiResponse) {
                is ApiResponse.Error -> processError()
                is ApiResponse.Loading -> if (apiResponse.isLoading) loadingModal.show() else loadingModal.hide()
                is ApiResponse.Success -> processCatList(apiResponse.data)
            }

        }

        catListViewModel.getCats()

    }


    /**
     * Metodo utilizado para inicializar los eventos de la interfaz grafica
     */
    private fun setupListeners() {

        binding.edtSearch.doOnTextChanged { inputText, _, _, _ ->

            if (gCats.isEmpty())
                return@doOnTextChanged

            val filterList = catListViewModel.filterCatsList(gCats, inputText.toString())
            binding.lavNotFount.visibility = if (filterList.isEmpty()) View.VISIBLE else View.GONE
            gCardsAdapter.update(filterList)

        }

        binding.edtSearch.doOnNextLayout {
            it.hideKeyboard()
        }

        binding.edtSearch.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || (event?.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {
                binding.edtSearch.hideKeyboard()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        binding.btnReSend.setOnClickListener {
            catListViewModel.getCats()
        }

        binding.rvCats.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager

                val isAtTop = layoutManager.findFirstCompletelyVisibleItemPosition() == 0

                binding.fabUp.visibility = if (isAtTop) View.GONE else View.VISIBLE

            }
        })

        binding.fabUp.setOnClickListener {
            binding.rvCats.smoothScrollToPosition(0)
        }

    }

    /**
     * Metodo utilizado para procesar la la lista de gatos
     * @param cats Lista de gatos obtenida desde el servidor
     */
    private fun processCatList(cats: List<CatModel>) {
        binding.edtSearch.setText("")
        binding.lavNotRed.visibility = View.GONE
        binding.rvCats.visibility = View.VISIBLE
        gCats = cats
        gCardsAdapter.update(gCats)
    }

    private fun processError() {
        binding.lavNotRed.visibility = View.VISIBLE
        binding.rvCats.visibility = View.GONE
        Snackbar.make(binding.btnReSend, getString(R.string.cat_list_txt_error), Snackbar.LENGTH_LONG).show()
    }

    /**
     * Metodo utilizado para navergar a la interfaz de [CatDetailActivity]
     * @param cat Item seleccionado
     */
    private fun goToCatDetail(cat: CatModel, viewImage: ImageView) {
        binding.edtSearch.hideKeyboard()
        val intent = Intent(this, CatDetailActivity::class.java)
        val options = ActivityOptions.makeSceneTransitionAnimation(this, viewImage, "sharedElementImageView")
        intent.putExtra(CatListActivity.BUNDLE_CAT, cat)
        startActivity(intent, options.toBundle())

        // navigateToOption(this@CatListActivity, CatDetailActivity::class.java, isFinish = false)
    }


}