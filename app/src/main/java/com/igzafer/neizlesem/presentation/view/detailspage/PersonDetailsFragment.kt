package com.igzafer.neizlesem.presentation.view.detailspage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.igzafer.neizlesem.R
import com.igzafer.neizlesem.databinding.FragmentPersonDetailsBinding
import com.igzafer.neizlesem.domain.model.MovieCreditsModel
import com.igzafer.neizlesem.presentation.adapter.CreditsMovieAdapter
import com.igzafer.neizlesem.presentation.view_model.PersonDetailsViewModel
import com.igzafer.neizlesem.util.ItemClickListener
import timber.log.Timber

class PersonDetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPersonDetailsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    private lateinit var binding: FragmentPersonDetailsBinding
    lateinit var viewModel: PersonDetailsViewModel
    private lateinit var creditsMovieAdapter: CreditsMovieAdapter

    private val args: PersonDetailsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[PersonDetailsViewModel::class.java]
        creditsMovieAdapter = CreditsMovieAdapter(ItemClickListener {
            it as MovieCreditsModel
            val bundle = Bundle().apply {
                putInt("movieId", it.id)
            }
            findNavController().navigate(
                R.id.action_personDetailsFragment_to_movieDetailsFragment,
                bundle
            )
        })
        binding.movieCreditsRw.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = creditsMovieAdapter
        }
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        getData()

    }

    fun getData() {
        lifecycleScope.launchWhenCreated {
            Timber.d(args.personId.toString())
            binding.personDetailsModel = viewModel.getPersonDetails(args.personId)
            creditsMovieAdapter.submitList(viewModel.getPersonMovieCredits(args.personId))
        }
    }
}