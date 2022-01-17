package MovieCollection.gui.model;

import MovieCollection.Dal.Exceptions.DataException;
import MovieCollection.be.Movie;
import MovieCollection.bll.MovieLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchModel {

    private MovieLogic movieLogic;
    private ArrayList<Movie> movieList;

    public SearchModel() throws DataException {



        this.movieLogic = new MovieLogic();
    }

    private List<Movie> filterByName(String input) throws DataException {
        this.movieList = movieLogic.getAllMovies();
        List<Movie> result = movieList.stream().filter(movie->
                movie.getName().toLowerCase().contains(input.toLowerCase().trim())).collect(Collectors.toList());
        return result;
    }

    private List<Movie> filterByCategory(String input) throws DataException
    {
        List<Movie> result = movieList.stream().filter(movie ->
                movie.getCategoryString().toLowerCase().contains(input.toLowerCase().trim())).collect(Collectors.toList());
        return result;
    }

    private List<Movie> filterByImdbRating(String input) throws DataException
    {
        List<Movie> result = movieList.stream().filter(movie ->
                String.valueOf(movie.getImdbRating()).contains(input.trim())).collect(Collectors.toList());
        return result;
    }

    private List<Movie> filterByPersonalRating(String input) throws DataException
    {
        List<Movie> result = movieList.stream().filter(movie ->
                String.valueOf(movie.getPrivateRating()).contains(input.trim())).collect(Collectors.toList());
        return result;
    }

    public ObservableList<Movie> filterLists(String input) throws DataException
    {
        List<List<Movie>> filteredLists = new ArrayList<>();
        filteredLists.add(filterByName(input));
        filteredLists.add(filterByCategory(input));
        filteredLists.add(filterByImdbRating(input));
        filteredLists.add(filterByPersonalRating(input));

        ObservableList<Movie> fullFilteredList = FXCollections.observableArrayList();
        for(List<Movie> listOfSearches: filteredLists)
        {
            for(Movie movie: listOfSearches)
            {
                if(!fullFilteredList.contains(movie))
                {
                    fullFilteredList.add(movie);
                }
            }
        }
        return fullFilteredList;
    }
}
