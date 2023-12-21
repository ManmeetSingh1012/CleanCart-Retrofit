package com.app.cleancart.data.repositry


import com.app.cleancart.domain.Modal.DataResponse
import com.app.cleancart.domain.Repositry.Product
import com.app.cleancart.util.ProductResources
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/** here we are injecting the dependency**/

class Repositry @Inject constructor(
    val product: Product
) {

    fun getproducts(): Flow<ProductResources<DataResponse>>
    {
        return product.getProduct()
    }
}