package MovieCollection.be;

import java.util.ArrayList;

public class OldMovieList {

    private ArrayList<Movie> movies;
    private boolean shouldBeReturned;

    public OldMovieList()
    {
        this.movies = new ArrayList<>();
        this.shouldBeReturned = false;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public boolean isShouldBeReturned() {
        return shouldBeReturned;
    }

    public void setShouldBeReturned(boolean shouldBeReturned) {
        this.shouldBeReturned = shouldBeReturned;
    }

}
