package com.mycompany.hotel;
import java.time.LocalDate;
import java.util.HashMap;
import java.io.*;
import java.util.ArrayList;

public class RoomServiceManager {
    private HashMap<Integer,Room > rooms =  new HashMap<>();
    private HashMap<Integer,Service> services = new HashMap<>();
    private HashMap<Integer, Reservation> reservations = new HashMap<>();
    // Use project-relative paths that match the actual layout: src/main/data/...
    private final String roomData = "src/main/java/data/roomdata.txt";
    private final String serviceData = "src/main/java/data/servicedata.txt";
    private final String reservationData = "src/main/java/data/reservationData.txt";

    public void addRoom (Room room){
        rooms.put(room.getRoomId(), room);
        saveRoomsToFile();
    }

    public void updateRoom(int roomId, Room room){
        if (rooms.containsKey(roomId)) {
            rooms.put(roomId, room);
            saveRoomsToFile();
        }
    }

    public void deleteRoom(int roomId){
        rooms.remove(roomId);
        saveRoomsToFile();
    }

    public void addService(Service service){
        services.put(service.getServiceId(), service);
        saveServicesToFile();
    }

    public void updateService(int serviceId ,Service service){
        if (services.containsKey(serviceId)) {
            services.put(serviceId, service);
            saveServicesToFile();
        }
    }

    public void deleteService(int serviceId){
        services.remove(serviceId);
        saveServicesToFile();
    }

    public void loadRoomsFromFile(){
        try(BufferedReader reader = new BufferedReader(new FileReader(roomData))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip empty or whitespace-only lines
                if (line.trim().isEmpty()) continue;

                String[] part = line.split(";");
                // Expect exactly 4 parts: id;type;price;isBusy
                if (part.length != 4) continue;

                try {
                    int roomId = Integer.parseInt(part[0].trim());
                    String roomType =  part[1].trim();
                    double price = Double.parseDouble(part[2].trim());
                    boolean isBusy = Boolean.parseBoolean(part[3].trim());

                    Room room = new Room(roomId, roomType, price, isBusy);
                    rooms.put(roomId, room);
                } catch (NumberFormatException ignore) {
                    // Skip malformed line
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveRoomsToFile(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(roomData))){
            for (Room room : rooms.values()) {
                String line = room.toString();
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public HashMap<Integer, Room> getRooms() {
        return rooms;
    }

    public HashMap<Integer, Service> getServices() {
        return services;
    }
    
    public HashMap<Integer, Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation) {
        reservations.put(reservation.getReservationId(), reservation);


        Room room = reservation.getRoom();
        room.setBusy(true);
        rooms.put(room.getRoomId(), room);

        saveReservationsToFile();
        saveRoomsToFile();
    }

    public void updateReservation(int reservationId, Reservation reservation) {
        if (reservations.containsKey(reservationId)) {
            reservations.put(reservationId, reservation);
            saveReservationsToFile();
        }
    }

    public void deleteReservation(int reservationId) {
        Reservation res = reservations.remove(reservationId);
        if (res != null) {
            Room room = res.getRoom();
            room.setBusy(false);
            rooms.put(room.getRoomId(), room);
        }
        saveReservationsToFile();
        saveRoomsToFile();
    }

    public void checkOutReservation(int reservationId) {
        Reservation res = reservations.get(reservationId);
        if (res != null) {
            Room room = res.getRoom();
            room.setBusy(false);
            rooms.put(room.getRoomId(), room);

            reservations.remove(reservationId);
            saveReservationsToFile();
            saveRoomsToFile();
        }
    }

    public void loadReservationsFromFile(HashMap<Integer, Customers> customers) {
        try (BufferedReader reader = new BufferedReader(new FileReader(reservationData))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip empty or whitespace-only lines
                if (line.trim().isEmpty()) continue;

                String[] p = line.split(";");
                // Expect at least 5 parts: resId;custId;roomId;checkIn;checkOut[;...]
                if (p.length < 5) continue;

                try {
                    int resId = Integer.parseInt(p[0].trim());
                    int custId = Integer.parseInt(p[1].trim());
                    int roomId = Integer.parseInt(p[2].trim());
                    LocalDate in = LocalDate.parse(p[3].trim());
                    LocalDate out = LocalDate.parse(p[4].trim());

                    Customers customer = customers.get(custId);
                    Room room = rooms.get(roomId);
                    if (customer == null || room == null) continue;

                    Reservation r = new Reservation(resId, customer, room, in, out);
                    reservations.put(resId, r);
                } catch (Exception ignore) {
                    // Skip malformed line
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveReservationsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reservationData))) {
            for (Reservation r : reservations.values()) {
                String line = r.toString();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Reservation> getUpcomingCheckouts(int days) {
        ArrayList<Reservation> list = new ArrayList<>();
        LocalDate now = LocalDate.now();

        for (Reservation r : reservations.values()) {
            if (!r.getCheckOut().isBefore(now) &&
                    !r.getCheckOut().isAfter(now.plusDays(days))) {
                list.add(r);
            }
        }
        return list;
    }

    public ArrayList<Room> filterRooms(String roomType, Boolean isBusy, Integer serviceId) {
        ArrayList<Room> result = new ArrayList<>();

        for (Room room : rooms.values()) {
            if (roomType != null && !room.getRoomType().equalsIgnoreCase(roomType))
                continue;
            if (isBusy != null && room.isBusy() != isBusy)
                continue;

            result.add(room);
        }
        return result;
    }

    public void loadServicesFromFile(){
        try(BufferedReader reader = new BufferedReader(new FileReader(serviceData))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip empty or whitespace-only lines
                if (line.trim().isEmpty()) continue;

                String[] part = line.split(";");
                // Expect exactly 4 parts: id;name;description;price
                if (part.length != 4) continue;

                try {
                    int serviceId = Integer.parseInt(part[0].trim());
                    String name = part[1].trim();
                    String description = part[2].trim();
                    double price = Double.parseDouble(part[3].trim());
                    Service service = new Service(serviceId, name, description, price);
                    services.put(serviceId, service);
                } catch (NumberFormatException ignore) {
                    // Skip malformed line
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveServicesToFile(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(serviceData))){
            for (Service service : services.values()) {
                String line = service.toString();
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
   
public ArrayList<Room> filterRooms(String roomType, Boolean onlyAvailable) {
    ArrayList<Room> filtered = new ArrayList<>();
    for (Room room : rooms.values()) {
        if (roomType != null && !roomType.isEmpty() && !room.getRoomType().equalsIgnoreCase(roomType)) {
            continue;
        }
        if (onlyAvailable != null && onlyAvailable && room.isBusy()) {
            continue;
        }
        filtered.add(room);
    }
    return filtered;
}
public boolean assignRoomToGuest(int roomId, Customers customer, LocalDate checkIn, LocalDate checkOut) {
    Room room = rooms.get(roomId);
    if (room == null || room.isBusy()) {
        return false; // room not available
    }

    room.setBusy(true); // mark room as occupied

    // create a new reservation
    int newResId = reservations.size() + 1; // simple auto-increment
    Reservation res = new Reservation(newResId, customer, room, checkIn, checkOut);
    reservations.put(newResId, res);

    // save to files
    saveRoomsToFile();
    saveReservationsToFile();
    return true;
}


}