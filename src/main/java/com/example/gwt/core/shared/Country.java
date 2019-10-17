package com.example.gwt.core.shared;

import java.io.Serializable;

public class Country implements Serializable {

    private int id;

    private String countryName;

    private String capital;
    private String continent;
    public Country() { }

    public Country(int id, String countryName, String capital, String continent) {
        this.id = id;
        this.countryName = countryName;
        this.capital = capital;
        this.continent = continent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

}
