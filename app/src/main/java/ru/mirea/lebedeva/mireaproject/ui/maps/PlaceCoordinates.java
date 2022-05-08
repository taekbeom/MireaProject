package ru.mirea.lebedeva.mireaproject.ui.maps;


public class PlaceCoordinates {
    public String name;
    public int year;
    public String address;
    public double coordinate1;
    public double coordinate2;

    public PlaceCoordinates(String name, int year, String address, double coordinate1, double coordinate2) {
        this.name = name;
        this.year = year;
        this.address = address;
        this.coordinate1 = coordinate1;
        this.coordinate2 = coordinate2;
    }

    @Override
    public String toString(){
        return name + ", " + year + ", " + address + ", " + coordinate1 + ", " + coordinate2;
    }
}
