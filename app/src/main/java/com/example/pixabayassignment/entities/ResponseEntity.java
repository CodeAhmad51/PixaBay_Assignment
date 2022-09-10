package com.example.pixabayassignment.entities;

import java.util.ArrayList;

public class ResponseEntity {
    long total , totalHits;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(long totalHits) {
        this.totalHits = totalHits;
    }

    public ArrayList<Object> getHits() {
        return hits;
    }

    public void setHits(ArrayList<Object> hits) {
        this.hits = hits;
    }

    ArrayList<Object> hits;

}
