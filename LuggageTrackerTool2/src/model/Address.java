package model;

/**
 *
 * @author Reinhard van Apeldoorn
 */
public class Address {
    private String country;
    private String city;
    private String streetName;
    private int streetNumber;
    private int postalCodeNumbers;
    private String postalCodeLetters;
    private String passengerId;

    // Constructors
    public Address(String country, String city, String streetName, int streetNumber, int postalCodeNumbers, String postalCodeLetters, String passengerId) {
        this.country = country;
        this.city = city;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCodeNumbers = postalCodeNumbers;
        this.postalCodeLetters = postalCodeLetters;
        this.passengerId = passengerId;
    }

    // Getters and Setters
   
    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the streetName
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * @param streetName the streetName to set
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * @return the streetNumber
     */
    public int getStreetNumber() {
        return streetNumber;
    }

    /**
     * @param streetNumber the streetNumber to set
     */
    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    /**
     * @return the postalCodeNumbers
     */
    public int getPostalCodeNumbers() {
        return postalCodeNumbers;
    }

    /**
     * @param postalCodeNumbers the postalCodeNumbers to set
     */
    public void setPostalCodeNumbers(int postalCodeNumbers) {
        this.postalCodeNumbers = postalCodeNumbers;
    }

    /**
     * @return the postalCodeLetters
     */
    public String getPostalCodeLetters() {
        return postalCodeLetters;
    }

    /**
     * @param postalCodeLetters the postalCodeLetters to set
     */
    public void setPostalCodeLetters(String postalCodeLetters) {
        this.postalCodeLetters = postalCodeLetters;
    }

    /**
     * @return the passengerId
     */
    public String getPassengerId() {
        return passengerId;
    }

    /**
     * @param passengerId the passengerId to set
     */
    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }
    
    
    
    
}



