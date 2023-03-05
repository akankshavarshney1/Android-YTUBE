package free.rm.Video.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import free.rm.Video.R;
import free.rm.Video.app.VideoMain;
import free.rm.Video.businessobjects.VideoCategory;
import free.rm.Video.businessobjects.YouTube.POJOs.YouTubeChannel;
import free.rm.Video.businessobjects.YouTube.POJOs.YouTubePlaylist;
import free.rm.Video.gui.businessobjects.MainActivityListener;
import free.rm.Video.gui.businessobjects.PlaylistClickListener;
import free.rm.Video.gui.businessobjects.adapters.PlaylistsGridAdapter;

/**
 * A fragment that displays the Playlists belonging to a Channel
 */
public class ChannelPlaylistsFragment extends VideosGridFragment implements PlaylistClickListener, SwipeRefreshLayout.OnRefreshListener {
	private PlaylistsGridAdapter    playlistsGridAdapter;
	private MainActivityListener    mainActivityListener;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);

		swipeRefreshLayout.setOnRefreshListener(this);

		if (playlistsGridAdapter == null) {
			playlistsGridAdapter = new PlaylistsGridAdapter(getActivity(), this);
		} else {
			playlistsGridAdapter.setContext(getActivity());
		}

		YouTubeChannel channel = (YouTubeChannel) requireArguments()
				.getSerializable(ChannelBrowserFragment.CHANNEL_OBJ);
		playlistsGridAdapter.setYouTubeChannel(channel);

		gridviewBinding.gridView.setAdapter(playlistsGridAdapter);

		return view;
	}

	@Override
	public void onDestroy() {
		playlistsGridAdapter.clearBackgroundTasks();
		super.onDestroy();
	}

	@Override
	public String getFragmentName() {
		return VideoMain.getStr(R.string.playlists);
	}

	@Override
	public void onClickPlaylist(YouTubePlaylist playlist) {
		if(mainActivityListener != null)
			mainActivityListener.onPlaylistClick(playlist);
	}

	public void setMainActivityListener(MainActivityListener mainActivityListener) {
		this.mainActivityListener = mainActivityListener;
	}

	@Override
	public void onRefresh() {
		playlistsGridAdapter.refresh(youTubePlaylists -> swipeRefreshLayout.setRefreshing(false));
	}

	@Override
	protected VideoCategory getVideoCategory() {
		return null;
	}

	@Override
	public int getPriority() {
		return 6;
	}
}
