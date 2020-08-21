package com.example.fractalmedia.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Languages {

    @SerializedName("iso_639_1")
    @Expose
    private String iso;
    private String name;

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getLanguagesWithDelimiter(List<Languages> list, final String separator) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder csvBuilder = new StringBuilder();
        for (Languages lenguage : list) {
            csvBuilder.append(lenguage.getName());
            csvBuilder.append(separator);
        }
        String csv = csvBuilder.toString();

        //Remove last comma
        return csv.substring(0, csv.length() - separator.length());
    }
}
