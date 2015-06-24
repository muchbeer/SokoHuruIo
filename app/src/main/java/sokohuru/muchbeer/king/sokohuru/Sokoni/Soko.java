package sokohuru.muchbeer.king.sokohuru.Sokoni;

/**
 * Created by muchbeer on 6/4/2015.
 */
public class Soko {

    private int id;
    private String title;
    private String image;
    private int releaseYear;
    private String rating;
    private String genre;

    public Soko() {

    }

    public Soko(int id,
                String title,
                String image,
                int releaseYear,
                String rating,
                String genre) {

        this.id = id;
        this.title = title;
        this.image = image;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.genre=genre;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image= image;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return
                "Title: "+title+
                "Image: " + image+
                "Release Year: "+ releaseYear+
                "Rating: "+rating+
                "Genre: " + genre;
    }
}
