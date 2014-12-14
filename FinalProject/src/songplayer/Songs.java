package songplayer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

// TODO: Auto-generated Javadoc
/**
 * The Class Songs.
 */
public class Songs {

	// private SongList SL = new SongList();
	/** The play queue. */
	private static ArrayList<Song> playQueue;
	
	/** The first play. */
	static boolean firstPlay = true;
	
	/** The playing. */
	static boolean playing = true;

	// Creates a new player composed of the below.
	/**
	 * Instantiates a new songs.
	 */
	public Songs() {
		// playQueue = SL.getSongs();
		playQueue = new ArrayList<Song>();
		playQueue.add(new Song("dfg", "Dfg", 200, baseDir + "MusicForGame.mp3"));
		playQueue.add(new Song("dfg", "Dfg", 200, baseDir + "MusicForGamePlayOverandOver.mp3"));
		addSong(playQueue.get(0));
		addSong(playQueue.get(1));
		play();

	}

	/** The base dir. */
	public static String baseDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "songfiles" + System.getProperty("file.separator");

	// adds a song to the queue and starts to play if there isn't a song playing
	/**
	 * Adds the song.
	 *
	 * @param songFileName the song file name
	 */
	public void addSong(Song songFileName) {
		if (playQueue.isEmpty()) {

			playQueue.add(songFileName);

			play();

		} else {

			playQueue.add(songFileName);

		}
	}

	// plays the song once the waiter gets word the next song can be played
	/**
	 * Play.
	 */
	public static void play() {

		ObjectWaitingForSongToEnd waiter = new ObjectWaitingForSongToEnd();

		if (playQueue.isEmpty()) {
			return;
		}

		SongPlayer.playFile(waiter, playQueue.get(0).getFileName());

	}

	// wait listener for letting play() know to send the next song
	/**
	 * The Class ObjectWaitingForSongToEnd.
	 */
	private static class ObjectWaitingForSongToEnd implements EndOfSongListener {

		/* (non-Javadoc)
		 * @see songplayer.EndOfSongListener#songFinishedPlaying(songplayer.EndOfSongEvent)
		 */
		@Override
		public void songFinishedPlaying(EndOfSongEvent eventWithFileNameAndDateFinished) {

			if (firstPlay) {
				playQueue.remove(0);
				firstPlay = false;
			}

			play();

		}

	}

	/**
	 * Gets the playlist.
	 *
	 * @return the playlist
	 */
	public ArrayList<Song> getPlaylist() {
		return playQueue;
	}

	/**
	 * Toogle sound.
	 */
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
	
	/**
	 * Sets the playing.
	 *
	 * @param shouldBePlaying the new playing
	 */
	public static void setPlaying(boolean shouldBePlaying) {
		playing = shouldBePlaying;
	}

}
