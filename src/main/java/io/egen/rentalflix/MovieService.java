package io.egen.rentalflix;

import java.util.ArrayList;
import java.util.List;

/**
 * Service implementing IFlix interface You can use any Java collection type to
 * store movies
 */
public class MovieService implements IFlix {
    //List for maintaining all the movies
    public static List<Movie> allMovies = new ArrayList();
    //Count for auto incrementing the ids
    public static int count = 101;

    @Override
    public List<Movie> findAll() {
        System.out.println("All movies: ");
        System.out.println("ID\t\tTitle\t\t\tYear\t\tRented By");
        for (Movie movie : allMovies) {
            //Print allth movies 
            System.out.println(movie.toString());
        }
        return allMovies;
    }

    @Override
    public List<Movie> findByName(String name) {
        List<Movie> moviesByName = new ArrayList();
        for (Movie m : allMovies) {
            //Check whether movie with given title is present in the list
            if (m.getTitle().equals(name)) {
                moviesByName.add(m);
            }
        }
        //Retrun list of movies with given name
        return moviesByName;
    }

    @Override
    public Movie create(Movie movie) {
        //Add movie to the list and return the newly created movie
        allMovies.add(movie);
        return movie;
    }

    @Override
    public Movie update(Movie movie) throws IllegalArgumentException {
        boolean isPresent = false;
        for (Movie m : allMovies) {
            //If a movie is found where it's id is equal to the id of given movie then update the values
            if (m.getId() == movie.getId()) {
                m.setLanguage(movie.getLanguage());
                m.setTitle(movie.getTitle());
                m.setYear(movie.getYear());
                //temporary variable to check whether an update took place
                isPresent = true;
                //exit the loop as we already found the movie
                break;
            }
        }
        if(isPresent == false){
            //If movie with given id is not found, throw IllegalArgumentException
            throw new IllegalArgumentException("Movie not found");
        }
        //return updated movie
        return movie;
    }

    @Override
    public Movie delete(int id) throws IllegalArgumentException{
        Movie m = null;
        for (Movie movie : allMovies) {
            //If id is found then keep that movie in temporary object and then delete that movie
            if (movie.getId() == id) {
                m = movie;
                //exit the loop as we already found the movie
                break;
            }
        }
        if (m == null) {
            //If movie with given id is not found, throw IllegalArgumentException
            throw new IllegalArgumentException("Movie not found");
        }else{
            allMovies.remove(m);
        }
        return m;
    }

    @Override
    public boolean rentMovie(int movieId, String user) throws IllegalArgumentException {
        boolean rent = false;
        for(Movie m: allMovies){
            //If the movie with given id is present in the list and it's rentedByUser field is null then
            //set rentedByUser field as given user's name and return true
            if(m.getId() == movieId && m.getRentedByUser() == null){
                m.setRentedByUser(user);
                rent = true;
                break;
            }
        }
        if(!rent){
            //If movie is not present or if it is present but already rented by someone then throw exception
            throw new IllegalArgumentException("Movie is already rented by someone");
        }
        return rent;
    }

    //Initialize new movie parameters
    public void initMovie(Movie m, String title, int year, String language) {
        m.setId(count);
        m.setTitle(title);
        m.setLanguage(language);
        m.setYear(year);
        m.setRentedByUser(null);
        //Increment movie id
        count++;
    }

}
