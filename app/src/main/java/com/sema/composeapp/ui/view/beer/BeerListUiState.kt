package com.sema.composeapp.ui.view.beer

import com.sema.base.data.model.Beer

data class BeerListUiState(
    val success: List<Beer>? = null,
    val error: String? = null,
    val loading: Boolean = false
)

