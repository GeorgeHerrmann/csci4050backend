package com.csci4050.backend.data;

/**
 * The Address class represents a user's address.
 */
public class Address {
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;

    /**
     * Creates an Address object that will hold a user's address.
     * 
     * @param street The street address.
     * @param city  The city.
     * @param state The state.
     * @param zip The zip code.
     * @param country The country.
     */
    public Address(String street, String city, String state, String zip, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    /**
     * Returns the street address.
     * 
     * @return The street address.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Returns the city.
     * 
     * @return The city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the state.
     * 
     * @return The state.
     */
    public String getState() {
        return state;
    }

    /**
     * Returns the zip code.
     * 
     * @return The zip code.
     */
    public String getZip() {
        return zip;
    }

    /**
     * Returns the country.
     * 
     * @return The country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the street address.
     * 
     * @param street The street address.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Sets the city.
     * 
     * @param city The city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets the state.
     * 
     * @param state The state.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Sets the zip code.
     * 
     * @param zip The zip code.
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Sets the country.
     * 
     * @param country The country.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns a string representation of the Address object.
     * 
     * @return A string representation of the Address object.
     */
    @Override
    public String toString() {
        return "Address [city=" + city + ", country=" + country + ", state=" + state + ", street=" + street + ", zip="
                + zip + "]";
    }
}
