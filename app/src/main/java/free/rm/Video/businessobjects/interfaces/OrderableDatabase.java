package free.rm.Video.businessobjects.interfaces;

import java.util.List;

import free.rm.Video.businessobjects.YouTube.POJOs.CardData;

/**
 * An interface to be used by a SQLiteOpenHelperEx database object, indicating that the videos in the database may be reordered.
 */
public interface OrderableDatabase {
	void updateOrder(List<CardData> videos);
}
