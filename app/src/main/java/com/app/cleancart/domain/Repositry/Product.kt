package com.app.cleancart.domain.Repositry

import com.app.cleancart.domain.Modal.DataResponse
import com.app.cleancart.util.ProductResources
import kotlinx.coroutines.flow.Flow

interface Product {

    fun getProduct() : Flow<ProductResources<DataResponse>>
}