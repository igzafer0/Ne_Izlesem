package com.igzafer.neizlesem.presentation.view.tabview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.igzafer.neizlesem.R
import com.igzafer.neizlesem.databinding.FragmentHomePageBinding
import com.igzafer.neizlesem.databinding.FragmentSavedPageBinding
import com.igzafer.neizlesem.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)

        return binding.root
    }
    private lateinit var binding: FragmentSearchBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchPageButton.setOnClickListener{
            findNavController().navigate(R.id.action_searchFragment_to_searchPage)
        }

    }
}