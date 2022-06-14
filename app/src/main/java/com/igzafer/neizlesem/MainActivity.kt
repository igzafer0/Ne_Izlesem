package com.igzafer.neizlesem

import android.os.Bundle
import android.view.Window
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.igzafer.neizlesem.databinding.ActivityMainBinding

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        initFragment()
        setFullScreen(window)
        lightStatusBar(window)
    }

    private fun setFullScreen(window: Window) {
        WindowCompat.setDecorFitsSystemWindows(window, false)

    }

    private fun lightStatusBar(window: Window, isLight: Boolean = false) {
        val wic = WindowInsetsControllerCompat(window, window.decorView)
        wic.isAppearanceLightStatusBars = isLight
        wic.isAppearanceLightNavigationBars = isLight
    }
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private fun initFragment() {
        val popupMenu = PopupMenu(this, null)
        popupMenu.inflate(R.menu.main_nav)
        navHostFragment = (supportFragmentManager
            .findFragmentById(R.id.viewPager) as NavHostFragment?)!!
        navController = navHostFragment.navController
        val menu = popupMenu.menu
        binding.tabLayout.setupWithNavController(menu, navController)

    }



}




