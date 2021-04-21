package TP01;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Park {

    private String name;
    private int ID;
    private ParkType type;
    private Point2D location;

    public Park(String name, int ID, ParkType type, Point2D location){
        this.name = name;
        this.ID = ID;
        this.type = type;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public ParkType getType() {
        return type;
    }

    public Point2D getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + " ID: " + this.ID + " type: " + this.type + " location: " + this.location;
    }

    public static void main(String[] args) {

    }
}
