/*
 * SkyTube
 * Copyright (C) 2016  Ramon Mifsud
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation (version 3 of the License).
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package free.rm.Video.gui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import free.rm.Video.R;
import free.rm.Video.app.VideoMain;
import free.rm.Video.businessobjects.VideoCategory;
import free.rm.Video.businessobjects.YouTube.POJOs.CardData;
import free.rm.Video.businessobjects.YouTube.newpipe.ContentId;
import free.rm.Video.businessobjects.db.BookmarksDb;
import free.rm.Video.businessobjects.interfaces.CardListener;
import free.rm.Video.databinding.FragmentBookmarksBinding;
import free.rm.Video.gui.businessobjects.adapters.OrderableVideoGridAdapter;
import free.rm.Video.gui.businessobjects.fragments.OrderableVideosGridFragment;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

/**
 * Fragment that displays bookmarked videos.
 */
public class BookmarksFragment extends OrderableVideosGridFragment implements CardListener {
    private FragmentBookmarksBinding binding;

    public BookmarksFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        initBookmarks(container.getContext(), new OrderableVideoGridAdapter(BookmarksDb.getBookmarksDb()), FragmentBookmarksBinding.inflate(inflater, container, false));
        return binding.getRoot();
    }

    private void initBookmarks(@NonNull Context context, @NonNull OrderableVideoGridAdapter videoGridAdapterParam, @NonNull FragmentBookmarksBinding bindingParam) {
        this.binding = bindingParam;
        initOrderableVideos(context, videoGridAdapterParam, bindingParam.videosGridview);
        BookmarksDb.getBookmarksDb().registerListener(this);
        setListVisible(false);

        populateList();
    }

    private void populateList() {
        BookmarksDb.getBookmarksDb().getTotalBookmarkCount()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(numberOfBookmarks -> {
                    if (numberOfBookmarks > 0 && swipeRefreshLayout != null) {
                        setListVisible(true);
                        // swipeRefreshLayout.setRefreshing(true);
                    }
                }).subscribe();
    }

    @Override
    public void onCardAdded(final CardData card) {
        videoGridAdapter.onCardAdded(card);
        setListVisible(true);
    }

    @Override
    public void onCardDeleted(final ContentId contentId) {
        videoGridAdapter.onCardDeleted(contentId);
        if (videoGridAdapter.getItemCount() == 0) {
            setListVisible(false);
        }
    }

	@Override
	protected VideoCategory getVideoCategory() {
		return VideoCategory.BOOKMARKS_VIDEOS;
	}
	

	@Override
	public String getFragmentName() {
		return VideoMain.getStr(R.string.bookmarks);
	}

	@Override
	public int getPriority() {
		return 3;
	}

	@Override
	public String getBundleKey() {
		return MainFragment.BOOKMARKS_FRAGMENT;
	}

    @Override
    public void onDestroyView() {
        BookmarksDb.getBookmarksDb().unregisterListener(this);
        binding = null;
        super.onDestroyView();
    }

    private void setListVisible(boolean visible) {
        if (visible) {
            swipeRefreshLayout.setVisibility(View.VISIBLE);
            binding.noBookmarkedVideosText.setVisibility(View.GONE);
        } else {
            swipeRefreshLayout.setVisibility(View.GONE);
            binding.noBookmarkedVideosText.setVisibility(View.VISIBLE);
        }
    }

}
