package com.app.cleancart.Presentation.Common

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.cleancart.data.repositry.Repositry
import com.app.cleancart.util.ProductResources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

// no need to have injection for the repositry . we do injection , but we create theam first
/** its the common view modal for all the screen this view modal provide the data to every single screen out there**/
@HiltViewModel
class CommonViewModal @Inject constructor(
    val repositry: Repositry
):ViewModel() {


    // used mutalbestateflow instead of live data so that every screen should get data from starting
    private val _product = MutableStateFlow(productmodal())
    val product : StateFlow<productmodal> = _product

    init {
        getresult()
    }


    // here we are getting the data from data layer
    private fun getresult()
    {
        repositry.getproducts().onEach { it ->

            when(it)
            {

                is ProductResources.Sucess -> {
                    _product.value = productmodal(product = it.data!!.products)
                    Log.d("product_", it.data!!.products.toString())
                }

                is ProductResources.Loading ->{
                    _product.value = productmodal(isloading = true)
                    Log.d("product_","loading")
                }

                is ProductResources.Error ->{
                    _product.value = productmodal(error = it.message ?: "Something went wrong")
                    Log.d("product_","${it.message.toString()}")
                }
            }

        }.launchIn(viewModelScope) // coroutines
    }
}