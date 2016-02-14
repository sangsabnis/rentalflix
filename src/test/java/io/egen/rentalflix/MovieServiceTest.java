package io.egen.rentalflix;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test cases for MovieService
 */
public class MovieServiceTest {

    protected MovieService testMovieService = new MovieService();
    protected Movie testMovie1 = new Movie();
    protected Movie testMovie2 = new Movie();
    protected Movie testMovie3 = new Movie();
    protected Movie testMovie4 = new Movie();
    protected List<Movie> testAllMovies = new ArrayList();
    
    public void setup(){
        //Create test data
        testMovieService.initMovie(testMovie1, "TrialMovie1", 2012, "en");
        testMovieService.initMovie(testMovie2, "TrialMovie2", 2013, "en");
        testMovieService.initMovie(testMovie3, "TrialMovie3", 2014, "Hindi");
        testMovieService.initMovie(testMovie4, "TrialMovie1", 2000, "Spanish");
        
        MovieService.allMovies.add(testMovie1);
        MovieService.allMovies.add(testMovie2);
        MovieService.allMovies.add(testMovie3);
        MovieService.allMovies.add(testMovie4);
        testAllMovies.addAll(MovieService.allMovies);
    }
    
    
    //Test findAll method. Check whether any changes are taking place to the list after executing findAll method. 
    //As we are just retrieving the movies, there should not be any change.
    @Test
    public void testFindAll(){
        boolean testSame = true;
        testMovieService.findAll();
        for(int i=0; i<testAllMovies.size();i++){
            if(!testAllMovies.get(i).equals(MovieService.allMovies.get(i))){
                testSame = false;
            }
        }
        
        Assert.assertTrue(testSame);
    }
    //Test findByName method
    @Test
    public void testFindByName(){
        String name = "TrialMovie2";
        List<Movie> testByName = new ArrayList();
        //Check whether given name and name returned by method is same
        testByName = testMovieService.findByName(name);
        boolean testSame = false;
        for(Movie m: testByName){
            if(m.getTitle().equals(name)){
                testSame = true;
            }
        }
        Assert.assertTrue(testSame);
        
    }
    //Check findByName method when the given name is not present in the list
    @Test
    public void testFindByNameForNull(){
        String name = "NotPresent";
        List<Movie> testByName = new ArrayList();
        testByName = testMovieService.findByName(name);
        boolean testSame = false;
        //Check whether the list returned by the function is empty 
        if(testByName.isEmpty()){
            testSame = true;
        }
        Assert.assertTrue(testSame); 
    }
    //Test create movie method. Method should return the same movie after creating it. No changes should be made to 
    //any of the values.
    @Test
    public void testCreate(){
        Movie movie = new Movie();
        testMovieService.initMovie(movie, "TestMovieCreate", 2005, "en");
        Movie movie2 = testMovieService.create(movie);
        boolean testSame = false;
        if(movie.getId() == movie2.getId()){
            testSame = true;
        }
        Assert.assertTrue(testSame); 
    }
    //Test update method.
    @Test
    public void testUpdate(){
        Movie updateMovie = new Movie();
        updateMovie.setId(102);
        updateMovie.setTitle("UpdateMovie");
        updateMovie.setYear(2000);
        updateMovie.setLanguage("French");
        boolean testSame = false;
        Movie movie2 = null;
        try{
             movie2 = testMovieService.update(updateMovie);
             //Check is the id of the movie returned by the method is same as the given  id.
             if (updateMovie.getId() == movie2.getId()) {
                testSame = true;
            }
        }catch(IllegalArgumentException e){
            System.out.println(e);
        }
        finally{
            Assert.assertTrue(testSame);
        }
    }
    //Test update method for the case when given id is not present in the movie list. In that case IllegalArgumentException is
    //thrown. 
    @Test
    public void testUpdateNoMovie(){
        Movie updateMovie = new Movie();
        updateMovie.setId(100);
        updateMovie.setTitle("UpdateMovie");
        updateMovie.setYear(2000);
        updateMovie.setLanguage("French");
        boolean testSame = false;
        Movie movie2 = null;
        try{
             movie2 = testMovieService.update(updateMovie);
        }catch(IllegalArgumentException e){
            //As IllegalArgumentException is thrown check whether such message is thrown. If it is then test is successful
            if(e.getMessage().equals("Movie not found")){
                testSame=true;
            }
            System.out.println(e);
        }
        finally{
            Assert.assertTrue(testSame);
        }
    }
    //Test delete method
    @Test
    public void testDelete(){
        Movie movie = null;
        boolean testSame = false;
        try{
             movie = testMovieService.delete(103);
             //Check is the id of the movie returned by the method is same as the given  id.
             if(movie.getId() == 103){
                 testSame = true;
             }
        }catch(IllegalArgumentException e){
            System.out.println(e);
        }
        finally{
            Assert.assertTrue(testSame);
        }
    }
    //Test delete method for the case when given id is not present in the movie list. In that case IllegalArgumentException is
    //thrown. 
    @Test
    public void testDeleteNoMovie(){
        Movie movie = null;
        boolean testSame = false;
        try{
             movie = testMovieService.delete(100);
        }catch(IllegalArgumentException e){
            //As IllegalArgumentException is thrown check whether such message is thrown. If it is then test is successful
            if(e.getMessage().equals("Movie not found")){
                testSame=true;
            }
            System.out.println(e);
        }
        finally{
            Assert.assertTrue(testSame);
        }
    }
    //Test rentMovie method
    @Test
    public void testRentMovie(){
        setup();
        boolean testSame = false;
        try{
            testMovieService.findAll();
            //If the movie with given id is present in the list and it's rentedByUser field is null then
            //method returns true else false.
            testSame = testMovieService.rentMovie(104, "Sangram");
        }catch(IllegalArgumentException e){
            System.out.println(e);
        }finally{
            Assert.assertTrue(testSame);
        }
    }
    //Test rentMovie method when id is not present in the list
    @Test
    public void testRentMovieFail(){
        boolean testSame = false;
        try{
            System.out.println("Test 10");
            testMovieService.findAll();
            testMovieService.rentMovie(9, "Sangram");
        }catch(IllegalArgumentException e){
            //Compare the error message thrown by the method
            if(e.getMessage().equals("Movie is already rented by someone")){
                testSame = true;
            }
            System.out.println(e);
        }finally{
            Assert.assertTrue(testSame);
        }
    }
    
}
