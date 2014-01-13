package model;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Reinhard van Apeldoorn, Tomas Slaman
 */
public class Passenger {

    private Integer passengerid;
    private String surname;
    private String insertion;
    private String name;
    private String gender;
    private Date dob;
    private String mobphone;
    private String homephone;
    private int homeaddressid;
    private int tempaddressid;
    private model.Address homeaddress;
    private model.Address tempaddress;
    
    private static java.util.List<model.Passenger> passengerList = new java.util.ArrayList<>();


    // Constructors
    public Passenger() {
    }

    /**
     * 
     * @param passengerid
     * @param surname
     * @param name
     * @param gender
     * @param dob
     * @param mobphone
     * @param homephone
     * @param homeaddress
     * @param tempaddress
     * @param insertion
     * @throws ParseException 
     */
    public Passenger(int passengerid, String surname, String name, String gender, String dob, String mobphone, String homephone, int homeaddress, int tempaddress, String insertion) throws ParseException {
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        this.passengerid = passengerid;
        this.surname = surname;
        this.name = name;
        this.gender = gender;
        this.dob = (Date)df.parse(dob);
        this.mobphone = mobphone;
        this.homephone = homephone;
        this.homeaddressid = homeaddress;
        this.tempaddressid = tempaddress;
        this.insertion = insertion;
    }

    /**
     * 
     * @return 
     */
    public static List<Passenger> getPassengerList() {
        return passengerList;
    }
    
    /**
     * 
     * @param passenger 
     */
    public static void addToPassengerList(model.Passenger passenger) {
        passengerList.add(passenger);
    }

    /**
     * 
     * @return 
     */
    public Integer getPassengerid() {
        return passengerid;
    }

    /**
     * 
     * @param passengerid 
     */
    public void setPassengerid(Integer passengerid) {
        this.passengerid = passengerid;
    }

    /**
     * 
     * @return 
     */
    public String getSurname() {
        return surname;
    }

    /**
     * 
     * @param surname 
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * 
     * @return 
     */
    public String getInsertion() {
        return insertion;
    }

    /**
     * 
     * @param insertion 
     */
    public void setInsertion(String insertion) {
        this.insertion = insertion;
    }

    /**
     * 
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return 
     */
    public String getGender() {
        return gender;
    }

    /**
     * 
     * @param gender 
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 
     * @return 
     */
    public Date getDob() {   
        //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return this.dob;
    }

    /**
     * 
     * @param dob 
     */
    public void setDob(Date dob){
        this.dob = dob;
    }

    /**
     * 
     * @return 
     */
    public String getMobphone() {
        return mobphone;
    }

    /**
     * 
     * @param mobphone 
     */
    public void setMobphone(String mobphone) {
        this.mobphone = mobphone;
    }

    /**
     * 
     * @return 
     */
    public String getHomephone() {
        return homephone;
    }

    /**
     * 
     * @param homephone 
     */
    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    /**
     * 
     * @return 
     */
    public int getHomeaddressid() {
        return homeaddress.getAddressid();
    }

    /**
     * 
     * @param homeaddressid 
     */
    public void setHomeaddressid(int homeaddressid) {
        homeaddress.setAddressid(homeaddressid);
    }

    /**
     * 
     * @return 
     */
    public int getTempaddressid() {
        return tempaddress.getAddressid();
    }

    /**
     * 
     * @param tempaddressid 
     */
    public void setTempaddressid(int tempaddressid) {
        tempaddress.setAddressid(tempaddressid);
    }
    
    /**
     * 
     * @param homeaddress 
     */
    public void setHomeaddress(model.Address homeaddress) {
        this.homeaddress = homeaddress;
    }

    /**
     * 
     * @param tempaddress 
     */
    public void setTempaddress(model.Address tempaddress) {
        this.tempaddress = tempaddress;
    }

    /**
     * 
     * @return 
     */
    public Address getHomeaddress() {
        return homeaddress;
    }

    /**
     * 
     * @return 
     */
    public Address getTempaddress() {
        return tempaddress;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "model.Passenger[ passengerid=" + passengerid + " ]";
    }

    

}
