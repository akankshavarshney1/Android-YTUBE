package free.rm.Video.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import free.rm.Video.R;
import free.rm.Video.app.VideoMain;
import free.rm.Video.businessobjects.VideoCategory;
import free.rm.Video.businessobjects.YouTube.POJOs.YouTubeChannel;
import free.rm.Video.gui.businessobjects.adapters.VideoGridAdapter;

/**
 * A fragment that displays the videos belonging to a channel.
 */
public class ChannelVideosFragment extends VideosGridFragment {
	/** YouTube Channel */
	private YouTubeChannel channel;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// get the channel
		channel = (YouTubeChannel) requireArguments().getSerializable(ChannelBrowserFragment.CHANNEL_OBJ);

		// create and return the view
		View view =  super.onCreateView(inflater, container, savedInstanceState);

		if (channel != null) {
			videoGridAdapter.setYouTubeChannel(channel);
		}

		return view;
	}

    public VideoGridAdapter getVideoGridAdapter() {
        return videoGridAdapter;
    }

	public void setYouTubeChannel(YouTubeChannel youTubeChannel) {
		channel = youTubeChannel;
		if (videoGridAdapter != null) {
			videoGridAdapter.setYouTubeChannel(youTubeChannel);
		}
	}

	@Override
	protected VideoCategory getVideoCategory() {
		return VideoCategory.CHANNEL_VIDEOS;
	}

	@Override
	protected String getSearchString() {
		return channel.getId();
	}

	@Override
	public String getFragmentName() {
		return VideoMain.getStr(R.string.videos);
	}

	@Override
	public int getPriority() {
		return 0;
	}
}
