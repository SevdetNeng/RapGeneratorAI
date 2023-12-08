package com.okation.aivideocreator.presentation.home

import android.app.AlertDialog
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.okation.aivideocreator.R
import com.okation.aivideocreator.base.BaseFragment
import com.okation.aivideocreator.databinding.FragmentHomeBinding
import com.okation.aivideocreator.domain.model.local.SongEntity
import com.okation.aivideocreator.presentation.home.adapter.SongsAdapter
import com.okation.aivideocreator.util.PromptsItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val homeViewModel : HomeViewModel by viewModels()
    private val mediaPlayer : MediaPlayer = MediaPlayer()
    private val adapter = SongsAdapter(onMediaClick = {
        if(homeViewModel.isPlayingSong.value){
            if(homeViewModel.playingSong.value == it){
                stopSong()
            } else{
                playSong(it)
            }
        }else{
            playSong(it)
        }
    }, onMoreClick = {
        val alertBuilder = AlertDialog.Builder(requireContext())
        with(alertBuilder){
            val items = arrayOf("Delete")
            setTitle(it.songTitle)
            setItems(items){ dialog, which ->
                if(items[which] == "Delete"){
                    homeViewModel.deleteSong(it
                    )
                }
            }
            show()
        }
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.favoriteSongs.collect(){
                if(it.isEmpty()){
                    binding.premiumGroup.visibility = View.GONE
                    binding.freeGroup.visibility = View.VISIBLE
                } else {
                    binding.premiumGroup.visibility = View.VISIBLE
                    binding.freeGroup.visibility = View.GONE
                    adapter.submitList(it)
                }
            }
        }
        binding.homeAddButton.setOnClickListener {
            val sharedPreferences = requireActivity().getSharedPreferences("Status", Context.MODE_PRIVATE)
            with(sharedPreferences.edit()){
                if(sharedPreferences.getString("Status","Free")=="Free"){
                    if(sharedPreferences.getInt("Count",1)==0){
                        println(sharedPreferences.getString("Status","Free"))
                        println(sharedPreferences.getInt("Count",1))

                        findNavController().navigate(R.id.inAppFragment)
                    }else{
                        println(sharedPreferences.getString("Status","Free"))
                        println(sharedPreferences.getInt("Count",1))
                        findNavController().navigate(R.id.promptFragment)
                    }
                } else {
                    println(sharedPreferences.getString("Status","Free"))
                    println(sharedPreferences.getInt("Count",1))
                    findNavController().navigate(R.id.promptFragment)
                }
            }

        }
        binding.settingsButton.setOnClickListener {
            findNavController().navigate(R.id.settingsFragment)
        }
        binding.floatingActionButton.setOnClickListener {
            val sharedPreferences = requireActivity().getSharedPreferences("Status", Context.MODE_PRIVATE)
            with(sharedPreferences.edit()){
                if(sharedPreferences.getString("Status","Free")=="Free"){
                    if(sharedPreferences.getInt("Count",1)==0){
                        println(sharedPreferences.getString("Status","Free"))
                        println(sharedPreferences.getInt("Count",1))

                        findNavController().navigate(R.id.inAppFragment)
                    }else{
                        println(sharedPreferences.getString("Status","Free"))
                        println(sharedPreferences.getInt("Count",1))
                        findNavController().navigate(R.id.promptFragment)
                    }
                } else {
                    println(sharedPreferences.getString("Status","Free"))
                    println(sharedPreferences.getInt("Count",1))
                    findNavController().navigate(R.id.promptFragment)
                }
            }
        }
        setupSongsRecycler()
        mediaPlayer.setOnCompletionListener {
            stopSong()
            adapter.unselectItem()
        }
    }

    fun setupSongsRecycler(){
        val recyclerView = binding.songsRecycler
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = null
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        recyclerView.addItemDecoration(PromptsItemDecoration(2,16))
    }

    fun playSong(song : SongEntity){
        homeViewModel.setPlayingSong(song)
        homeViewModel.setIsPlaying(true)
        mediaPlayer.reset()
        mediaPlayer.setDataSource(song.songUrl)
        mediaPlayer.prepare()
        mediaPlayer.start()
    }

    fun stopSong(){

        homeViewModel.setIsPlaying(false)
        homeViewModel.setPlayingSong(null)
        mediaPlayer.reset()
    }
}