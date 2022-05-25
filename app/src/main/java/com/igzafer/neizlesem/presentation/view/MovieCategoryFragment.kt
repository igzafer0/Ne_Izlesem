package com.igzafer.neizlesem.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.igzafer.neizlesem.MainActivity
import com.igzafer.neizlesem.R
import com.igzafer.neizlesem.databinding.FragmentMovieCategoryBinding
import com.igzafer.neizlesem.presentation.adapter.Movie.DiscoverMoviesAdapter
import com.igzafer.neizlesem.presentation.view_model.category_fragment.MovieCategoryFragmentViewModel
import kotlinx.coroutines.flow.collectLatest


class MovieCategoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_category, container, false)
    }

    private lateinit var binding: FragmentMovieCategoryBinding
    lateinit var viewModel: MovieCategoryFragmentViewModel
    private lateinit var discoverMoviesAdapter: DiscoverMoviesAdapter
    private val args: MovieCategoryFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieCategoryBinding.bind(view)
        //Log.d("winter",args.categoryModel.name+"csd")
        binding.titleTw.text = args.categoryModel.name + " \nKategorisindeki\nFilmler"
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        viewModel = (activity as MainActivity).movieCategoryFragmentViewModel
        discoverMoviesAdapter = (activity as MainActivity).recyAdapterDiscoverMoviesAdapter
        getDatas()
        initRecys()
        discoverMoviesAdapter.setOnClickItemListener {
            val bundle = Bundle().apply {
                putSerializable("MovieModel", it)
            }
           findNavController().navigate(R.id.action_movieCategoryFragment_to_movieDetailsFragment,bundle)
        }
        discoverMoviesAdapter.addOnPagesUpdatedListener {
            binding.loadingLottie.cancelAnimation()
            binding.loadingLottie.visibility=View.GONE
        }
    }

    private fun getDatas() {
        lifecycleScope.launchWhenCreated {
            viewModel.discoverMovies(args.categoryModel.id).collectLatest {
                discoverMoviesAdapter.submitData(it)
            }
        }

    }

    private fun initRecys() {
        binding.discoverMoviesRw.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = discoverMoviesAdapter

        }


    }
}