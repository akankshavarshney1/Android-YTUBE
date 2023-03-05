package free.rm.Video.gui.businessobjects.adapters;

import java.util.List;

import free.rm.Video.businessobjects.AsyncTaskParallel;
import free.rm.Video.businessobjects.YouTube.POJOs.YouTubeCommentThread;

public class NewPipeCommentsTask extends AsyncTaskParallel<Void, Void, List<YouTubeCommentThread>> {

    private String nextPageUrl;
    private boolean hasNextPage = true;

    @Override
    protected List<YouTubeCommentThread> doInBackground(Void... voids) {
        return null;
    }
}
