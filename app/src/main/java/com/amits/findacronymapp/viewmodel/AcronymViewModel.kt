package com.amits.findacronymapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amits.findacronymapp.models.Acronym
import com.amits.findacronymapp.repository.MainRepository
import kotlinx.coroutines.*

class AcronymViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val meaningList = MutableLiveData<List<Acronym>>()
    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("We have exception: ${throwable.localizedMessage}")
    }

    fun getAcronymMeanings(searchInput: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getAcronymMeanings(searchInput)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    meaningList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}
