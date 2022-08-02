package com.sema.composeapp.ui.view.beer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sema.base.common.Resource
import com.sema.base.data.model.Beer
import com.sema.composeapp.ui.view.beerdetail.BeerDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(
    private val repository: BeerRepository
) : ViewModel() {

    var page = 1

    private var _beerListFlow = MutableStateFlow(BeerListUiState())
    val beerListFlow: StateFlow<BeerListUiState> = _beerListFlow

    init {
        getBeers()
    }

    private fun getBeers() {
        resetPageId()
        _beerListFlow.value = BeerListUiState(loading = true)
        viewModelScope.launch {
            repository.getBeers()
                .catch {
                    _beerListFlow.value = BeerListUiState(error =  it.message.toString())
                }
                .collect {
                    _beerListFlow.value = BeerListUiState(success = it.data)
                    page += 1
                }
        }
    }

    fun getNextBeers(nextPage: Int? = null) {
        viewModelScope.launch {
            repository.getBeers(nextPage)
                .catch {
                    _beerListFlow.value = BeerListUiState(error =  it.message.toString())
                }
                .collect {
                    _beerListFlow.value = BeerListUiState(success = it.data)
                    page += 1
                }
        }
    }

    private fun resetPageId() {
        page = 1
    }

}