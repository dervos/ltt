package model;

import java.util.List;

/**
 *
 * @author Reinhard van Apeldoorn, Tomas Slaman
 */
public class Luggage {

    private Integer luggageid;
    private String description;
    private String storagelocation;
    private String luggagestatus;
    private Integer passenger;
    
    private static java.util.List<model.Luggage> luggageList = new java.util.ArrayList<>();

    public Luggage() {
    }

    public Luggage(Integer luggageid, String description, String storagelocation, String luggagestatus, Integer passenger) {
        this.luggageid = luggageid;
        this.description = description;
        this.storagelocation = storagelocation;
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

    public String getLuggagestatus() {
        return luggagestatus;
    }

    public void setLuggagestatus(String luggagestatus) {
        this.luggagestatus = luggagestatus;
    }

    public Integer getPassengerid() {
        return passenger;
    }

    public void setPassenger(Integer passenger) {
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        return "Luggage{" + "luggageid=" + luggageid + ", description=" + description + ", storagelocation=" + storagelocation + ", luggagestatus=" + luggagestatus + ", passenger=" + passenger + '}';
    }

}
