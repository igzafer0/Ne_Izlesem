package com.igzafer.neizlesem.presentation.view.page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.igzafer.neizlesem.R
import com.igzafer.neizlesem.databinding.FragmentSearchPageBinding
import com.igzafer.neizlesem.domain.model.MultiSearchModel
import com.igzafer.neizlesem.presentation.adapter.SearchAdapter

import com.igzafer.neizlesem.presentation.view_model.SearchPageViewModel
import com.igzafer.neizlesem.util.ItemClickListener
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class SearchPage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchPageBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    private lateinit var binding: FragmentSearchPageBinding
    lateinit var viewModel: SearchPageViewModel
    private lateinit var searchAdapter: SearchAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SearchPageViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.searchQuery.observe(viewLifecycleOwner) {
            getData(it)
        }

        binding.removeEt.setOnClickListener {
            binding.searchEt.setText("")
        }
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        setAdapter()
        setRecy()

    }

    private fun getData(query: String) {
        lifecycleScope.launch {
            viewModel.searchData(query).collectLatest {
                searchAdapter.submitData(it)
            }
        }
    }

    private fun setRecy() {
        binding.searchRw.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = searchAdapter
        }
    }

    private fun setAdapter() {
        searchAdapter = SearchAdapter(ItemClickListener {
            it as MultiSearchModel
            val bundle = Bundle().apply {
                if (it.mediaType == "movie") {
                    putInt("movieId", it.id)
                } else {
                    putInt("personId", it.id)
                }
            }
            if (it.mediaType == "movie") {
                findNavController().navigate(
                    R.id.action_searchPage_to_movieDetailsFragment,
                    bundle
                )
            } else {
                findNavController().navigate(
                    R.id.action_searchPage_to_personDetailsFragment,
                    bundle
                )
            }
        })
    }
}