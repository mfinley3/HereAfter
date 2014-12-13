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

public class Song implements Serializable{

	public final static int MAX_PLAYS = 5;
	
	String title;
	String artist;
	int timesPlayedInDay;
	int songLength;
	String fileName;
	private GregorianCalendar mostRecentDate;
	
	//creates a song
	public Song(String artist, String title, int songLength, String fileName){
		
		this.title = title;
		this.artist = artist;
		this.songLength = songLength;
		timesPlayedInDay = 0;
		this.fileName = fileName;
		mostRecentDate = new GregorianCalendar(1970, 0, 1);
		
	}

	//returns the artist of that song
	public String getArtist() {
		
		return artist;
	}

	//returns the title of that song
	public String getTitle() {
		
		return title;
	}

	//returns the length of that song
	public int getSeconds() {
		
		return songLength;
	}
	
	//returns the times played of that song
	public int getTimesPlayedInDay() {
		
		return timesPlayedInDay;
	}

	//returns the FileName of that song
	public String getFileName() {
		return fileName;
	}
	
	//returns if the song can play 
	public boolean canPlay() {
		
		return timesPlayedInDay < MAX_PLAYS;
	}
	
	//stuff for knowing the date
	private boolean sameDay(GregorianCalendar today, GregorianCalendar other) {
		return today.get(Calendar.YEAR) == other.get(Calendar.YEAR)
				&& today.get(Calendar.MONTH) == other.get(Calendar.MONTH)
				&& today.get(Calendar.DAY_OF_MONTH) == other
						.get(Calendar.DAY_OF_MONTH);
	}

	public void pretendTheDateHasChanged() {
		mostRecentDate.set(Calendar.DATE, 1);
	}
	
	//checks to see if the song can play again using dates and if it can resets count
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

	public GregorianCalendar getmostRecentDate() {
		
		return mostRecentDate;
		
	}
	
	public String toString(){
		
		DecimalFormat df = new DecimalFormat("00");
		int minutes = (getSeconds() / 60);
		int seconds = getSeconds() - (minutes * 60);
		
		return df.format(minutes) + ":" + df.format(seconds) + " " + getTitle() + " by " + getArtist();
	
	}
	
	
}
