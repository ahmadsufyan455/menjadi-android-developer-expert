package com.fyndev.moviecatalogue.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fyndev.moviecatalogue.core.domain.model.Movie
import com.fyndev.moviecatalogue.detail.DetailFragment
import com.fyndev.moviecatalogue.favorite.FavoriteFragmentDirections
import com.fyndev.moviecatalogue.favorite.R
import com.fyndev.moviecatalogue.favorite.databinding.FragmentFavoriteMovieBinding
import com.fyndev.moviecatalogue.favorite.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class FavoriteMovieFragment : Fragment() {

    private val binding: FragmentFavoriteMovieBinding by viewBinding()
    private val viewModel: FavoriteMovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        val favoriteMovieAdapter = FavoriteMovieAdapter()

        viewModel.getFavoriteMovie().observe(viewLifecycleOwner, { favMovie ->
            if (favMovie != null && favMovie.isNotEmpty()) {
                favoriteMovieAdapter.setData(favMovie)
                binding.tvNoData.visibility = View.GONE
                binding.lottieEmpty.visibility = View.GONE
            }
        })

        // setup recyclerview
        with(binding.rvFavoriteMovie) {
            layoutManager = LinearLayoutManager(context)
            adapter = favoriteMovieAdapter
            setHasFixedSize(true)
        }

        // set navigate to detail
        favoriteMovieAdapter.setOnItemClickCallBack(object :
            FavoriteMovieAdapter.OnItemClickCallBack {
            override fun onItemClicked(movie: Movie) {
                val directions =
                    FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment2(
                        movie.id,
                        DetailFragment.MOVIE
                    )
                findNavController().navigate(directions)
            }
        })
    }

    override fun onStop() {
        super.onStop()
        unloadKoinModules(favoriteModule)
    }
}