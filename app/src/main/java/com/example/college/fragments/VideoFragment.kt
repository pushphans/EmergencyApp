package com.example.college.fragments

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.college.R
import com.example.college.databinding.FragmentVideoBinding
import com.example.college.db.ContactViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class VideoFragment : Fragment(R.layout.fragment_video) {

    private var _binding : FragmentVideoBinding? = null
    private val binding get() = _binding!!

    private val viewModel : ContactViewModel by activityViewModels()
    private val args : VideoFragmentArgs by navArgs()
    private val currentTime = 0F
    private lateinit var myYoutubePlayer : YouTubePlayer
    private lateinit var  videoId : String




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVideoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        videoId = args.videoId

        lifecycle.addObserver(binding.youtubePlayer)

        binding.youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                myYoutubePlayer = youTubePlayer
                myYoutubePlayer.loadVideo(videoId, 0F)
            }
        })

    }







    override fun onResume() {
        super.onResume()

        (activity as AppCompatActivity).supportActionBar?.title = "Emergency Video Tips"
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding.youtubePlayer.release()
        _binding = null

    }


}