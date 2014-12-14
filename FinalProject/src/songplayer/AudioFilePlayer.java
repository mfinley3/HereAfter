/**
 * This class allows songs to be played in separate threads so they can
 * play concurrently.  It is also possible to register and EndOfSongListener
 * to each new instance of this class so the client code knows when the song
 * song has completely finished. For this, use Rick's class SongPlayer
 * with method playSong that takes an EndOfSongListener as its first argument.
 * 
 * @author Java Zoom and Jorge Vergara
 */
package songplayer;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

// TODO: Auto-generated Javadoc
/**
 * The Class AudioFilePlayer.
 */
public class AudioFilePlayer extends Thread {

  private String fileName;
  private List<EndOfSongListener> listeners = new ArrayList<EndOfSongListener>();

  /**
   * Instantiates a new audio file player.
   *
   * @param audioFileName the audio file name
   */
  public AudioFilePlayer(String audioFileName) {
    fileName = audioFileName;
  }

  /**
   * Adds the end of song listener.
   *
   * @param listener the listener
   */
  public void addEndOfSongListener(EndOfSongListener listener) {
    this.listeners.add(listener);
  }

  /* (non-Javadoc)
   * @see java.lang.Thread#run()
   */
  @Override
  public void run() {
    play();
  }

  /**
   * Note: This Code snippet is from JavaZOOM'a JLayer project
   * 
   * Write the audio file to the output line.
   * 
   * After that loop finishes, send a songFinishedPlaying to all
   * EndOfSongListener objects.
   * 
   */
  public void play() {
    AudioFormat decodedFormat = null;
    try {
      File file = new File(fileName);
      AudioInputStream in = AudioSystem.getAudioInputStream(file);
      AudioInputStream din = null;
      AudioFormat baseFormat = in.getFormat();

      decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
          baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
          baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
          false);

      din = AudioSystem.getAudioInputStream(decodedFormat, in);
      // Play now.
      rawplay(decodedFormat, din);
      in.close();
      // stop();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  // This Code snippet is from JavaZOOM
  /**
   * Rawplay.
   *
   * @param targetFormat the target format
   * @param din the din
   */
  private void rawplay(AudioFormat targetFormat, AudioInputStream din) {
    SourceDataLine line = null;
    try {
      byte[] data = new byte[4096];
      try {
        line = getLine(targetFormat);
      } catch (LineUnavailableException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      if (line != null) {
        // Start
        line.start();
        int nBytesRead = 0;
        @SuppressWarnings("unused")
        int nBytesWritten = 0;
        while (nBytesRead != -1) {
          nBytesRead = din.read(data, 0, data.length);
          if (nBytesRead != -1)
            nBytesWritten = line.write(data, 0, nBytesRead);
        }
        // Stop
        line.drain();
        line.stop();
        line.close();
        din.close();
        
        // Notify the listeners if there are any objects waiting for
        // this message.
        for(EndOfSongListener listener : listeners) {
          EndOfSongEvent eose = new EndOfSongEvent(fileName,
              new GregorianCalendar());
          if (!EventQueue.isDispatchThread()) {
            try {
              EventQueue.invokeAndWait(new EDTListener(eose, listener));
            } catch (InvocationTargetException e) {
              e.printStackTrace();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          } else {
            listener.songFinishedPlaying(eose);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Gets the line.
   *
   * @param audioFormat the audio format
   * @return the line
   * @throws LineUnavailableException the line unavailable exception
   */
  private SourceDataLine getLine(AudioFormat audioFormat)
      throws LineUnavailableException {
    SourceDataLine res = null;
    DataLine.Info info = new DataLine.Info(SourceDataLine.class,
        audioFormat);
    res = (SourceDataLine) AudioSystem.getLine(info);
    res.open(audioFormat);
    return res;
  }

  /**
   * The listener interface for receiving EDT events.
   * The class that is interested in processing a EDT
   * event implements this interface, and the object created
   * with that class is registered with a component using the
   * component's <code>addEDTListener<code> method. When
   * the EDT event occurs, that object's appropriate
   * method is invoked.
   *
   * @see EDTEvent
   */
  private class EDTListener implements Runnable {

    /** The eose. */
    private EndOfSongEvent eose;
    
    /** The listener. */
    private EndOfSongListener listener;

    /**
     * Instantiates a new EDT listener.
     *
     * @param eose the eose
     * @param listener the listener
     */
    public EDTListener(EndOfSongEvent eose, EndOfSongListener listener) {
      this.eose = eose;
      this.listener = listener;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
      listener.songFinishedPlaying(eose);
    }
  }

}