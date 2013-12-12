package model;

import java.util.Date;
import java.util.List;
import main.Status;

/**
 *
 * @author Reinhard van Apeldoorn, Tomas Slaman
 */
public class Luggage {

    private Integer luggageid; //Just a counter from now on.
    private String luggageLabel;
    private String description;
    private Status luggagestatus;
    private String storagelocation;
    private String differentLocation;
    private Date dateAdded;
    private Integer passenger;

    private static java.util.List<model.Luggage> luggageList = new java.util.ArrayList<>();

    public Luggage() {
    }

    public Luggage(String luggageLabel, String description, String storagelocation, String differentLocation, Status luggagestatus, Integer passenger) {
        this.luggageLabel = luggageLabel;
        this.description = description;
        this.storagelocation = storagelocation;
        this.differentLocation = differentLocation;
        this.luggagestatus = luggagestatus;
        this.passenger = passenger;
    }

    public static List<model.Luggage> getLuggageList() {
        return luggageList;
    }

    public static void addToLuggageList(model.Luggage luggage) {
        luggageList.add(luggage);
    }

    /* Getters and setters */
    public Integer getLuggageid() {
        return luggageid;
    }

    public void setLuggageid(Integer luggageid) {
        this.luggageid = luggageid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStoragelocation() {
        return storagelocation;
    }

    public void setStoragelocation(String storagelocation) {

        this.storagelocation = storagelocation;
    }

    public Status getLuggagestatus() {
        return luggagestatus;
    }

    public void setLuggagestatus(String luggagestatus) {
        this.luggagestatus = Status.valueOf(luggagestatus);
    }

    public Integer getPassengerid() {
        return passenger;
    }

    public void setPassenger(Integer passenger) {
        this.passenger = passenger;
    }

    /**
     * @return the luggageLabel
     */
    public String getLuggageLabel() {
        return luggageLabel;
    }

    /**
     * @param luggageLabel the luggageLabel to set
     */
    public void setLuggageLabel(String luggageLabel) {
        this.luggageLabel = luggageLabel;
    }

    /**
     * @return the differentLocation
     */
    public String getDifferentLocation() {
        return differentLocation;
    }

    /**
     * @param differentLocation the differentLocation to set
     */
    public void setDifferentLocation(String differentLocation) {
        this.differentLocation = differentLocation;
    }

    /**
     * @return the dateAdded
     */
    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "Luggage{" + "luggageid=" + luggageid + ", luggageLabel=" + luggageLabel + ", description=" + description + ", storagelocation=" + storagelocation + ", differentLocation=" + differentLocation + ", luggagestatus=" + luggagestatus + ", dateAdded=" + dateAdded + ", passenger=" + passenger + '}';
    }
}
