package model;

import java.sql.Date;
import java.util.Collection;

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
    private Collection<Luggage> luggageCollection;

    // Constructors
    public Passenger() {
    }

    public Passenger(int passengerid, String surname, String name, String gender, Date dob, String mobphone, String homephone, int homeaddress, int tempaddress, String insertion) {
        this.passengerid = passengerid;
        this.surname = surname;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
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
        return dob;
    }

    public void setDob(Date dob) {
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
        return homeaddressid;
    }

    public void setHomeaddressid(int homeaddressid) {
        this.homeaddressid = homeaddressid;
    }

    public int getTempaddressid() {
        return tempaddressid;
    }

    public void setTempaddressid(int tempaddressid) {
        this.tempaddressid = tempaddressid;
    }

    public Collection<Luggage> getLuggageCollection() {
        return luggageCollection;
    }

    public void setLuggageCollection(Collection<Luggage> luggageCollection) {
        this.luggageCollection = luggageCollection;
    }

    @Override
    public String toString() {
        return "model.Passenger[ passengerid=" + passengerid + " ]";
    }

}
