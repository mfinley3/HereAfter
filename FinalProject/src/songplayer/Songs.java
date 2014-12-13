package songplayer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

public class Songs {

	// private SongList SL = new SongList();
	private static ArrayList<Song> playQueue;
	static boolean firstPlay = true;
	static boolean playing = true;

	// Creates a new player composed of the below.
	public Songs() {
		// playQueue = SL.getSongs();
		playQueue = new ArrayList<Song>();
		playQueue.add(new Song("dfg", "Dfg", 200, baseDir + "MusicForGame.mp3"));
		playQueue.add(new Song("dfg", "Dfg", 200, baseDir + "MusicForGamePlayOverandOver.mp3"));
		addSong(playQueue.get(0));
		addSong(playQueue.get(1));
		play();

	}

	public static String baseDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "songfiles" + System.getProperty("file.separator");

	// adds a song to the queue and starts to play if there isn't a song playing
	public void addSong(Song songFileName) {
		if (playQueue.isEmpty()) {

			playQueue.add(songFileName);

			play();

		} else {

			playQueue.add(songFileName);

		}
	}

	// plays the song once the waiter gets word the next song can be played
	public static void play() {

		ObjectWaitingForSongToEnd waiter = new ObjectWaitingForSongToEnd();

		if (playQueue.isEmpty()) {
			return;
		}

		SongPlayer.playFile(waiter, playQueue.get(0).getFileName());

	}

	// wait listener for letting play() know to send the next song
	private static class ObjectWaitingForSongToEnd implements EndOfSongListener {

		@Override
		public void songFinishedPlaying(EndOfSongEvent eventWithFileNameAndDateFinished) {

			if (firstPlay) {
				playQueue.remove(0);
				firstPlay = false;
			}

			play();

		}

	}

	public ArrayList<Song> getPlaylist() {
		return playQueue;
	}

	public static void toogleSound() {

		if (playing) {
			SongPlayer.stop();
			playing = false;

		} else {
			firstPlay = true;
			playing = true;
			play();

		}
	}
	
	public static void setPlaying(boolean shouldBePlaying) {
		playing = shouldBePlaying;
	}

}
