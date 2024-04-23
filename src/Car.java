import enums.CarBrand;
import enums.CarType;
import enums.FuelType;
import enums.GearType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Car {
    private UUID id;
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
    private String seatType;
    private int seatAmount;
    private int horsePower;
    private String registrationNumber;

    public Car(CarBrand carBrand, String model, FuelType fuelType, LocalDate firstRegistrationDate, int odometer, int motorSize, GearType gearType, boolean hasAircondition, boolean hasCruiseControl, String seatType, int seatAmount, int horsePower, String registrationNumber) {
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
        // TODO set carType
        this.carType = findCarType();
    }

    public Car(UUID id, CarBrand carBrand, String model, FuelType fuelType, LocalDate firstRegistrationDate, int odometer, int motorSize, GearType gearType, boolean hasAircondition, boolean hasCruiseControl, String seatType, int seatAmount, int horsePower, String registrationNumber) {
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

    private CarType findCarType(){
        return CarType.LUXURY;
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

    public boolean isHasAircondition() {
        return hasAircondition;
    }

    public void setHasAircondition(boolean hasAircondition) {
        this.hasAircondition = hasAircondition;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
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

    public boolean isHasCruiseControl() {
        return hasCruiseControl;
    }

    public void setHasCruiseControl(boolean hasCruiseControl) {
        this.hasCruiseControl = hasCruiseControl;
    }

    public UUID getId() {
        return id;
    }

    public String toString(){
        //TODO add toString
        return "";
    }
}
