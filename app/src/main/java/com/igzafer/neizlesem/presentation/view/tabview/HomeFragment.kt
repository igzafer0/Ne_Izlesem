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
import com.igzafer.neizlesem.databinding.FragmentHomePageBinding
import com.igzafer.neizlesem.domain.model.HomeModel
import com.igzafer.neizlesem.domain.model.MoviesModel
import com.igzafer.neizlesem.presentation.adapter.HomePageAdapter
import com.igzafer.neizlesem.presentation.view_model.HomeViewModel
import com.igzafer.neizlesem.util.ItemClickListener
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        moviesAdapter = HomePageAdapter(ItemClickListener {
            it as HomeModel
            val bundle = Bundle().apply {
                putInt("movieId", it.moviesModel!!.id)
            }
            findNavController().navigate(R.id.action_homeFragment_to_movieDetailsFragment, bundle)
        })
        getMovies()
    }

    private lateinit var binding: FragmentHomePageBinding
    lateinit var viewModel: HomeViewModel
    private lateinit var moviesAdapter: HomePageAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.popularRw.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = moviesAdapter
        }

    }

    private fun getMovies() {
        lifecycleScope.launch {
            moviesAdapter.submitList(viewModel.getPopularMovies())

        }
    }

}