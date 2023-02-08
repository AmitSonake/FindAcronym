package com.amits.findacronymapp.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amits.findacronymapp.viewmodel.AcronymViewModel

class AcronymViewModelFactory (
    private val repository: MainRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AcronymViewModel::class.java)) {
            AcronymViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("Unable to find viewmodel ")
        }
    }
}