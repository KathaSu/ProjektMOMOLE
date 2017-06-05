package com.momole.de.projektmomole.database.model;

/**
 * Created by manji on 10.05.2017.
 */

public class Momole {

    private long id = -1;
    private String date;
    private String time;
    private String food;
    private String comp;
    private String allgr;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getFood() {
        return food;
    }
    public void setFood(String food) { this.food = food; }

    public String getComp() {
        return comp;
    }
    public void setComp(String comp) { this.comp = comp; }

    public String getAllgr() {
        return allgr;
    }
    public void setAllgr(String allgr) { this.allgr = allgr; }
}
