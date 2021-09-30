package com.soten.sotenshopclient.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.soten.sotenshopclient.R
import com.soten.sotenshopclient.databinding.ActivityMainBinding
import com.soten.sotenshopclient.databinding.ItemBannerBinding
import com.soten.sotenshopclient.databinding.ItemPhotoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.navigationCategoryFragment -> hideNavView()
                R.id.navigationSignInAndSignUpFragment -> hideNavView()
                R.id.navigationSettingFragment -> hideNavView()
                else -> {
                    binding.navView.visibility = View.VISIBLE
                }
            }
        }

    }

    private fun hideNavView() {
        binding.navView.visibility = View.GONE
    }

}