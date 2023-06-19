package org.example;

import java.time.LocalDate;

public class SalesContract extends Contract {
    private double salesTax;
    private double processingFee;
    private double recordingFee;
    private boolean financed;
    public void setFinanced(boolean financed) {
        this.financed = financed;
    }


    public double getProcessingFee() {
        return processingFee;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public boolean isFinanced() {
        return financed;
    }
    public double getSalesTaxAmount(){
        return  getVehicleSold().getPrice() * 0.05;
    }

    public SalesContract(LocalDate date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date.toString(), customerName, customerEmail, vehicleSold);
    }

    @Override
    public double getTotalPrice() {
        double vehiclePrice;
        double totalPrice;
        Vehicle vehicleSold = getVehicleSold();
        vehiclePrice = vehicleSold.getPrice();
        if (vehiclePrice < 10000) {
            totalPrice = vehiclePrice + 295; // processing fee for vehicles under 10,000
        } else {
            totalPrice = vehiclePrice + 495; // processing fee for vehicles over 10,000
        }
        totalPrice = totalPrice + 100; // Adding Recording fee of 100
        totalPrice = totalPrice + (vehiclePrice * 0.05); // 5% sales tax amount
        return totalPrice;
    }

    @Override
    public String getMonthlyPayment() {
        Vehicle vehicleSold = getVehicleSold();
        double vehiclePrice = vehicleSold.getPrice();
        double p = vehiclePrice;
        double r;
        double n;
        double t;
        double payment;
        if (financed) {
            if (vehiclePrice >= 10000) {
                r = 0.0425;
                t = 4;
                n = 12;
            } else {
                r = 0.0525;
                t = 2;
                n = 12;
            }
            double rOverN = r / n;
            double numerator = p * rOverN;
            double onePlusOverN = 1 + rOverN;
            double power = -t * n;
            double denominator = 1 - Math.pow(onePlusOverN, power);
            payment = numerator / denominator;
            return payment;
        } else {
            return 0;
        }
    }

    @Override
    public String getPersistenceString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SALE").append("|");
        builder.append(getDate()).append("|");
        builder.append(getCustomerName()).append("|");
        builder.append(getCustomerEmail()).append("|");
        builder.append(getVehicleSold().getVin()).append("|");
        builder.append(getVehicleSold().getYear()).append("|");
        builder.append(getVehicleSold().getMake()).append("|");
        builder.append(getVehicleSold().getModel()).append("|");
        builder.append(getVehicleSold().getVehicleType()).append("|");
        builder.append(getVehicleSold().getColor()).append("|");
        builder.append(getVehicleSold().getOdometer()).append("|");
        builder.append(getVehicleSold().getPrice()).append("|");
        builder.append(getSalesTaxAmount()).append("|");
        builder.append(getRecordingFee()).append("|");
        builder.append(getProcessingFee()).append("|");
        builder.append(getTotalPrice()).append("|");
        builder.append(isFinanced() ? "YES" : "NO").append("|");
        builder.append(getMonthlyPayment()).append("|").append(System.lineSeparator());

        return builder.toString();
    }


}