package abouraadapps.assignment_2;

/**
 * Created by marioabouraad on 15-09-08.
 */
public class Movie {

    private String title;
    private String year;
    private String summary;
    private int fanart;
    private int poster;


    public Movie(String title, String year, String summary, int fanart, int poster) {
        this.title = title;
        this.year = year;
        this.summary = summary;
        this.fanart = fanart;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


    public int getFanart() {
        return fanart;
    }

    public void setFanart(int fanart) {
        this.fanart = fanart;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }


}
