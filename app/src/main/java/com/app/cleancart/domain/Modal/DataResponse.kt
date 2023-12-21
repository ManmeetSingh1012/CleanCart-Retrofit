package com.app.cleancart.domain.Modal


/** it is the data class represent the output of the api**/
data class DataResponse(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)

