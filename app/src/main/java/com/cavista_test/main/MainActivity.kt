package com.cavista_test.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cavista_test.R
import com.cavista_test.api.ApiHelper
import com.cavista_test.api.RetrofitBuilder
import com.cavista_test.api.Status
import com.cavista_test.databinding.ActivityMainBinding
import com.cavista_test.main.adapter.SearchAdapter
import com.cavista_test.main.data.SearchData
import com.cavista_test.main.data.SearchViewModel
import com.cavista_test.main.data.SearchViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var searchAdapter: SearchAdapter

    private val viewModel: SearchViewModel by viewModels {
        SearchViewModelFactory(
                ApiHelper(RetrofitBuilder.apiService)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupUI()
        showKeyBoard()
    }

    private fun setupUI() {

        // Show All Images
        showData()
        binding.etxSearch.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyBoard()
                val searchText = binding.etxSearch.text.toString()
                if (searchText != "") {
                    setupObservers(searchText)
                } else {
                    binding.etxSearch.error = "Please enter valid text"
                }
                return@OnEditorActionListener true
            }
            false
        })

        binding.imgSearch.setOnClickListener {
            hideKeyBoard()
            val searchText = binding.etxSearch.text.toString()

            if (searchText != "") {

                // Get Image Data
                setupObservers(searchText)
            } else {
                binding.etxSearch.error = "Please enter valid text"
            }
        }
    }

    private fun showData() {

        // Recyler view to show Images
        val layoutManager = GridLayoutManager(this, 4, RecyclerView.VERTICAL, false)
        binding.rvImageSearch.layoutManager = layoutManager
        searchAdapter = SearchAdapter()
        binding.rvImageSearch.adapter = searchAdapter
    }

    private fun setupObservers(searchText: String) {
        viewModel.getSearchData(searchText).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.rvImageSearch.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        binding.tvMsg.visibility = View.GONE
                        binding.networkError.visibility = View.GONE
                        binding.otherApiError.visibility = View.GONE
                        resource.data?.let { response -> retrieveList(response) }
                    }
                    Status.ERROR -> {
                        binding.rvImageSearch.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        binding.tvMsg.visibility = View.GONE
                        binding.networkError.visibility = View.GONE
                        binding.otherApiError.visibility = View.VISIBLE
                        val btTryAgain = binding.otherApiError.findViewById(R.id.bt_try_again) as Button
                        btTryAgain.setOnClickListener {
                            setupObservers(searchText)
                        }
                    }
                    Status.NETWORK_ERROR -> {
                        binding.rvImageSearch.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        binding.tvMsg.visibility = View.GONE
                        binding.networkError.visibility = View.VISIBLE
                        binding.otherApiError.visibility = View.GONE
                        val btTryAgain = binding.networkError.findViewById(R.id.bt_try_again_network) as Button
                        btTryAgain.setOnClickListener {
                            setupObservers(searchText)
                        }
                    }
                    Status.LOADING -> {
                        binding.rvImageSearch.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                        binding.tvMsg.visibility = View.GONE
                        binding.networkError.visibility = View.GONE
                        binding.otherApiError.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(response: SearchData) {
        searchAdapter.submitList(response.data)
    }

    private fun showKeyBoard() {
        Handler().post {
            val inputMethodManager =
                    this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInput(
                    InputMethodManager.SHOW_FORCED,
                    0
            )
            binding.etxSearch.requestFocus()
        }
    }

    private fun hideKeyBoard() {
        val imm =
                this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = this.currentFocus
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}