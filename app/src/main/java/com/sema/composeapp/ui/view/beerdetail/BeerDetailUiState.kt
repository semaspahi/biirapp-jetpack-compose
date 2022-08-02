package com.sema.composeapp.ui.view.beerdetail

import com.sema.base.data.model.Beer

data class BeerDetailUiState(
    val success: Beer? = null,
    val error: String? = null,
    val loading: Boolean = false
)