package com.example.Project.Application


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.Project.*
import com.example.Project.Feature.Favorite.Presentation.UI.Fragment.FragmentMainFavorite
import com.example.Project.Feature.Home.Presentation.UI.Fragment.FragmentMainHome
import com.example.Project.Feature.Search.Presentation.UI.Fragment.FragmentMainSearch
import com.example.Project.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //region Properties
    private lateinit var binding: ActivityMainBinding
    //endregion

    //region life cycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialBinding()
    }
    //endregion

    //region methods
    private fun initialBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigation()
        supportFragmentManager.beginTransaction()
            .replace(binding.mainFragment.id, FragmentMainHome())
            .commit()
    }

    private fun setupBottomNavigation() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.mainFragment.id, FragmentMainHome())
                        .commit()
                    true
                }
                R.id.search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.mainFragment.id, FragmentMainSearch())
                        .commit()
                    true
                }
                R.id.favorite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.mainFragment.id, FragmentMainFavorite())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }


    //endregion

}