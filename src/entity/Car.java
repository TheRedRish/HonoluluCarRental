package entity;

import enums.*;

import java.time.LocalDate;
import java.util.UUID;

public class Car {
    private final UUID id;
    private CarType carType;
    private CarBrand carBrand;
    private String model;
    private FuelType fuelType;
    private LocalDate firstRegistrationDate;
    private int odometer;
    private int motorSize;
    private GearType gearType;
    private boolean hasAircondition;
    private boolean hasCruiseControl;
    private SeatType seatType;
    private int seatAmount;
    private int horsePower;
    private String registrationNumber;

    public Car(CarBrand carBrand, String model, FuelType fuelType, LocalDate firstRegistrationDate, int odometer, int motorSize, GearType gearType, boolean hasAircondition, boolean hasCruiseControl, SeatType seatType, int seatAmount, int horsePower, String registrationNumber) {
        this.id = UUID.randomUUID();
        this.carBrand = carBrand;
        this.model = model;
        this.fuelType = fuelType;
        this.firstRegistrationDate = firstRegistrationDate;
        this.odometer = odometer;
        this.motorSize = motorSize;
        this.gearType = gearType;
        this.hasAircondition = hasAircondition;
        this.hasCruiseControl = hasCruiseControl;
        this.seatType = seatType;
        this.seatAmount = seatAmount;
        this.horsePower = horsePower;
        this.registrationNumber = registrationNumber;
        this.carType = findCarType();
    }

    public Car(UUID id, CarBrand carBrand, String model, FuelType fuelType, LocalDate firstRegistrationDate, int odometer, int motorSize, GearType gearType, boolean hasAircondition, boolean hasCruiseControl, SeatType seatType, int seatAmount, int horsePower, String registrationNumber) {
        this.id = id;
        this.carBrand = carBrand;
        this.model = model;
        this.fuelType = fuelType;
        this.firstRegistrationDate = firstRegistrationDate;
        this.odometer = odometer;
        this.motorSize = motorSize;
        this.gearType = gearType;
        this.hasAircondition = hasAircondition;
        this.hasCruiseControl = hasCruiseControl;
        this.seatType = seatType;
        this.seatAmount = seatAmount;
        this.horsePower = horsePower;
        this.registrationNumber = registrationNumber;

        this.carType = findCarType();
    }

    private CarType findCarType() {
        if (motorSize > 3000 && gearType.equals(GearType.AUTOMATIC) && hasAircondition && hasCruiseControl && seatType.equals(SeatType.LEATHER)) {
            return CarType.LUXURY;
        } else if(gearType.equals(GearType.MANUAL) && hasAircondition && seatAmount > 7){
            return CarType.FAMILY;
        } else if (gearType.equals(GearType.MANUAL) && horsePower > 200){
            return CarType.SPORT;
        } else {
            return CarType.UNCATEGORIZED;
        }
    }

    public CarType getCarType() {
        return carType;
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public LocalDate getFirstRegistrationDate() {
        return firstRegistrationDate;
    }

    public void setFirstRegistrationDate(LocalDate firstRegistrationDate) {
        this.firstRegistrationDate = firstRegistrationDate;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public int getMotorSize() {
        return motorSize;
    }

    public void setMotorSize(int motorSize) {
        this.motorSize = motorSize;
    }

    public GearType getGearType() {
        return gearType;
    }

    public void setGearType(GearType gearType) {
        this.gearType = gearType;
    }

    public boolean hasAircondition() {
        return hasAircondition;
    }

    public void setHasAircondition(boolean hasAircondition) {
        this.hasAircondition = hasAircondition;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public int getSeatAmount() {
        return seatAmount;
    }

    public void setSeatAmount(int seatAmount) {
        this.seatAmount = seatAmount;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public boolean hasCruiseControl() {
        return hasCruiseControl;
    }

    public void setHasCruiseControl(boolean hasCruiseControl) {
        this.hasCruiseControl = hasCruiseControl;
    }

    public UUID getId() {
        return id;
    }

    public String getConsoleSaveString(String argSeparator) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id.toString()).append(argSeparator);
        stringBuilder.append(carBrand.toString()).append(argSeparator);
        stringBuilder.append(model).append(argSeparator);
        stringBuilder.append(fuelType.toString()).append(argSeparator);
        stringBuilder.append(firstRegistrationDate.toString()).append(argSeparator);
        stringBuilder.append(odometer).append(argSeparator);
        stringBuilder.append(motorSize).append(argSeparator);
        stringBuilder.append(gearType.toString()).append(argSeparator);
        stringBuilder.append(hasAircondition).append(argSeparator);
        stringBuilder.append(hasCruiseControl).append(argSeparator);
        stringBuilder.append(seatType).append(argSeparator);
        stringBuilder.append(seatAmount).append(argSeparator);
        stringBuilder.append(horsePower).append(argSeparator);
        stringBuilder.append(registrationNumber);
        return stringBuilder.toString();
    }

    public void updateValues(Car otherCar) {
        this.carBrand = otherCar.getCarBrand();
        this.model = otherCar.getModel();
        this.fuelType = otherCar.getFuelType();
        this.firstRegistrationDate = otherCar.getFirstRegistrationDate();
        this.odometer = otherCar.getOdometer();
        this.motorSize = otherCar.getMotorSize();
        this.gearType = otherCar.getGearType();
        this.hasAircondition = otherCar.hasAircondition();
        this.hasCruiseControl = otherCar.hasCruiseControl();
        this.seatType = otherCar.getSeatType();
        this.seatAmount = otherCar.getSeatAmount();
        this.horsePower = otherCar.getHorsePower();
        this.registrationNumber = otherCar.getRegistrationNumber();
        this.carType = getCarType();
    }

    @Override
    public String toString() {
        return "Car Type: " + carType + "\n" +
                "Car Brand: " + carBrand + "\n" +
                "Model: " + model + "\n" +
                "Fuel Type: " + fuelType + "\n" +
                "First Registration Date: " + firstRegistrationDate + "\n" +
                "Odometer: " + odometer + " km\n" +
                "Motor Size: " + motorSize + " ccm\n" +
                "Gear Type: " + gearType + "\n" +
                "Air Condition: " + (hasAircondition ? "Yes" : "No") + "\n" +
                "Cruise Control: " + (hasCruiseControl ? "Yes" : "No") + "\n" +
                "Seat Type: " + seatType + "\n" +
                "Seat Amount: " + seatAmount + "\n" +
                "Horsepower: " + horsePower + " HP\n" +
                "Registration Number: " + registrationNumber;
    }

}
