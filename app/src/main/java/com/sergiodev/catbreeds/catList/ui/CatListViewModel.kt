package com.sergiodev.catbreeds.catList.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergiodev.catbreeds.catList.interactor.ICatListInteractor
import com.sergiodev.catbreeds.catList.ui.model.CatModel
import com.sergiodev.catbreeds.core.network.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatListViewModel @Inject constructor(private val interactor: ICatListInteractor) : ViewModel() {

    private val _catList = MutableLiveData<ApiResponse<List<CatModel>>>()
    val catList: LiveData<ApiResponse<List<CatModel>>> get() = _catList

    fun getCats() {
        viewModelScope.launch {
            interactor.getCats().collect {
                _catList.value = it


            }
        }
    }

    fun filterCatsList(list: List<CatModel>, filter: String): List<CatModel> {

        if (filter.isEmpty())
            return list

        return list.filter { x -> x.name.uppercase().contains(filter.uppercase()) }
    }

}