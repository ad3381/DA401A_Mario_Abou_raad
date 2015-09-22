package abouraadapps.assignment_3;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marioabouraad on 15-09-17.
 */
public class Movie {
    public String title;
    public String year;
    public String coverUrl;
    public List<Movie> movieList;

    public Movie() {

    }

    public Movie(String title, String year, String coverUrl) {
        this.title = title;
        this.year = year;
        this.coverUrl = coverUrl;
    }

    public String getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public List<Movie> fromJson(JSONArray jsonArray) throws JSONException {
        movieList = new ArrayList<>(jsonArray.length());


        for (int i = 0; i < jsonArray.length(); i++) {
            title = jsonArray.getJSONObject(i).getString("title");
            year = jsonArray.getJSONObject(i).getString("year");
            coverUrl = jsonArray.getJSONObject(i).getJSONObject("images").getJSONObject("poster").getString("cover");
            movieList.add(new Movie(title, year, coverUrl));
        }
        return movieList;
    }
}
