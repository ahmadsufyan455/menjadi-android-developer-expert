package com.fyndev.moviecatalogue.home.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fyndev.moviecatalogue.R
import com.fyndev.moviecatalogue.core.data.Resource
import com.fyndev.moviecatalogue.core.domain.model.TvShow
import com.fyndev.moviecatalogue.databinding.FragmentTvBinding
import com.fyndev.moviecatalogue.detail.DetailFragment
import org.koin.android.viewmodel.ext.android.viewModel

class TvFragment : Fragment() {

    private val binding: FragmentTvBinding by viewBinding()
    private val viewModel: TvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvShowAdapter = TvShowAdapter()

        viewModel.getTvShow().observe(viewLifecycleOwner, { tvShow ->
            if (tvShow != null) {
                when (tvShow) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        tvShow.data?.let { tvShowAdapter.setData(it) }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        with(binding.rvTvShow) {
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }

        tvShowAdapter.setOnItemClickCallBack(object : TvShowAdapter.OnItemClickCallBack {
            override fun onItemClicked(tvShow: TvShow) {
                val direction = TvFragmentDirections.actionTvFragmentToDetailFragment(
                    tvShow.id,
                    DetailFragment.TV_SHOW
                )
                findNavController().navigate(direction)
            }
        })
    }
}