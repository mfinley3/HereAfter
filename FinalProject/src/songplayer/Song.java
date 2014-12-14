/*
 * Authors: Katelyn Hudspeth and Mike Finley
 * 
 * This is the song class.  It makes a Song and sets
 * all the proper components to it. Also knows to only allow itself 5 plays per day, and 
 * resets each day so the song can be played again the next 5 times.
 * 
 */

package songplayer;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

// TODO: Auto-generated Javadoc
/**
 * The Class Song.
 */
public class Song implements Serializable{

	/** The Constant MAX_PLAYS. */
	public final static int MAX_PLAYS = 5;
	
	/** The title. */
	String title;
	
	/** The artist. */
	String artist;
	
	/** The times played in day. */
	int timesPlayedInDay;
	
	/** The song length. */
	int songLength;
	
	/** The file name. */
	String fileName;
	
	/** The most recent date. */
	private GregorianCalendar mostRecentDate;
	
	//creates a song
	/**
	 * Instantiates a new song.
	 *
	 * @param artist the artist
	 * @param title the title
	 * @param songLength the song length
	 * @param fileName the file name
	 */
	public Song(String artist, String title, int songLength, String fileName){
		
		this.title = title;
		this.artist = artist;
		this.songLength = songLength;
		timesPlayedInDay = 0;
		this.fileName = fileName;
		mostRecentDate = new GregorianCalendar(1970, 0, 1);
		
	}

	//returns the artist of that song
	/**
	 * Gets the artist.
	 *
	 * @return the artist
	 */
	public String getArtist() {
		
		return artist;
	}

	//returns the title of that song
	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		
		return title;
	}

	//returns the length of that song
	/**
	 * Gets the seconds.
	 *
	 * @return the seconds
	 */
	public int getSeconds() {
		
		return songLength;
	}
	
	//returns the times played of that song
	/**
	 * Gets the times played in day.
	 *
	 * @return the times played in day
	 */
	public int getTimesPlayedInDay() {
		
		return timesPlayedInDay;
	}

	//returns the FileName of that song
	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}
	
	//returns if the song can play 
	/**
	 * Can play.
	 *
	 * @return true, if successful
	 */
	public boolean canPlay() {
		
		return timesPlayedInDay < MAX_PLAYS;
	}
	
	//stuff for knowing the date
	/**
	 * Same day.
	 *
	 * @param today the today
	 * @param other the other
	 * @return true, if successful
	 */
	private boolean sameDay(GregorianCalendar today, GregorianCalendar other) {
		return today.get(Calendar.YEAR) == other.get(Calendar.YEAR)
				&& today.get(Calendar.MONTH) == other.get(Calendar.MONTH)
				&& today.get(Calendar.DAY_OF_MONTH) == other
						.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Pretend the date has changed.
	 */
	public void pretendTheDateHasChanged() {
		mostRecentDate.set(Calendar.DATE, 1);
	}
	
	//checks to see if the song can play again using dates and if it can resets count
	/**
	 * Play.
	 */
	public void play() {
		GregorianCalendar today = new GregorianCalendar();
		if (!sameDay(today, this.mostRecentDate)) {
			timesPlayedInDay = 1;
			mostRecentDate = today;
		} else {
			if (canPlay()) {
				timesPlayedInDay++;
			}
		}
	}

	/**
	 * Gets the most recent date.
	 *
	 * @return the most recent date
	 */
	public GregorianCalendar getmostRecentDate() {
		
		return mostRecentDate;
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		
		DecimalFormat df = new DecimalFormat("00");
		int minutes = (getSeconds() / 60);
		int seconds = getSeconds() - (minutes * 60);
		
		return df.format(minutes) + ":" + df.format(seconds) + " " + getTitle() + " by " + getArtist();
	
	}
	
	
}
