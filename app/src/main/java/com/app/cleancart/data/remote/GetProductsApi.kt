package com.app.cleancart.data.remote

import com.app.cleancart.domain.Modal.DataResponse
import retrofit2.http.GET

// Interface to make api calls
interface GetProductsApi {

    // get annotation will help to get the data
@GET("products")
suspend fun getproducts():DataResponse
}