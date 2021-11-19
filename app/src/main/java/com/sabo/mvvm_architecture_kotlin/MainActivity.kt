package com.sabo.mvvm_architecture_kotlin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sabo.mvvm_architecture_kotlin.adapter.NoteAdapter
import com.sabo.mvvm_architecture_kotlin.databinding.ActivityMainBinding
import com.sabo.mvvm_architecture_kotlin.models.Note

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.rvItem.layoutManager = LinearLayoutManager(this)
        observeData()

        binding.btnSubmit.setOnClickListener {
            addData()
        }
    }

    private fun addData() {
        val title = binding.etTitle.text.toString()
        if (title.isEmpty()) return Toast.makeText(this, "Required!", Toast.LENGTH_SHORT).show()

        viewModel.add(Note(title))
        binding.etTitle.text.clear()
        binding.rvItem.adapter!!.notifyDataSetChanged()
    }

    private fun observeData() {
        viewModel.mutableLive.observe(this, {
            Log.d("data-recycler-view", it.toString())
            binding.rvItem.adapter = NoteAdapter( viewModel, it)
        })
    }
}