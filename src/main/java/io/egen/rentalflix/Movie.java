package io.egen.rentalflix;

/**
 * Entity representing a movie.
 * Fields: id, title, year, language
 */
public class Movie {
	//POJO IMPLEMENTATION GOES HERE
    private int id;
    private String title;
    private int year;
    private String language;
    private String rentedByUser;    //To check whether movie is rented by someone. If null can be rented
    //Override toString method to print the movies in desired format
    @Override
    public String toString(){
        return this.id + "\t\t"+ this.title + "\t\t" + this.year + "\t\t" + this.rentedByUser;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRentedByUser() {
        return rentedByUser;
    }

    public void setRentedByUser(String rentedByUser) {
        this.rentedByUser = rentedByUser;
    }
}
