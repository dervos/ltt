package model;

import java.util.Date;

/**
 *
 * @author Reinhard van Apeldoorn, Tomas Slaman
 */

public class Passenger {

    //Fields ordered as in database
    private int passengerId;
    private String surname;
    private String firstName;
    private String gender;
    private Date dateOfBirth;
    private String mobileNumber;
    private String privateNumber; /*Home telephone number or mobile one if
     passenger hasn't got a phone at his house.*/

    private int homeAddressId;
    private int temporaryAddressId;
    private String tussenvoegsel; /*Means a sort of insertion, example;
     Jaap van Pot, "van" being the tussenvoegsel*/


    // Constructors
    public Passenger() {
    }

    public Passenger(int passengerId, String surname, String firstName, String gender, Date dateOfBirth, String mobileNumber, String privateNumber, int homeAddressId, int temporaryAddressId, String tussenvoegsel) {
        this.passengerId = passengerId;
        this.surname = surname;
        this.firstName = firstName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.privateNumber = privateNumber;
        this.homeAddressId = homeAddressId;
        this.temporaryAddressId = temporaryAddressId;
        this.tussenvoegsel = tussenvoegsel;
    }

    /**
     * @return the passengerId
     */
    public int getPassengerId() {
        return passengerId;
    }

    /**
     * @param passengerId the passengerId to set
     */
    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * @param mobileNumber the mobileNumber to set
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * @return the privateNumber
     */
    public String getPrivateNumber() {
        return privateNumber;
    }

    /**
     * @param privateNumber the privateNumber to set
     */
    public void setPrivateNumber(String privateNumber) {
        this.privateNumber = privateNumber;
    }

    

    /**
     * @return the homeAddressId
     */
    public int getHomeAddressId() {
        return homeAddressId;
    }

    /**
     * @param homeAddressId the homeAddressId to set
     */
    public void setHomeAddressId(int homeAddressId) {
        this.homeAddressId = homeAddressId;
    }

    /**
     * @return the temporaryAddressId
     */
    public int getTemporaryAddressId() {
        return temporaryAddressId;
    }

    /**
     * @param temporaryAddressId the temporaryAddressId to set
     */
    public void setTemporaryAddressId(int temporaryAddressId) {
        this.temporaryAddressId = temporaryAddressId;
    }

    /**
     * @return the tussenvoegsel
     */
    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    /**
     * @param tussenvoegsel the tussenvoegsel to set
     */
    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    @Override
    public String toString() {
        return "Passenger{" + "passengerId=" + passengerId + ", surname=" + surname + ", firstName=" + firstName + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", mobileNumber=" + mobileNumber + ", privateNumber=" + privateNumber + ", homeAddressId=" + homeAddressId + ", temporaryAddressId=" + temporaryAddressId + ", tussenvoegsel=" + tussenvoegsel + '}';
    }
            
            
}
