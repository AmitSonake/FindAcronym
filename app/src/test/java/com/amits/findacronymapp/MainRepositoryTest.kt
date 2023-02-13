package com.amits.findacronymapp


import com.amits.findacronymapp.api.RetrofitService
import com.amits.findacronymapp.models.Acronym
import com.amits.findacronymapp.repository.MainRepository
import org.junit.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(JUnit4::class)
class MainRepositoryTest {

    lateinit var mainRepository: MainRepository

    @Mock
    lateinit var apiService: RetrofitService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainRepository = MainRepository(apiService)
    }

    @Test
    fun getAcronymMeanings() {
        runBlocking {
            Mockito.`when`(apiService.getAcronymMeanings("hmm")).thenReturn(Response.success(listOf<Acronym>()))
            val response = mainRepository.getAcronymMeanings("hmm")
            assertEquals(listOf<Acronym>(),  response.body())
        }

    }

}
