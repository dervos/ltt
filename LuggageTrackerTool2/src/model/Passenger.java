package model;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

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


    // Constructors
    public Passenger() {
    }

    public Passenger(int passengerid, String surname, String name, String gender, String dob, String mobphone, String homephone, int homeaddress, int tempaddress, String insertion) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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

    public Integer getPassengerid() {
        return passengerid;
    }

    public void setPassengerid(Integer passengerid) {
        this.passengerid = passengerid;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getInsertion() {
        return insertion;
    }

    public void setInsertion(String insertion) {
        this.insertion = insertion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {   
        //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return this.dob;
    }

    public void setDob(Date dob) throws ParseException {
        this.dob = dob;
    }

    public String getMobphone() {
        return mobphone;
    }

    public void setMobphone(String mobphone) {
        this.mobphone = mobphone;
    }

    public String getHomephone() {
        return homephone;
    }

    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    public int getHomeaddressid() {
        return homeaddress.getAddressid();
    }

    public void setHomeaddressid(int homeaddressid) {
        homeaddress.setAddressid(homeaddressid);
    }

    public int getTempaddressid() {
        return tempaddress.getAddressid();
    }

    public void setTempaddressid(int tempaddressid) {
        tempaddress.setAddressid(tempaddressid);
    }
    
    public void setHomeaddress(model.Address homeaddress) {
        this.homeaddress = homeaddress;
    }

    public void setTempaddress(model.Address tempaddress) {
        this.tempaddress = tempaddress;
    }

    @Override
    public String toString() {
        return "model.Passenger[ passengerid=" + passengerid + " ]";
    }

    

}
