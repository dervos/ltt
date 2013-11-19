package model;

/**
 *
 * @author Reinhard van Apeldoorn
 */
public class Passenger 
{
    private String passengerId;
    private String firstName;
    private String insertion;
    private String surname;
    private String gender;
    private String dateOfBirth;
    private String mobileNumber;
    private String privateNumber;

    public Passenger() {
    }

    
    
    // Constructors
    public Passenger(String passengerId, String firstName, String insertion, String surname, String gender, String dateOfBirth, String mobileNumber, String privateNumber) {
        this.passengerId = passengerId;
        this.firstName = firstName;
        this.insertion = insertion;
        this.surname = surname;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.privateNumber = privateNumber;
    }
    
    // Getters and Setters
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
     * @return the insertion
     */
    public String getInsertion() {
        return insertion;
    }

    /**
     * @param insertion the insertion to set
     */
    public void setInsertion(String insertion) {
        this.insertion = insertion;
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
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
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
    
    
}
