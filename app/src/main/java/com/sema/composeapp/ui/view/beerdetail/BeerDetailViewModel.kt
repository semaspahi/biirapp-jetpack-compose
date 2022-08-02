package com.sema.composeapp.ui.view.beerdetail

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.compose.runtime.mutableStateMapOf
import androidx.databinding.BindingAdapter
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sema.base.common.Resource
import com.sema.base.data.model.Beer
import com.sema.composeapp.R
import com.sema.composeapp.ui.view.beer.BeerListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerDetailViewModel @Inject constructor(
    private val repository: BeerDetailRepository,
    stateHandle: SavedStateHandle,
) : ViewModel() {

    private var _beerDetailFlow = MutableStateFlow(BeerDetailUiState())
    val beerDetailFlow: StateFlow<BeerDetailUiState> = _beerDetailFlow

    init {
        val beerId = stateHandle.get<String>("beerId")
            ?: throw IllegalStateException("No beerId was passed to destination.")
        getBeer(beerId.toInt())
    }

    private fun getBeer(id: Int) {
        viewModelScope.launch {
            _beerDetailFlow.value = BeerDetailUiState(loading = true)
            repository.getBeer(id)
                .catch {
                    _beerDetailFlow.value =
                        BeerDetailUiState(error = it.localizedMessage)
                }
                .collect { beerList ->
                    beerList.data?.first().let {
                        _beerDetailFlow.value = BeerDetailUiState(success = it)
                    }
                }
        }
    }

    fun showIbu(ibu: Double?): Int {
        ibu?.let {
            val ibuRes = when {
                ibu <= 20 -> R.string.beer_detail_smooth
                ibu <= 50 && ibu > 20 -> R.string.beer_detail_bitter
                ibu >= 50 -> R.string.beer_detail_hipsterPlus
                else -> return@let
            }
            return ibuRes
        }
        return R.string.beer_detail_smooth
    }

}