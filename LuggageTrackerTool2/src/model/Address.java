package model;

/**
 *
 * @author Reinhard van Apeldoorn, Tomas Slaman
 */
public class Address {

    //Fields ordered as in database
    private int addressId;
    private String streetName;
    private int streetNumber;
    private String zipCode;
    private String city;
    private String country;

    // Constructors
    public Address() {

    }

    public Address(int addressId, String streetName, int streetNumber, String zipCode, String city, String country) {
        this.addressId = addressId;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }

    // Getters and Setters
    /**
     *
     * @return
     */
    public int getAddressId() {
        return addressId;
    }

    /**
     *
     * @param addressId
     */
    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

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
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
