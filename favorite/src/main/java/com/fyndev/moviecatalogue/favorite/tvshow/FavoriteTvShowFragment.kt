package com.fyndev.moviecatalogue.favorite.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fyndev.moviecatalogue.core.domain.model.TvShow
import com.fyndev.moviecatalogue.detail.DetailFragment
import com.fyndev.moviecatalogue.favorite.FavoriteFragmentDirections
import com.fyndev.moviecatalogue.favorite.R
import com.fyndev.moviecatalogue.favorite.databinding.FragmentFavoriteTvShowBinding
import com.fyndev.moviecatalogue.favorite.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class FavoriteTvShowFragment : Fragment() {

    private val binding: FragmentFavoriteTvShowBinding by viewBinding()
    private val viewModel: FavoriteTvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        val favoriteTvShowAdapter = FavoriteTvShowAdapter()

        viewModel.getFavoriteTvShow().observe(viewLifecycleOwner, { favTvShow ->
            if (favTvShow != null && favTvShow.isNotEmpty()) {
                favoriteTvShowAdapter.setData(favTvShow)
                binding.tvNoData.visibility = View.GONE
            }
        })

        // setup recyclerview
        with(binding.rvFavoriteTvShow) {
            layoutManager = LinearLayoutManager(context)
            adapter = favoriteTvShowAdapter
            setHasFixedSize(true)
        }

        favoriteTvShowAdapter.setOnItemClickCallBack(object :
            FavoriteTvShowAdapter.OnItemClickCallBack {
            override fun onItemClicked(tvShow: TvShow) {
                val direction =
                    FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment2(
                        tvShow.id,
                        DetailFragment.TV_SHOW
                    )
                findNavController().navigate(direction)
            }
        })
    }

    override fun onStop() {
        super.onStop()
        unloadKoinModules(favoriteModule)
    }
}