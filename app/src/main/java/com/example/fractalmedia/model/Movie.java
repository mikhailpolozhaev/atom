package com.example.fractalmedia.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {

    private String title;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    private String homepage;
    private int budget;
    private List<Genres> genres;
    private String overview;
    private Double popularity;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("production_companies")
    @Expose
    private List<Companies> productionCompanies;
    @SerializedName("spoken_languages")
    @Expose
    private List<Languages> spokenLanguages;
    private String status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<Companies> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<Companies> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<Languages> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<Languages> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", homepage='" + homepage + '\'' +
                ", budget=" + budget +
                ", genres=" + genres +
                ", overview='" + overview + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + posterPath + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", productionCompanies=" + productionCompanies +
                ", spokenLanguages=" + spokenLanguages +
                ", status='" + status + '\'' +
                '}';
    }
}
