package com.sevdetneng.rapgeneratorai.presentation.song

import android.media.MediaPlayer
import android.media.TimedText
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sevdetneng.rapgeneratorai.R
import com.sevdetneng.rapgeneratorai.base.BaseFragment
import com.sevdetneng.rapgeneratorai.databinding.FragmentSongBinding
import com.sevdetneng.rapgeneratorai.util.Extensions.millisecondsToMinsAndSeconds
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class SongFragment : BaseFragment<FragmentSongBinding>(FragmentSongBinding::inflate) {

    private val args : SongFragmentArgs by navArgs()
    private val mediaPlayer : MediaPlayer = MediaPlayer()
    private val songViewModel : SongViewModel by viewModels()
    private var stoppedSecond : Int = -1
    private lateinit var seekbar : SeekBar
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val song = args.song
        prepareMediaPlayer(song.songUrl)
        seekbar = binding.songSeekbar
        binding.songRapperImg.setImageResource(song.rapper.rapperImg!!)
        binding.songRapperNameText.text = song.rapper.rapperName

        viewLifecycleOwner.lifecycleScope.launch {
            songViewModel.isPlaying.collect(){
                if(it){
                    binding.buttonPlayPauseSong.setImageResource(R.drawable.btn_pausesong)
                }else{
                    binding.buttonPlayPauseSong.setImageResource(R.drawable.btn_playsong)
                }
            }
        }
        binding.buttonPlayPauseSong.setOnClickListener {
            if(songViewModel.isPlaying.value){
                pauseSong()
            }else{
                continueOrPlaySong()
            }
        }
        binding.buttonBackwards.setOnClickListener {
            if(mediaPlayer.currentPosition>=15000){
                mediaPlayer.seekTo(mediaPlayer.currentPosition-15000)
                stoppedSecond = mediaPlayer.currentPosition
            }else{
                mediaPlayer.seekTo(0)
            }
        }
        binding.buttonForward.setOnClickListener {
            if(mediaPlayer.currentPosition+15000>=mediaPlayer.duration){
                stopSong()
            }else{
                mediaPlayer.seekTo(mediaPlayer.currentPosition+15000)
                stoppedSecond = mediaPlayer.currentPosition
            }
        }
        binding.songBtnBack.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
            findNavController().clearBackStack(R.id.homeFragment)

        }
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser){
                    stoppedSecond = progress
                    mediaPlayer.seekTo(progress)
                    binding.songCurrenTimeText.text = mediaPlayer.currentPosition.millisecondsToMinsAndSeconds()
                }else{
                    binding.songCurrenTimeText.text = mediaPlayer.currentPosition.millisecondsToMinsAndSeconds()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        setOnMediaListener()
        initSeekbar(seekbar)

    }

    fun pauseSong(){
        if(songViewModel.isPlaying.value){
            stoppedSecond = mediaPlayer.currentPosition
            mediaPlayer.pause()
            songViewModel.setIsPlaying(false)
        }
    }

    fun initSeekbar(seekbar : SeekBar){
        seekbar.max = mediaPlayer.duration
        binding.songDurationText.text = mediaPlayer.duration.millisecondsToMinsAndSeconds()
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object : Runnable{
            override fun run() {
                try{
                    seekbar.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this,1000)
                    //binding.songCurrenTimeText.text = mediaPlayer.currentPosition.millisecondsToMinsAndSeconds()
                }catch (e : Exception){
                    seekbar.progress = 0
                    //binding.songCurrenTimeText.text = "0:00"
                }
            }
        },0)
    }


    fun prepareMediaPlayer(url : String){
        mediaPlayer.setDataSource(url)
        mediaPlayer.prepare()
    }

    fun continueOrPlaySong(){
        if(stoppedSecond!=-1){
            mediaPlayer.seekTo(stoppedSecond)
            mediaPlayer.start()
            songViewModel.setIsPlaying(true)
        } else {
            mediaPlayer.start()
            songViewModel.setIsPlaying(true)
        }

    }

    fun setOnMediaListener(){
        mediaPlayer.setOnCompletionListener {
            stopSong()
        }
    }

    fun stopSong(){
        mediaPlayer.pause()
        stoppedSecond = -1
        songViewModel.setIsPlaying(false)
        seekbar.progress = 0
        mediaPlayer.seekTo(0)
        binding.songCurrenTimeText.text = "0:00"

    }

}