package com.sergiodev.catbreeds.catList.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sergiodev.catbreeds.catList.ui.model.CatModel
import com.sergiodev.catbreeds.databinding.ItemCatListBinding


/**
 * Clase que modela el adaptador de la lista de gatos
 * @param data La lista de [CatModel] que se mostrará en el RecyclerView.
 */
class CatListAdapter(private var data: List<CatModel>) :
    RecyclerView.Adapter<CatListAdapter.ViewHolder>() {

    private lateinit var context: Context
    private lateinit var onSelectCat: (catModel: CatModel, viewImage: ImageView) -> Unit

    /**
     * Metodo que actualiza la lista de datos del adaptador y notifica los cambios.
     * @param data La nueva lista de [CatModel] que se utilizará en el adaptador.
     */
    fun update(data: List<CatModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    /**
     * Metodo que establece la función de devolución de llamada que se invocará cuando se seleccione una card.
     * @param callBack La función de devolución de llamada que se ejecutará cuando se seleccione una card.
     */
    fun onSelectCat(callBack: (catModel: CatModel, viewImage: ImageView) -> Unit) {
        this.onSelectCat = callBack
    }

    /**
     * @inheritDoc
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val binding = ItemCatListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    /**
     * @inheritDoc
     */
    override fun getItemCount(): Int = data.count()

    /**
     * @inheritDoc
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position])

    /**
     * Clase interna que representa un ViewHolder para un elemento de la lista en el RecyclerView.
     * @param binding El objeto de enlace que contiene las vistas del elemento de la lista .
     */
    inner class ViewHolder(private val binding: ItemCatListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(catModel: CatModel) {

            binding.txtName.text = catModel.name
            binding.txtCountry.text = catModel.origin
            binding.txtintelligence.text = "${catModel.intelligence}"

            Glide.with(context).load(catModel.url).into(binding.imgCat)

            binding.clContainer.setOnClickListener {
                onSelectCat(catModel, binding.imgCat)
            }

            binding.txtMore.setOnClickListener {
                onSelectCat(catModel, binding.imgCat)
            }

        }

    }

}