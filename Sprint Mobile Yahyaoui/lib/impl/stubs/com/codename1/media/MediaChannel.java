package com.codename1.media;


/**
 *  A thread-safe media channel that ensures that only Media is playing at a time.  This class will make sure
 *  that the previous media has finished pausing before the next one begins to play.  This can be important
 *  if you're alternating between recording audio and playing audio, as some platforms (e.g. iOS Safari) have problems
 *  dealing with simulataneous use of the microphone and audio playing.
 *  
 *  @author shannah
 */
public class MediaChannel {

	public MediaChannel() {
	}

	/**
	 *  Plays the provided media on the channel.
	 *  @param media The media to play.
	 *  @return The play request object to track when play has started.
	 */
	public AsyncMedia.PlayRequest play(Media media) {
	}

	/**
	 *  Plays the provided media on the channel.
	 *  @param media The media to play
	 *  @param autoclean If true, then the channel will automatically call cleanup() on the media 
	 *  when it is finished playing.
	 *  @return The play request object to track when play has started.
	 */
	public AsyncMedia.PlayRequest play(Media media, boolean autoclean) {
	}

	/**
	 *  Plays the provided media on the channel.
	 *  @param media The media to play.
	 *  @return The play request object to track when play has started.
	 */
	public AsyncMedia.PlayRequest play(AsyncMedia media) {
	}

	/**
	 *  Plays the provided media on the channel.
	 *  @param media The media to play
	 *  @param autoclean If true, then the channel will automatically call cleanup() on the media 
	 *  when it is finished playing.
	 *  @return The play request object to track when play has started.
	 */
	public AsyncMedia.PlayRequest play(AsyncMedia media, boolean autoclean) {
	}

	/**
	 *  Plays the provided media on the channel.
	 *  @param req The play request object to track when the media has started playing (or if the playing is canceled).
	 *  @param media The media to play.
	 *  @param autoclean IF true, then the channel will automatically call cleanup() on the media when playing is done.
	 *  @return The play request object.
	 *  
	 */
	public AsyncMedia.PlayRequest play(AsyncMedia.PlayRequest req, AsyncMedia media, boolean autoclean) {
	}
}
