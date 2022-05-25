package com.igzafer.neizlesem.presentation.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieDrawable
import com.igzafer.neizlesem.MainActivity
import com.igzafer.neizlesem.R
import com.igzafer.neizlesem.databinding.FragmentSearchBinding
import com.igzafer.neizlesem.presentation.adapter.Movie.SearchMovieAdapter
import com.igzafer.neizlesem.presentation.view_model.search_fragment.SearchFragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*


class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    private lateinit var binding: FragmentSearchBinding
    lateinit var viewModel: SearchFragmentViewModel
    private lateinit var searchMovieAdapter: SearchMovieAdapter
    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        viewModel = (activity as MainActivity).searchFragmentViewModel
        searchMovieAdapter = (activity as MainActivity).recyAdapterSearchMovieAdapter
        searchMovieAdapter.setOnClickItemListener {
            val bundle = Bundle().apply {
                putSerializable("MovieModel", it)
            }
            findNavController().navigate(R.id.action_searchFragment_to_movieDetailsFragment, bundle)
        }
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.removeEt.setOnClickListener {
            binding.searchEt.setText("")
        }
        initRecys()
        binding.searchEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            private var timer: Timer = Timer()
            private val DELAY: Long = 500 // Milliseconds
            override fun afterTextChanged(p0: Editable?) {
                lifecycleScope.launch(Dispatchers.Main){
                    binding.loadingLottie.visibility=View.VISIBLE
                }
                timer.cancel()
                timer = Timer()
                timer.schedule(
                    object : TimerTask() {
                        override fun run() {
                            if (binding.searchEt.text.toString() != "") {
                                getDatas(binding.searchEt.text.toString())
                            }else{
                                lifecycleScope.launch(Dispatchers.Main){
                                    binding.loadingLottie.visibility=View.GONE
                                }
                            }

                        }
                    },
                    DELAY
                )
            }
        })
        searchMovieAdapter.addOnPagesUpdatedListener {
            if (searchMovieAdapter.itemCount == 0) {
                binding.notFoundLl.visibility = View.VISIBLE
                binding.notFound.playAnimation()
                binding.notFound.repeatMode = LottieDrawable.RESTART
                binding.loadingLottie.visibility=View.GONE


            } else {
                binding.notFoundLl.visibility = View.GONE
                binding.notFound.cancelAnimation()
                binding.loadingLottie.visibility=View.GONE


            }
        }

        binding.searchRw.setOnTouchListener { _, _ ->
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun getDatas(q: String) {

        lifecycleScope.launchWhenCreated {
            viewModel.searchMovie(q).collectLatest {
                searchMovieAdapter.submitData(it)

            }
        }

    }

    private fun initRecys() {
        binding.searchRw.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = searchMovieAdapter
        }

    }

}