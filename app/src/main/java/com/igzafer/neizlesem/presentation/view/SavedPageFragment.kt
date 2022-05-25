package com.igzafer.neizlesem.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.igzafer.neizlesem.MainActivity
import com.igzafer.neizlesem.R
import com.igzafer.neizlesem.databinding.FragmentSavedPageBinding
import com.igzafer.neizlesem.databinding.FragmentSearchBinding
import com.igzafer.neizlesem.presentation.adapter.Movie.SavedMoviesAdapter
import com.igzafer.neizlesem.presentation.adapter.Movie.SearchMovieAdapter
import com.igzafer.neizlesem.presentation.view_model.saved_fragment.SavedPageFragmentViewModel
import com.igzafer.neizlesem.presentation.view_model.search_fragment.SearchFragmentViewModel


class SavedPageFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_page, container, false)
    }
    private lateinit var binding: FragmentSavedPageBinding
    lateinit var viewModel: SavedPageFragmentViewModel
    private lateinit var savedMovieAdapter: SavedMoviesAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedPageBinding.bind(view)
        viewModel = (activity as MainActivity).savedPageFragmentViewModel
        savedMovieAdapter = (activity as MainActivity).recyAdapterSavedMoviesAdapter
        initRecys()
        getData()
        savedMovieAdapter.setOnClickItemListener {
            val bundle = Bundle().apply {
                putSerializable("MovieModel", it)
            }
            findNavController().navigate(R.id.action_savedPageFragment_to_movieDetailsFragment,bundle)
        }
    }

    private fun getData() {
        viewModel.getSavedMovies().observe(viewLifecycleOwner) {
            savedMovieAdapter.differ.submitList(it)
        }
    }

    private fun initRecys() {
        binding.savedRw.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = savedMovieAdapter
        }

    }
}