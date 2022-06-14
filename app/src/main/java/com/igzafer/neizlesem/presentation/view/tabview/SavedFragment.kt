package com.igzafer.neizlesem.presentation.view.tabview

import android.content.Context
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
import com.igzafer.neizlesem.databinding.FragmentSavedPageBinding
import com.igzafer.neizlesem.domain.model.MovieDetailsModel
import com.igzafer.neizlesem.presentation.adapter.SavedMoviesAdapter
import com.igzafer.neizlesem.presentation.view_model.SavedMovieViewModel
import com.igzafer.neizlesem.util.ItemClickListener
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class SavedFragment : Fragment() {

    private lateinit var binding: FragmentSavedPageBinding
    lateinit var viewModel: SavedMovieViewModel
    private lateinit var moviesAdapter: SavedMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedPageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(requireActivity())[SavedMovieViewModel::class.java]
        moviesAdapter = SavedMoviesAdapter(ItemClickListener {
            it as MovieDetailsModel
            val bundle = Bundle().apply {
                putInt("movieId", it.id)
            }
            findNavController().navigate(R.id.action_savedFragment_to_movieDetailsFragment, bundle)
        })
        getSavedMovies()
    }

    private fun getSavedMovies() {
        lifecycleScope.launch {
            viewModel.getMovies().collectLatest {
                moviesAdapter.submitList(it)

            }
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.savedRw.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = moviesAdapter
        }
    }
}