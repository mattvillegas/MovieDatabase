/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataModel;

/**
 *
 * @author MatthewVillegas
 */
public class FilmDAO {
    private String filmName;
    private String filmRating;
    private String filmDescription;
    private Double filmPrice;
    
    /**
     *
     * @param inputName is what filmName is set to
     * @param inputRating is what filmRating is set to
     * @param inputDescription is what filmDescription is set to
     * @param inputPrice is what filmPrice is set to
     */
    // holds information for each film
    public FilmDAO(String inputName, String inputRating, String inputDescription, Double inputPrice)
    {
        filmName = inputName;
        filmRating = inputRating;
        filmDescription = inputDescription;
        filmPrice = inputPrice;
    }
    
    /**
     * No argument constructor
     */
    public FilmDAO()
    {
        
    }

    /**
     *
     * @return returns member variable
     */
    public String getFilmName() {
        return filmName;
    }

    /**
     *
     * @param filmName is what member variable is set to 
     */
    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    /**
     *
     * @return returns member variable
     */
    public String getFilmRating() {
        return filmRating;
    }

    /**
     *
     * @param filmRating is what member variable is set to
     */
    public void setFilmRating(String filmRating) {
        this.filmRating = filmRating;
    }

    /**
     *
     * @return returns the member variable
     */
    public String getFilmDescription() {
        return filmDescription;
    }

    /**
     *
     * @param filmDescription is what member variable is set to
     */
    public void setFilmDescription(String filmDescription) {
        this.filmDescription = filmDescription;
    }

    /**
     *
     * @return returns member variable
     */
    public Double getFilmPrice() {
        return filmPrice;
    }

    /**
     *
     * @param filmPrice is what member variable is set to
     */
    public void setFilmPrice(Double filmPrice) {
        this.filmPrice = filmPrice;
    }

  
}
