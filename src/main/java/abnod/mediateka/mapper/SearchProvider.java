package abnod.mediateka.mapper;

import abnod.mediateka.model.Media;

import java.util.Map;

public class SearchProvider {
    public static String search(Map parameters) {
        String query = "SELECT * FROM mediateka.media WHERE ";
        Media media = (Media) parameters.get("searchParams");
        if (media.getSinger().length() > 0) {
            query += "title=\"" + media.getSinger() + "\", ";
        }
        if (media.getPath().length() > 0) {
            query += "path=\"" + media.getPath() + "\", ";
        }
        if (media.getTitle().length() > 0) {
            query += "singer=\"" + media.getTitle() + "\", ";
        }
        query += "type=\"" + media.getType() + "\";";
        return query;
    }
}
