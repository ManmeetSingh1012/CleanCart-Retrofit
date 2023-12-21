package com.app.cleancart.data.repositry

import com.app.cleancart.domain.Modal.DataResponse
import com.app.cleancart.data.remote.GetProductsApi
import com.app.cleancart.domain.Repositry.Product
import com.app.cleancart.util.ProductResources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


/** calling the api and passing the data**/

class ProductImpl(val api: GetProductsApi):Product {

    override fun getProduct(): Flow<ProductResources<DataResponse>> = flow{

        emit(ProductResources.Loading<DataResponse>())

        try {

            val result = api.getproducts()
            emit(ProductResources.Sucess<DataResponse>(result))
            
        }catch (t: IOException)
        {


            emit(ProductResources.Error<DataResponse>("Error occured ${t.message.toString()}"))

        }catch (t: HttpException)
        {

            emit(ProductResources.Error<DataResponse>("Error Occurred ${t.message.toString()}"))
        }

    }
}