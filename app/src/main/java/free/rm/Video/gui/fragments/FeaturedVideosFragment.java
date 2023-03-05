/*
 * SkyTube
 * Copyright (C) 2017  Ramon Mifsud
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

import free.rm.Video.R;
import free.rm.Video.app.VideoMain;
import free.rm.Video.businessobjects.VideoCategory;

/**
 * A fragment that holds featured videos.
 */
public class FeaturedVideosFragment extends VideosGridFragment {

	@Override
	protected VideoCategory getVideoCategory() {
		return VideoCategory.FEATURED;
	}


	@Override
	public String getFragmentName() {
		return VideoMain.getStr(R.string.featured);
	}

	@Override
	public int getPriority() {
		return 0;
	}

	@Override
	public String getBundleKey() {
		return MainFragment.FEATURED_VIDEOS_FRAGMENT;
	}
}
