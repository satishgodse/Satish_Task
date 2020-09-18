package com.cavista_test.details

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cavista_test.R
import com.cavista_test.database.AppDatabase
import com.cavista_test.database.Comment
import com.cavista_test.databinding.ActivityImageDetailsBinding
import com.cavista_test.details.adapter.CommentsAdapter
import com.cavista_test.details.adapter.ImageAdapter
import com.cavista_test.details.data.ImageDetailsViewModel
import com.cavista_test.details.data.ImageViewModelFactory
import com.cavista_test.main.data.Data
import java.text.SimpleDateFormat
import java.util.*

class ImageDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageDetailsBinding
    private lateinit var imageAdapter: ImageAdapter
    private lateinit var commentsAdapter: CommentsAdapter
    private var dataItem: Data? = null

    private val viewModel: ImageDetailsViewModel by viewModels {
        ImageViewModelFactory(
                AppDatabase.getInstance(this)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_details)

        // Get Data Item Passed from Image List Screen
        dataItem = intent.getParcelableExtra("dataItem")
        setupUI()
    }

    @SuppressLint("SimpleDateFormat")
    private fun setupUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Show Image Details
        showDetails()

        // Show List of Comments
        showComments()

        binding.btnSubmit.setOnClickListener {
            hideKeyBoard()
            val commentText = binding.etxComment.text.toString()
            if (commentText.isNotEmpty()) {

                // Get Current Date
                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")
                val currentDate = sdf.format(Date())

                // Insert Comment in DB when dataItem is Not Null
                dataItem?.let {
                    val comment = Comment(
                            postId = it.id,
                            comment = binding.etxComment.text.toString(),
                            time = currentDate
                    )
                    viewModel.insertUpdateComments(comment)
                    binding.etxComment.text.clear()
                    Toast.makeText(this, "Added Successfully!!", Toast.LENGTH_SHORT).show()
                }
            } else {
                binding.etxComment.error = "Please enter comment"
            }
        }
    }

    private fun showDetails() {

        // Recyler View For Showing Images
        val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.rvImageDetail.layoutManager = layoutManager
        binding.rvImageDetail.isNestedScrollingEnabled = true
        imageAdapter = ImageAdapter()
        binding.rvImageDetail.adapter = imageAdapter
        imageAdapter.submitList(dataItem?.images)
    }

    private fun showComments() {

        dataItem?.let {
            val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            binding.rvComments.layoutManager = layoutManager
            binding.rvImageDetail.isNestedScrollingEnabled = true
            commentsAdapter = CommentsAdapter()
            binding.rvComments.adapter = commentsAdapter
        }

        // Get Comments From DB
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getCommentsByPostId(dataItem?.id).observe(this, androidx.lifecycle.Observer {
            it.let { resource ->
                val list = mutableListOf<Comment>()
                list.addAll(resource)
                commentsAdapter.submitList(list)
                if (list.isEmpty()) {
                    binding.rvComments.visibility = View.GONE
                    binding.tvMsgComments.visibility = View.VISIBLE
                    binding.tvMsgComments.text = getString(R.string.comment_empty_msg)
                } else {
                    binding.tvMsgComments.visibility = View.GONE
                    binding.rvComments.visibility = View.VISIBLE
                }
            }
        })
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)

    }
}