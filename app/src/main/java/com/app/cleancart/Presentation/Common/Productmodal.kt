package com.app.cleancart.Presentation.Common

import com.app.cleancart.domain.Modal.Product

// this will help us to handle ouput in the view modal class
data class productmodal(
    val isloading: Boolean = false,
    val product: List<Product> = emptyList(),
    val error: String = ""
)
