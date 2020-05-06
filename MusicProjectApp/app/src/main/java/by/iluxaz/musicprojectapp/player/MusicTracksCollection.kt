package by.iluxaz.musicprojectapp.player

import by.iluxaz.musicprojectapp.R

class MusicTracksCollection {
    val musicCollection = ArrayList<MusicTracks>()

    companion object{
       val instance by lazy {
        MusicTracksCollection()
       }
    }

   fun addMusicTracksToList(){
       musicCollection.add(MusicTracks("Luqus","I'll Need You","4:11", R.drawable.feel_the_moment))
       musicCollection.add(MusicTracks("Menual","Amplitude","4:51", R.drawable.feel_the_moment2))
       musicCollection.add(MusicTracks("Pensees & Menual","Disconnect","4:45", R.drawable.feel_the_moment3))
       musicCollection.add(MusicTracks("Luqus","I'll Need You","4:11", R.drawable.feel_the_moment))
       musicCollection.add(MusicTracks("Luqus","I'll Need You","4:11", R.drawable.feel_the_moment))
       musicCollection.add(MusicTracks("Luqus","I'll Need You","4:11", R.drawable.feel_the_moment))
       musicCollection.add(MusicTracks("Luqus","I'll Need You","4:11", R.drawable.feel_the_moment))
       musicCollection.add(MusicTracks("Luqus","I'll Need You","4:11", R.drawable.feel_the_moment))
       musicCollection.add(MusicTracks("Luqus","I'll Need You","4:11", R.drawable.feel_the_moment))
   }
}