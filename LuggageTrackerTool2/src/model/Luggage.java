package model;

/**
 *
 * @author Reinhard van Apeldoorn, Tomas Slaman
 */
public class Luggage {

    //Fields ordered as in database
    private int luggageId;
    private String description;
    private String storageLocation;
    private String status;

    /**
     *
     * @param luggageId
     * @param description //color, size and anything out of the ordinary.
     * @param storageLocation //Location where luggage got stored after being
     * found
     * @param status //Found, lost, solved?
     */
    public Luggage(int luggageId, String description, String storageLocation, String status) {
        this.luggageId = luggageId;
        this.description = description;
        this.storageLocation = storageLocation;
        this.status = status;
    }

    public Luggage() {
    }

    // Getters and Setters
    /**
     * @return the luggageId
     */
    public int getLuggageId() {
        return luggageId;
    }

    /**
     * @param luggageId the luggageId to set
     */
    public void setLuggageId(int luggageId) {
        this.luggageId = luggageId;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the storageLocation
     */
    public String getStorageLocation() {
        return storageLocation;
    }

    /**
     * @param storageLocation the storageLocation to set
     */
    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
