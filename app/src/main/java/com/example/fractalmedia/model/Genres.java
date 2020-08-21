package com.example.fractalmedia.model;

import java.util.List;

public class Genres {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getGenresWithDelimiter(List<Genres> list, final String separation) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder csvBuilder = new StringBuilder();
        for (Genres genre : list) {
            csvBuilder.append(genre.getName());
            csvBuilder.append(separation);
        }
        String csv = csvBuilder.toString();

        //Remove last comma
        return csv.substring(0, csv.length() - separation.length());
    }
}
