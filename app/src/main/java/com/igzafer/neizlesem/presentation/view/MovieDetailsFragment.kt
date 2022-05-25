package com.igzafer.neizlesem.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.igzafer.neizlesem.BuildConfig
import com.igzafer.neizlesem.MainActivity
import com.igzafer.neizlesem.R
import com.igzafer.neizlesem.data.model.category.CategoryModel
import com.igzafer.neizlesem.data.model.movie.movie_details.BaseMovieDetailsModel
import com.igzafer.neizlesem.data.model.movie.movie_images.BaseMovieImagesModel
import com.igzafer.neizlesem.databinding.CategoryRowStyleBinding
import com.igzafer.neizlesem.databinding.FragmentMovieDetailsBinding
import com.igzafer.neizlesem.presentation.adapter.Actors.CastRowAdapter
import com.igzafer.neizlesem.presentation.adapter.SliderAdapter
import com.igzafer.neizlesem.presentation.view_model.details_fragment.MovieDetailsFragmentViewModel


class MovieDetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var castAdapter: CastRowAdapter
    lateinit var viewModel: MovieDetailsFragmentViewModel
    private var viewPager: ViewPager? = null
    private val args: MovieDetailsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailsBinding.bind(view)
        viewModel = (activity as MainActivity).movieDetailsFragmentViewModel
        castAdapter = (activity as MainActivity).recyAdapterGetCast
        viewModel
        initRecys()
        getData()
        viewPager = binding.viewPagerSlider
        lifecycleScope.launchWhenStarted {
            val x = viewModel.isExistMovie(args.movieModel.id!!)
            if (x == null) {
                isSaved = false
            } else {
                isSaved = true
                binding.saveButton.speed = 1f
                binding.saveButton.playAnimation()
            }
        }
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun getData() {
        lifecycleScope.launchWhenCreated {
            submitData(viewModel.getMovieDetails(args.movieModel.id!!))
        }
        lifecycleScope.launchWhenCreated {
            castAdapter.differ.submitList(viewModel.getCast(args.movieModel.id!!).cast)
        }
        lifecycleScope.launchWhenCreated {
            createSlider(viewModel.getMoviesImages(args.movieModel.id!!))
        }
    }

    private var size = 1

    @SuppressLint("SetTextI18n")
    private fun createSlider(moviesImages: BaseMovieImagesModel?) {
        if(moviesImages?.backdrops?.size==0){
            viewPager!!.adapter =
                SliderAdapter(requireContext(), null)
            size = 1
        }else{
            size=moviesImages!!.backdrops.size
            viewPager!!.adapter =
                SliderAdapter(requireContext(), moviesImages.backdrops)
        }

        binding.pageTw.text = "1 / $size"

    }

    var isSaved = false
    private fun submitData(it: BaseMovieDetailsModel) {
        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                Log.d("winter", position.toString())
                binding.pageTw.text = (position + 1).toString() + " / " + size.toString()
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
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
        binding.loadingLottie.cancelAnimation()
        binding.loadingLottie.visibility = View.GONE
        binding.saveButton.setOnClickListener {
            if (isSaved) {
                isSaved = false
                binding.saveButton.speed = -2f
                binding.saveButton.playAnimation()
                viewModel.deleteMovie(args.movieModel.id!!)


            } else {
                isSaved = true
                viewModel.saveMovie(args.movieModel)
                binding.saveButton.speed = 1f
                binding.saveButton.playAnimation()

            }

        }
        binding.releaseTw.text = it.releaseDate


    }

    private fun initRecys() {
        binding.castRw.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = castAdapter
        }

    }
}