package com.mycompany.hotel;

public class Room {
    private int roomId;
    private String roomType;
    private double price;
    boolean  isBusy;

    public Room(int roomId, String roomType, double price, boolean isBusy) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.price = price;
        this.isBusy = isBusy;
    }

    public Room() {}

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    @Override
    public String toString() {
        return roomId + ";" + roomType + ";" + price + ";" + isBusy;
    }
}

