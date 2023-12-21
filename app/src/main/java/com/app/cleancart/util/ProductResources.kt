package com.app.cleancart.util


/** this we will use to give  pass the reuslt that we will be getting from api using sealed class**/
sealed class ProductResources<T>( val data:T?=null,val message:String?=null)
{
    // errror : When something went wrong , Loading : When data is loading , Sucess : When we get the data
    class Error<T>(message: String,data: T?= null):ProductResources<T>(data,message)
    class Loading<T>():ProductResources<T>()
    class Sucess<T>(data:T):ProductResources<T>(data)
}
