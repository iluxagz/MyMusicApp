package by.iluxaz.musicprojectapp.player

import by.iluxaz.musicprojectapp.R

class MusicTracksCollection {
    val musicCollection = arrayListOf<MusicTracks>(MusicTracks("Luqus","I'll Need You","4:11", R.drawable.feel_the_moment),
        MusicTracks("Menual","Amplitude","4:51", R.drawable.feel_the_moment2),
        MusicTracks("Pensees & Menual","Disconnect","4:45", R.drawable.feel_the_moment3),
        MusicTracks("Bring Me The Horizon","Steal Something","10:11", R.drawable.feel_the_moment4),
        MusicTracks("Bones","90210","3:06", R.drawable.feel_the_moment5),
        MusicTracks("CRLSN","Perfect","2:05", R.drawable.feel_the_moment6),
        MusicTracks("Olorgin","Alone with You","4:30", R.drawable.feel_the_moment7),
        MusicTracks("Dasha Charusha","Husband and Wife","4:04", R.drawable.feel_the_moment8),
        MusicTracks("EXIT","LEFT","3:46", R.drawable.feel_the_moment9),
        MusicTracks("Glitch","Vacuum","2:53", R.drawable.feel_the_moment10),
        MusicTracks("Iluxa_Z","Free Your Mind","3:05", R.drawable.feel_the_moment11),
        MusicTracks("Menual","Planetary","4:20", R.drawable.feel_the_moment12),
        MusicTracks("Bones","True Fear","3:01", R.drawable.feel_the_moment13),
        MusicTracks("Silence","I Could Live","3:21", R.drawable.feel_the_moment14))

    companion object{
       val instance by lazy {
        MusicTracksCollection()
       }
    }

}