package com.fyndev.moviecatalogue.home.movie

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
import com.fyndev.moviecatalogue.core.domain.model.Movie
import com.fyndev.moviecatalogue.databinding.FragmentMovieBinding
import com.fyndev.moviecatalogue.detail.DetailFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private val binding: FragmentMovieBinding by viewBinding()
    private val viewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieAdapter = MovieAdapter()

        viewModel.getDataMovie().observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        movies.data?.let { movieAdapter.setData(it) }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })


        // setup recyclerview
        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        // set navigate to detail
        movieAdapter.setOnItemClickCallBack(object : MovieAdapter.OnItemClickCallBack {
            override fun onItemClicked(movie: Movie) {
                val directions =
                    MovieFragmentDirections.actionMovieFragmentToDetailFragment(
                        movie.id,
                        DetailFragment.MOVIE
                    )
                findNavController().navigate(directions)
            }
        })
    }
}