package com.example.velizara_pc.national100places;

public class ObectVisited {

    private String name;
    private boolean isVisited;

    public ObectVisited(){
        this.name = "";
        this.isVisited = false;
    }
    public ObectVisited(String name, boolean isVis){
        this.name = name;
        this.isVisited = isVis;
    }

    public void setName(String obectName){
        this.name = obectName;
    }

    public String getName() {
        return name;
    }

    public void setIsVisited(boolean isVisited){
        this.isVisited = isVisited;
    }

    public boolean getIsVisited(){
        return isVisited;
    }
}
