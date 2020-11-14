package com.example.tempmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DiscoverRoot {
    private String page;
    @SerializedName("total_results")
    private String totalResults;
    @SerializedName("total_pages")
    private String totalPages;

    private List<Movie> results;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
