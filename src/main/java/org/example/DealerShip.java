package org.example;

import java.util.ArrayList;
import java.util.List;

public class DealerShip {

    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory = new ArrayList<>();

    // constructor
    public DealerShip() {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    // getters
    public List<Vehicle> getVehiclesByPrice(double min, double max) {

        List<Vehicle> values = new ArrayList<Vehicle>();
        for (Vehicle v : inventory) {
            if (v.getPrice() >= min && v.getPrice() <= max) {
                values.add(v);
            }
        }
        return values;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> values = new ArrayList<Vehicle>();
        for (Vehicle v : inventory) {
            if (v.getMake().equalsIgnoreCase(make) || v.getModel().equalsIgnoreCase(model)) {
                values.add(v);
            }
        }
        return values;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> values = new ArrayList<Vehicle>();
        for (Vehicle v : inventory) {
            if (v.getColor().equalsIgnoreCase(color)) {
                values.add(v);
            }
        }
        return values;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        List<Vehicle> values = new ArrayList<Vehicle>();
        for (Vehicle v : inventory) {
            if (v.getOdometer() >= min && v.getOdometer() <= max) {
                values.add(v);
            }
        }
        return values;
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        List<Vehicle> values = new ArrayList<Vehicle>();
        for (Vehicle v : inventory) {
            if (v.getYear() >= min && v.getYear() <= max) {
                values.add(v);
            }
        }
        return values;
    }

//    public List<Vehicle> getVehiclesByType(String vehicleType) {
//        List<Vehicle> values = new ArrayList<Vehicle>();
//        for (Vehicle v : inventory) {
//            if (v.getColor().equalsIgnoreCase(vehicleType)) {
//                values.add(v);
//            }
//        }
//        return values;
//    }
public List<Vehicle> getVehiclesByType(String type) {
    List<Vehicle> vehiclesByType = new ArrayList<>();
    for (Vehicle vehicle : inventory) {
        if (vehicle.getVehicleType().equalsIgnoreCase(type)) {
            vehiclesByType.add(vehicle);
        }
    }
    return vehiclesByType;
}

    public Vehicle getVehicleByVin(String vin) {
        for (Vehicle v : inventory) {
            if (v.getVin().equalsIgnoreCase(vin)) {
                return v;
            }

        }
        return null;
    }


    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    // other methods
    public void addVehicle(Vehicle anotherVehicle) {
        inventory.add(anotherVehicle);
    }

    public void removeVehicle(Vehicle anotherVehicle) {
        inventory.remove(anotherVehicle);
    }
}