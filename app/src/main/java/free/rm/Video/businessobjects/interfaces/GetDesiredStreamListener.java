package free.rm.Video.businessobjects.interfaces;

import org.schabi.newpipe.extractor.stream.StreamInfo;

import free.rm.Video.businessobjects.YouTube.POJOs.YouTubeVideo;

/**
 * Interface to be used when retrieving the desired stream (per the user's preferences) from a Video.
 */
public interface GetDesiredStreamListener {

	/**
	 * Called when the video's stream has been successfully retrieved.
	 *
	 * @param streamInfo  The retrieved video's Uri.
	 */
	void onGetDesiredStream(StreamInfo streamInfo, YouTubeVideo video);

	/**
	 * Called if an error occurred while retrieving the video's Uri.
	 *
	 * @param throwable  Error.
	 */
	void onGetDesiredStreamError(Throwable throwable);
}
