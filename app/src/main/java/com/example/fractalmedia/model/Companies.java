package com.example.fractalmedia.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Companies {

    private int id;
    @SerializedName("logo_path")
    @Expose
    private String logoPath;
    private String name;
    @SerializedName("origin_country")
    @Expose
    private String originCountry;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public static String getCompaniesWithDelimiter(List<Companies> list, final String separator) {

        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder csvBuilder = new StringBuilder();
        for (Companies companies : list) {
            csvBuilder.append(companies.getName());
            csvBuilder.append(separator);
        }
        String csv = csvBuilder.toString();

        //Remove last comma
        return csv.substring(0, csv.length() - separator.length());
    }
}
