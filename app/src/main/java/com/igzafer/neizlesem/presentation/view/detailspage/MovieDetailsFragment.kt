package com.igzafer.neizlesem.presentation.view.detailspage

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.igzafer.neizlesem.R
import com.igzafer.neizlesem.databinding.FragmentMovieDetailsBinding
import com.igzafer.neizlesem.domain.model.CreditsModel
import com.igzafer.neizlesem.domain.model.MovieImagesModel
import com.igzafer.neizlesem.domain.model.MoviesModel
import com.igzafer.neizlesem.presentation.adapter.CreditsAdapter
import com.igzafer.neizlesem.presentation.adapter.RecommendedMoviesAdapter
import com.igzafer.neizlesem.presentation.adapter.SliderAdapter
import com.igzafer.neizlesem.presentation.view_model.MovieDetailViewModel
import com.igzafer.neizlesem.util.ItemClickListener
import kotlinx.coroutines.flow.collectLatest


class MovieDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    private lateinit var binding: FragmentMovieDetailsBinding
    lateinit var viewModel: MovieDetailViewModel
    private val args: MovieDetailsFragmentArgs by navArgs()
    private lateinit var creditsAdapter: CreditsAdapter
    private lateinit var recommendedMoviesAdapter: RecommendedMoviesAdapter
    private var viewPager: ViewPager? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MovieDetailViewModel::class.java]
        binding.viewModel = viewModel
        binding.backButton.setOnClickListener { findNavController().popBackStack() }
        viewPager = binding.viewPagerSlider
        viewModel.isSaved.observe(viewLifecycleOwner) {
            if (it) {
                binding.saveButton.speed = 1f
                binding.saveButton.playAnimation()
            } else {
                binding.saveButton.speed = -2f
                binding.saveButton.playAnimation()
            }
        }
        setAdapter()
        setRw()
        getData()
    }


    @SuppressLint("SetTextI18n")
    private fun createSlider(moviesImages: List<MovieImagesModel>?) {
        val size: Int
        if (moviesImages?.size == 0) {
            viewPager!!.adapter =
                SliderAdapter(requireContext(), null)
            size = 1
        } else {
            size = moviesImages!!.size
            viewPager!!.adapter =
                SliderAdapter(requireContext(), moviesImages)
        }

        binding.pageTw.text = "1 / $size"
        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {

                binding.pageTw.text = (position + 1).toString() + " / " + size.toString()
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }

    private fun setAdapter() {
        creditsAdapter = CreditsAdapter(ItemClickListener {
            it as CreditsModel
            val bundle = Bundle().apply {
                putInt("personId", it.id)
            }
            findNavController().navigate(
                R.id.action_movieDetailsFragment_to_personDetailsFragment,
                bundle
            )
        })
        recommendedMoviesAdapter = RecommendedMoviesAdapter(ItemClickListener {
            it as MoviesModel
            val bundle = Bundle().apply {
                putInt("movieId", it.id)
            }
            findNavController().navigate(R.id.action_movieDetailsFragment_self, bundle)
        })
    }

    private fun setRw() {
        binding.castRw.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = creditsAdapter
        }
        binding.similarRw.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = recommendedMoviesAdapter
        }
    }


    private fun getData() {
        lifecycleScope.launchWhenCreated {
            binding.movieDetailsModel = viewModel.getMovieDetail(args.movieId)
            creditsAdapter.submitList(viewModel.getCredits(args.movieId))
            createSlider(viewModel.getMovieImages(args.movieId))
            viewModel.getRecommendedMovies(args.movieId).collectLatest {
                recommendedMoviesAdapter.submitData(it)
            }
        }
    }
}