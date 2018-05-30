package com.example.velizara_pc.national100places;

public class ObectRating {

    private String name;
    public static double rating;

    public ObectRating(){
        this.name = "";
        this.rating = 0;
    }

    public ObectRating(String name, double rating){
        this.name = name;
        this.rating = rating;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRating(double rating){
        this.rating = rating;
    }

    public double getRating(){
        return rating;
    }
}
