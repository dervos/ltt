package model;

/**
 *
 * @author Reinhard van Apeldoorn, Tomas Slaman
 */
public class Address {

    private Integer addressid;
    private String streetname;
    private Integer streetnumber;
    private String zipcode;
    private String city;
    private String country;

    /**
     *
     * @param addressid
     * @param streetname
     * @param streetnumber
     * @param zipcode
     * @param city
     * @param country
     */
    public Address(Integer addressid, String streetname, Integer streetnumber, String zipcode, String city, String country) {
        this.addressid = addressid;
        this.streetname = streetname;
        this.streetnumber = streetnumber;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
    }

    /**
     *
     */
    public Address() {
    }

    /**
     *
     * @return
     */
    public Integer getAddressid() {
        return addressid;
    }

    /**
     *
     * @param addressid
     */
    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    /**
     *
     * @return
     */
    public String getStreetname() {
        return streetname;
    }

    /**
     *
     * @param streetname
     */
    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    /**
     *
     * @return
     */
    public Integer getStreetnumber() {
        return streetnumber;
    }

    /**
     *
     * @param streetnumber
     */
    public void setStreetnumber(Integer streetnumber) {
        this.streetnumber = streetnumber;
    }

    /**
     *
     * @return
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     *
     * @param zipcode
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" + "addressid=" + addressid + ", streetname=" + streetname + ", streetnumber=" + streetnumber + ", zipcode=" + zipcode + ", city=" + city + ", country=" + country + '}';
    }
}
