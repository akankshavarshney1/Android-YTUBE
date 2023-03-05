package free.rm.Video.gui.businessobjects.fragments;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;

import free.rm.Video.databinding.VideosGridviewBinding;
import free.rm.Video.gui.businessobjects.SimpleItemTouchHelperCallback;
import free.rm.Video.gui.businessobjects.adapters.OrderableVideoGridAdapter;
import free.rm.Video.gui.fragments.VideosGridFragment;

/**
 * A VideosGridFragment that supports reordering of the videos in the Grid.
 */
public abstract class OrderableVideosGridFragment extends VideosGridFragment {
    public OrderableVideosGridFragment() {
    }

    protected void initOrderableVideos(@NonNull Context context, @NonNull OrderableVideoGridAdapter videoGridAdapterParam, @NonNull VideosGridviewBinding gridviewBindingParam) {
        initVideos(context, videoGridAdapterParam, gridviewBindingParam);
        swipeRefreshLayout.setEnabled(false);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(videoGridAdapterParam);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(gridviewBinding.gridView);
    }
}
