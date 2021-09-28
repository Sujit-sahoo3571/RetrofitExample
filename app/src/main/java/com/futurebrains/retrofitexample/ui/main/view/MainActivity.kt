package com.futurebrains.retrofitexample.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.futurebrains.retrofitexample.data.api.RetrofitBuilder
import com.futurebrains.retrofitexample.data.model.User
import com.futurebrains.retrofitexample.databinding.ActivityMainBinding
import com.futurebrains.retrofitexample.ui.main.adapter.MainAdapter
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException



const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var useradapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpwithRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressbar.isVisible = true

            val response : Response<List<User>> = try {
                RetrofitBuilder.api.getUsers()
            } catch (e: IOException) {
                Log.e(TAG, "IOException , You Might not have Internet Connections ")
                binding.progressbar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException , Error Loading ... ")
                binding.progressbar.isVisible = false
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null ){
                useradapter.users =response.body()!!

            }
            else
                Log.e(TAG, "HttpException , Error Loading ... ")
                binding.progressbar.isVisible = false
        }

    }

    private fun setUpwithRecyclerView() = binding.rvHome.apply {
        useradapter = MainAdapter()
        adapter = useradapter
        layoutManager = LinearLayoutManager(this@MainActivity)
        setHasFixedSize(true)

    }

}