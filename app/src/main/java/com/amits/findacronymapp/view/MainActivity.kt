package com.amits.findacronymapp.view

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.amits.findacronymapp.R
import com.amits.findacronymapp.adapter.AcronymAdapter
import com.amits.findacronymapp.adapter.AcronymVarsAdapter
import com.amits.findacronymapp.api.RetrofitService
import com.amits.findacronymapp.databinding.ActivityMainBinding
import com.amits.findacronymapp.repository.AcronymViewModelFactory
import com.amits.findacronymapp.repository.MainRepository
import com.amits.findacronymapp.utility.CheckInternetConnection
import com.amits.findacronymapp.utility.isValidInput
import com.amits.findacronymapp.utility.toast
import com.amits.findacronymapp.viewmodel.AcronymViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AcronymViewModel
    private val adapter = AcronymAdapter()
    private val adapterVars = AcronymVarsAdapter()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)
        binding.recyclerview.adapter = adapter
        binding.recyclerviewVars.adapter = adapterVars
        viewModel = ViewModelProvider(this,AcronymViewModelFactory(mainRepository)).get(AcronymViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.loading.value=false
        binding.searchKeyword.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(searchInput: String?): Boolean {
              val isValid =isValidInput(searchInput)
                if(!isValid){
                    toast(resources.getString(R.string.valid_input_message))
                    binding.recyclerview.visibility=View.GONE
                    binding.recyclerviewVars.visibility=View.GONE
                }else{
                    if(CheckInternetConnection.checkForInternet(this@MainActivity)){
                        binding.progressDialog.visibility = View.VISIBLE
                        if (searchInput != null) {
                            viewModel.getAcronymMeanings(searchInput)
                        }
                    }else{
                        toast(resources.getString(R.string.network_status))
                    }
                }
                return false
            }

            override fun onQueryTextChange(searchInput: String?): Boolean {
                if (searchInput?.isNotEmpty() == true && searchInput.isBlank()) {
                    binding.searchKeyword.setQuery("", false)
                }
                return true
            }
        })
        binding.searchKeyword.setOnCloseListener(object : android.widget.SearchView.OnCloseListener, SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                binding.searchKeyword.isIconified = false
                return true
            }

        })
        viewModel.meaningList.observe(this, {
            if(it.size>=1){
                binding.listError.visibility=View.GONE
                binding.recyclerview.visibility=View.VISIBLE
                binding.recyclerviewVars.visibility=View.VISIBLE
                adapter.setAcronymList(it,adapterVars)
            } else {
                binding.recyclerview.visibility=View.GONE
                binding.recyclerviewVars.visibility=View.GONE
                binding.listError.visibility=View.VISIBLE
            }
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

    }



}