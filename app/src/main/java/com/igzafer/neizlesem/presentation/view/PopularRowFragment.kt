package com.igzafer.neizlesem.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.igzafer.neizlesem.MainActivity
import com.igzafer.neizlesem.R
import com.igzafer.neizlesem.databinding.FragmentPopularRowBinding
import com.igzafer.neizlesem.presentation.adapter.NowPlayingMoviesRowAdapter
import com.igzafer.neizlesem.presentation.adapter.PopularMoviesRowAdapter
import com.igzafer.neizlesem.presentation.view_model.PopularMoviesViewModel
import kotlinx.coroutines.flow.collectLatest


class PopularRowFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_row, container, false)
    }
    private lateinit var viewModel: PopularMoviesViewModel
    private lateinit var recyAdapter: PopularMoviesRowAdapter
    private lateinit var binding: FragmentPopularRowBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPopularRowBinding.bind(view)
        viewModel = (activity as MainActivity).popularMoviesViewModel
        recyAdapter = (activity as MainActivity).recyAdapterPopular
        initRecy()
        initData()

    }

    private fun initData() {
        lifecycleScope.launchWhenCreated {
            viewModel.getPopularMoviesList().collectLatest {
                recyAdapter.submitData(it)
            }
        }
    }

    private fun initRecy() {
        binding.rowRw.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = recyAdapter
        }
    }
}