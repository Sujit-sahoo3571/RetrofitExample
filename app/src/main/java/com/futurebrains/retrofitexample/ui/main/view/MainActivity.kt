package com.futurebrains.retrofitexample.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.futurebrains.retrofitexample.data.api.RetrofitBuilder
import com.futurebrains.retrofitexample.data.model.User
import com.futurebrains.retrofitexample.data.repository.MainRepostiory
import com.futurebrains.retrofitexample.databinding.ActivityMainBinding
import com.futurebrains.retrofitexample.ui.main.adapter.MainAdapter
import com.futurebrains.retrofitexample.ui.main.viewmodel.MainViewModel
import com.futurebrains.retrofitexample.util.Status

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViwModel()
        setUpUI()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.getUser().observe(this, Observer {
            Log.i("TAG", "setUpObservers: ."+ it )
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.rvHome.visibility = View.VISIBLE
                        binding.progressbar.visibility = View.GONE

                        resource.data?.let { users -> retriveList(users) }

                    }
                    Status.ERROR -> {
                        binding.rvHome.visibility = View.VISIBLE
                        binding.progressbar.visibility = View.GONE
                        Toast.makeText(this, "Error 404 haha ! ", Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        binding.rvHome.visibility = View.GONE
                        binding.progressbar.visibility = View.VISIBLE


                    }
                }
            }
        })
    }

    private fun retriveList(users: List<User>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }

    }

    private fun setUpUI() {
        binding.rvHome.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        binding.rvHome.addItemDecoration(
            DividerItemDecoration(
                binding.rvHome.context,
                (binding.rvHome.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.rvHome.adapter = adapter
    }

    private fun setUpViwModel() {
        val repository = MainRepostiory(RetrofitBuilder.apiService)
        viewModel = ViewModelProvider(this, MainViewModel.MainViewModelFactory(repository)).get(MainViewModel::class.java)

    }

}