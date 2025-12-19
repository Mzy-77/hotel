package com.mycompany.hotel;
import java.time.*;
import java.util.ArrayList;

public class Reservation {
    private int reservationId;
    private Customers customer;
    private Room room;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private ArrayList<Service> services = new ArrayList<>();

    public Reservation(int reservationId, Customers customer, Room room,
                       LocalDate checkIn, LocalDate checkOut) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getReservationId() {
        return reservationId;
    }

    public Customers getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }
     
    public ArrayList<Service> getServices() { return services; }
    public void setServices(ArrayList<Service> services) { this.services = services; }

    @Override
   public String toString() {
        // تخزين الخدمات كأرقام مفصولة بفاصلة
        String serviceIds = "";
        for (Service s : services) {
            serviceIds += s.getServiceId() + ",";
        }
        if (!serviceIds.isEmpty()) serviceIds = serviceIds.substring(0, serviceIds.length() - 1);

        return reservationId + ";" + customer.getId() + ";" + room.getRoomId() + ";" +
               checkIn + ";" + checkOut + ";" + serviceIds;
    }
}