package abnod.mediateka.mapper;

import abnod.mediateka.model.Media;

import java.util.Map;

public class SearchProvider {
    public static String search(Map parameters) {
        String query = "SELECT * FROM mediateka.media WHERE ";
        Media media = (Media) parameters.get("searchParams");
        if (media.getTitle().length() > 0) {
            query += "title=\"" + media.getTitle() + "\" AND ";
        }
        if (media.getPath().length() > 0) {
            query += "path=\"" + media.getPath() + "\" AND ";
        }
        if (media.getSinger().length() > 0) {
            query += "singer=\"" + media.getSinger() + "\" AND ";
        }
        query += "type=\"" + media.getType() + "\" LIMIT " + parameters.get("page") + ", " + parameters.get("records") + ";";
        return query;
    }

    public static String getPages(Map parameters) {
        String query = "SELECT COUNT(id) FROM mediateka.media WHERE ";
        Media media = (Media) parameters.get("searchParams");
        if (media.getTitle().length() > 0) {
            query += "title=\"" + media.getTitle() + "\" AND ";
        }
        if (media.getPath().length() > 0) {
            query += "path=\"" + media.getPath() + "\" AND ";
        }
        if (media.getSinger().length() > 0) {
            query += "singer=\"" + media.getSinger() + "\" AND ";
        }
        query += "type=\"" + media.getType() + "\";";
        return query;
    }
}
