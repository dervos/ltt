package model;

/**
 *
 * @author Reinhard van Apeldoorn
 */
public class Luggage {
    private String luggageId;
    private String luggageDescription;
    private String luggageStorageLocation;

    // Constructor
    public Luggage(String luggageId, String luggageDescription, String luggageStorageLocation) {
        this.luggageId = luggageId;
        this.luggageDescription = luggageDescription;
        this.luggageStorageLocation = luggageStorageLocation;
    }

    // Getters and Setters
    /**
     * @return the luggageId
     */
    public String getLuggageId() {
        return luggageId;
    }

    /**
     * @param luggageId the luggageId to set
     */
    public void setLuggageId(String luggageId) {
        this.luggageId = luggageId;
    }

    /**
     * @return the luggageDescription
     */
    public String getLuggageDescription() {
        return luggageDescription;
    }

    /**
     * @param luggageDescription the luggageDescription to set
     */
    public void setLuggageDescription(String luggageDescription) {
        this.luggageDescription = luggageDescription;
    }

    /**
     * @return the luggageStorageLocation
     */
    public String getLuggageStorageLocation() {
        return luggageStorageLocation;
    }

    /**
     * @param luggageStorageLocation the luggageStorageLocation to set
     */
    public void setLuggageStorageLocation(String luggageStorageLocation) {
        this.luggageStorageLocation = luggageStorageLocation;
    }
    
    
    
}
