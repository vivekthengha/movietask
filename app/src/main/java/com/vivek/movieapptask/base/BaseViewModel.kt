package com.vivek.movieapptask.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel(){
    val error: MutableLiveData<Error> = MutableLiveData()
}