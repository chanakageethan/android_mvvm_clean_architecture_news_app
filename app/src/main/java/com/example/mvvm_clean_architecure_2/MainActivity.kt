package com.example.mvvm_clean_architecure_2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.mvvm_clean_architecure_2.databinding.ActivityMainBinding
import com.example.mvvm_clean_architecure_2.presentation.adapter.NewsAdapter
import com.example.mvvm_clean_architecure_2.presentation.viewmodel.NewsViewModel
import com.example.mvvm_clean_architecure_2.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: NewsViewModelFactory

    @Inject
    lateinit var newsAdapter: NewsAdapter

    lateinit var viewModel : NewsViewModel
    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController


        binding.bnvNews.setupWithNavController(
            navController
        )

        viewModel = ViewModelProvider(this,factory)
            .get(NewsViewModel::class.java)



    }
}