package by.iluxaz.musicprojectapp.player

import by.iluxaz.musicprojectapp.R

class AloneTracksCollection {
    val musicCollection = arrayListOf(
        MusicTracks("Akira Yamaoka","Never Forgive Me","2:19", R.drawable.feel_yourself_alone8),
        MusicTracks("Brandon","Cry Of Fear","5:10",R.drawable.feel_yourself_alone2),
        MusicTracks("SurrenderDorothy","Be Careful Up There","1:08", R.drawable.feel_yourself_alone9),
        MusicTracks("Orlogin","Don't Come Close","2:50", R.drawable.feel_yourself_alone),
        MusicTracks("Bones","IFeelLikeDirt","1:47", R.drawable.feel_yourself_alone13),
        MusicTracks("Akira Yamaoka","Alessa's Theme","1:44", R.drawable.feel_yourself_alone4),
        MusicTracks("Orlogin","Sirens","7:17", R.drawable.feel_yourself_alone10),
        MusicTracks("Fable","Interlude","2:38", R.drawable.feel_yourself_alone5),
        MusicTracks("Bones","Disgrace","0:41", R.drawable.feel_yourself_alone6),
        MusicTracks("Akira Yamaoka","Fortunate Sleep","2:07", R.drawable.feel_yourself_alone3),
        MusicTracks("Muddasheep","Screech","3:58", R.drawable.feel_yourself_alone11),
        MusicTracks("Simply","Obscurity","10:42", R.drawable.feel_yourself_alone7),
        MusicTracks("Bones","KickingTheBucket","0:34", R.drawable.feel_yourself_alone12))


    companion object{
        val instance by lazy {
            AloneTracksCollection()
        }
    }
}