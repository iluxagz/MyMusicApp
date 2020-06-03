package by.iluxaz.musicprojectapp.player

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.MediaController
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import by.iluxaz.musicprojectapp.MainActivity
import by.iluxaz.musicprojectapp.R
import kotlinx.android.synthetic.main.music_player_layout.*
import java.util.concurrent.TimeUnit

open class MomentActivity : AppCompatActivity(), View.OnClickListener,
    SeekBar.OnSeekBarChangeListener {

    private val momentMusic = intArrayOf(
        R.raw.luqus_ill_need_you,
        R.raw.menual_amplitude,
        R.raw.pensees_menual_disconnect,
        R.raw.bmth_steal_something,
        R.raw.bones_90210,
        R.raw.crlsn_perfect,
        R.raw.olorgin_alone_with_you,
        R.raw.dasha_charusha_husband_and_wife,
        R.raw.exit_left,
        R.raw.glitch_vacuum,
        R.raw.iluxaz_free_your_mind,
        R.raw.menual_planetary,
        R.raw.bones_true_fear,
        R.raw.silence_i_could_live
    )

    private val momentMusicCover = intArrayOf(
        R.drawable.feel_the_moment,
        R.drawable.feel_the_moment2,
        R.drawable.feel_the_moment3,
        R.drawable.feel_the_moment4,
        R.drawable.feel_the_moment5,
        R.drawable.feel_the_moment6,
        R.drawable.feel_the_moment7,
        R.drawable.feel_the_moment8,
        R.drawable.feel_the_moment9,
        R.drawable.feel_the_moment10,
        R.drawable.feel_the_moment11,
        R.drawable.feel_the_moment12,
        R.drawable.feel_the_moment13,
        R.drawable.feel_the_moment14

    )

    private val artistOfTrack = arrayListOf(
        "Luqus",
        "Menual",
        "Pensees & Menual",
        "Bring Me The Horizon",
        "Bones",
        "CRLSN",
        "Olorgin",
        "Dasha Charusha",
        "EXIT",
        "Glitch",
        "Iluxa_Z",
        "Menual",
        "Bones",
        "Silence"
    )

    private val nameOfTrack = arrayListOf(
        "I'll Need You",
        "Amplitude",
        "Disconnect",
        "Steal Something",
        "90210",
        "Perfect",
        "Alone with You",
        "Husband and Wife",
        "LEFT",
        "Vacuum",
        "Free Your Mind",
        "Planetary",
        "True Fear",
        "I Could Live"
    )

    private var musicPosition = 0
    var handler = Handler()

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mediaController: MediaController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.music_player_layout)
        mediaController = MediaController(this)
        playerSeekBar.progress = 0
        playerSeekBar.max = 100
        createMediaPlayer(musicPosition)

        playerSeekBar.setOnSeekBarChangeListener(this)
        playButton.setOnClickListener(this)
        nextButton.setOnClickListener(this)
        previousButton.setOnClickListener(this)

        backToMenuButton.setOnClickListener {
            if (mediaPlayer.isPlaying)
                mediaPlayer.stop()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        listOfTracksButton.setOnClickListener {
            val intent = Intent(this, MomentTrackListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createMediaPlayer(position: Int) {
        mediaPlayer = MediaPlayer.create(this, momentMusic[position])
    }

    fun milliSecondsToString(time: Int): String {
        var correctTime: String
        var sec = TimeUnit.MILLISECONDS.toSeconds(time.toLong())
        val min = TimeUnit.SECONDS.toMinutes(sec)
        sec %= 60
        correctTime = "$min : "
        if (sec < 10)
            correctTime += "0"
        return correctTime + "$sec"
    }

    private fun playAudio(position: Int) {
        val seekBarProgress = SeekBarProgressThread()
        handler.postDelayed(seekBarProgress, 50)
        playerSeekBar.max = mediaPlayer.duration
        playerSeekBar.progress = mediaPlayer.currentPosition

        textTimeTotal.text = milliSecondsToString(playerSeekBar.max)
        textCurrentTime.text = milliSecondsToString(playerSeekBar.progress)
        textTitleOfTrack.text = nameOfTrack[position]
        textNameOfArtist.text = artistOfTrack[position]
        titlePhoto.setImageResource(momentMusicCover[position])

        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener {
            nextAudio()
        }

    }

     private fun nextAudio() {
        if (mediaPlayer.isPlaying)
            mediaPlayer.stop()
            mediaPlayer.release()
        if (musicPosition < (momentMusic.size - 1)) {
            musicPosition++
        } else {
            musicPosition = 0
        }
        createMediaPlayer(musicPosition)
        playAudio(musicPosition)
    }

     private fun previousAudio() {
        if (mediaPlayer.isPlaying)
            mediaPlayer.stop()
            mediaPlayer.release()
        if (musicPosition > 0) {
            musicPosition--
        } else {
            musicPosition = momentMusic.size - 1
        }
        createMediaPlayer(musicPosition)
        playAudio(musicPosition)
    }

    inner class SeekBarProgressThread : Runnable {
        override fun run() {
            val currentTime = mediaPlayer.currentPosition
            textCurrentTime.text = milliSecondsToString(currentTime)
            playerSeekBar.progress = currentTime

            if (currentTime != mediaPlayer.duration)
                handler.postDelayed(this, 50)

        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.playButton -> {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                    playButton.setImageResource(R.drawable.ic_play)
                } else {
                    playButton.setImageResource(R.drawable.ic_pause)
                    playAudio(musicPosition)
                    mediaPlayer.start()
                }
            }

            R.id.nextButton -> {
                nextAudio()
            }

            R.id.previousButton -> {
                previousAudio()
            }
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        seekBar?.progress?.let {
            mediaPlayer.seekTo(it)
        }
    }

}
