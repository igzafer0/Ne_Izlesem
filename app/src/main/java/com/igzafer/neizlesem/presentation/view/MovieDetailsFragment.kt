package com.igzafer.neizlesem.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.igzafer.neizlesem.MainActivity
import com.igzafer.neizlesem.R
import com.igzafer.neizlesem.data.model.category.CategoryModel
import com.igzafer.neizlesem.data.model.movie.movie_details.BaseMovieDetailsModel
import com.igzafer.neizlesem.databinding.CategoryRowStyleBinding
import com.igzafer.neizlesem.databinding.FragmentMovieDetailsBinding
import com.igzafer.neizlesem.presentation.view_model.MovieDetailsFragmentViewModel
import kotlinx.coroutines.flow.collectLatest


class MovieDetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    private lateinit var binding: FragmentMovieDetailsBinding
    lateinit var viewModel: MovieDetailsFragmentViewModel
    private val args: MovieDetailsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailsBinding.bind(view)
        viewModel = (activity as MainActivity).movieDetailsFragmentViewModel
        getData()
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun getData() {
        lifecycleScope.launchWhenCreated {
            viewModel.getMovieDetails(args.movieId).collectLatest {
                submitData(it)
            }
        }
    }

    var isSaved = false
    private fun submitData(it: BaseMovieDetailsModel) {
        if(it.backdropPath!=null){
            val posterPath = "https://image.tmdb.org/t/p/w500" + it.backdropPath
            Glide.with(binding.imPosterBig.context).load(posterPath)
                .into(binding.imPosterBig)
        }else{
            binding.imPosterBig.setImageResource(R.mipmap.ic_launcher)
        }

        binding.titleTw.text = it.title
        val x = binding.categoryLl
        for (i in 0 until it.genres.size) {
            val bindings: CategoryRowStyleBinding =
                CategoryRowStyleBinding.inflate(layoutInflater, x, false)
            bindings.categoryTw.text = it.genres[i].name
            bindings.idTw.text = it.genres[i].id.toString()
            bindings.root.setOnClickListener {
                val bundle = Bundle().apply {
                    putSerializable(
                        "CategoryModel",
                        CategoryModel(
                            id = bindings.idTw.text.toString().toInt(),
                            name = bindings.categoryTw.text.toString()
                        )
                    )
                }
                findNavController().navigate(
                    R.id.action_movieDetailsFragment_to_movieCategoryFragment,
                    bundle
                )
            }
            x.addView(bindings.root)
        }
        binding.descTw.text = it.overview
        binding.rateTw.text = it.voteAverage.toString()
        binding.saveButton.setOnClickListener {
            if (isSaved) {
                isSaved=false
                binding.saveButton.speed = -2f
                binding.saveButton.playAnimation()

            } else {
                isSaved=true
                binding.saveButton.speed = 1f
                binding.saveButton.playAnimation()

            }

        }
        binding.releaseTw.text=it.releaseDate


    }
}