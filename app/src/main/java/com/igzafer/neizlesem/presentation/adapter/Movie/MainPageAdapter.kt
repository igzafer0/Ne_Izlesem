package com.igzafer.neizlesem.presentation.adapter.Movie

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.igzafer.neizlesem.MainActivity
import com.igzafer.neizlesem.presentation.view.HomePageFragment
import dagger.hilt.android.ActivityRetainedLifecycle

@Suppress("DEPRECATION")
class MainPageAdapter( activity: AppCompatActivity): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->HomePageFragment()
            1->HomePageFragment()
            else-> HomePageFragment()
        }
    }
}