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
import com.igzafer.neizlesem.databinding.FragmentVizyondakilerRowBinding
import com.igzafer.neizlesem.presentation.adapter.NowPlayingMoviesRowAdapter
import com.igzafer.neizlesem.presentation.view_model.NowPlayingMoviesViewModel
import kotlinx.coroutines.flow.collectLatest


class VizyondakilerRowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vizyondakiler_row, container, false)
    }

    private lateinit var viewModel: NowPlayingMoviesViewModel
    private lateinit var recyAdapter: NowPlayingMoviesRowAdapter
    private lateinit var binding: FragmentVizyondakilerRowBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentVizyondakilerRowBinding.bind(view)
        viewModel = (activity as MainActivity).nowPlayingMoviesViewModel
        recyAdapter = (activity as MainActivity).recyAdapterNowPlaying
        initRecy()
        initData()
    }

    private fun initData() {
        lifecycleScope.launchWhenCreated {
            viewModel.getNowPlayingMoviesList().collectLatest {
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