package com.app.cleancart.di

import com.app.cleancart.data.repositry.ProductImpl
import com.app.cleancart.data.remote.GetProductsApi
import com.app.cleancart.domain.Repositry.Product
import com.app.cleancart.util.Const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/** it is our dependency injection collection providing the dependency for our api**/

/**
 * @Module marks Module as a module for dependency injection.
 * @InstallIn specifies where the module should be installed (in this case, the SingletonComponent).
 * @Provides indicates that the annotated function provides a dependency.
 * @Singleton ensures that there's only one instance of the provided dependency.




**/


@Module
@InstallIn(SingletonComponent::class)
object Network {

    @Provides
    @Singleton
    fun retrofit():Retrofit
    {
        return Retrofit.Builder()
            .baseUrl(Const.baseurl).addConverterFactory(GsonConverterFactory.create()).build()
    }


    @Provides
    @Singleton
    fun getproductclass(retrofit: Retrofit): GetProductsApi
    {
        return retrofit.create(GetProductsApi::class.java)
    }

    @Provides
    @Singleton
    fun getproduct(product: GetProductsApi): Product
    {
        return ProductImpl(product)
    }
}