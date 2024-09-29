package view;

import entity.Car;
import service.CarService;
import enums.CarBrand;
import enums.FuelType;
import enums.GearType;
import enums.SeatType;
import utils.ConsoleMenu;
import utils.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsoleCarUI implements ICarUI{
    private final CarService carService;
    private static final String spacerString = "--------------------------------------";

    public ConsoleCarUI(CarService carService) {
        this.carService = carService;
    }

    //<editor-fold desc="car">
    @Override
    public void displayCarMenu() {
        ConsoleMenu carMenu = new ConsoleMenu();
        carMenu.addItem("Add car", this::displayAddCar);
        carMenu.addItem("Search cars", this::displaySearchCars);
        carMenu.addItem("Update car", this::displayUpdateCar);
        carMenu.addItem("Delete car", this::displayDeleteCar);

        carMenu.run();
    }

    @Override
    public void displayAddCar() {
        System.out.println("<------Add car------>");
        Car newCar = createAndAddCar();
        System.out.println(spacerString);
        System.out.println("New car has been added: ");
        System.out.println(newCar);
        System.out.println();
        //TODO add handling for crashing during all service calls.
    }

    public Car createAndAddCar() {
        System.out.println("<------Create car------>");
        System.out.println("Select car brand");
        CarBrand carBrand = Utils.selectObject(CarBrand.values());
        System.out.println("Enter model");
        String model = Utils.getStringInput("Car model: ");
        System.out.println("Select fuel type");
        FuelType fuelType = Utils.selectObject(FuelType.values());
        System.out.println("Select date of first registration: ");
        LocalDate firstRegistrationDate = Utils.getLocalDateInput();
        int odometer = Utils.getIntInput("Enter odometer reading: ");
        int moterSize = Utils.getIntInput("Enter motor size in ccm: ");
        System.out.println("Select gear type");
        GearType gearType = Utils.selectObject(GearType.values());
        boolean hasAircondition = Utils.getStringInput("Does the car have aircondition? y/n ", "Please answer y/n", new String[]{"y", "n"}).equalsIgnoreCase("y");
        boolean hasCruiseControl = Utils.getStringInput("Does the car have cruise control? y/n ", "Please answer y/n", new String[]{"y", "n"}).equalsIgnoreCase("y");
        System.out.println("Select seat type");
        SeatType seatType = Utils.selectObject(SeatType.values());
        int seatAmount = Utils.getIntInput("Enter seat amount: ");
        int horsePower = Utils.getIntInput("Enter horse power: ");
        String registrationNumber = Utils.getStringInput("Enter registration number: ");

        Car newCar = new Car(carBrand, model, fuelType, firstRegistrationDate, odometer, moterSize, gearType, hasAircondition, hasCruiseControl, seatType, seatAmount, horsePower, registrationNumber);

        carService.addCar(newCar);
        return newCar;
    }

    @Override
    public void displaySearchCars() {
        System.out.println("<------Search car------>");
        List<Car> carMatches = getCarListFromSearch();
        System.out.println("Here is the result of your search: ");
        for (Car car : carMatches) {
            System.out.println(spacerString);
            System.out.println(car);
        }
        System.out.println();
    }

    private List<Car> getCarListFromSearch() {
        System.out.println("What do you want to search by?");
        System.out.println("1. Registration Number");
        System.out.println("2. Car brand");
        System.out.println("3. Fuel type");
        System.out.println("4. Car model");
        System.out.println("5. Date range");

        List<Car> carMatches = new ArrayList<>();

        int userSelection = Utils.getIntInput("Enter the number of your choice: ", "Invalid selection", 1, 5);
        switch (userSelection) {
            case 1:
                String registrationNumber = Utils.getStringInput("Enter registration number: ");
                Car car = carService.getCarByRegistrationNumber(registrationNumber);
                if (car != null) {
                    carMatches.add(car);
                }
                break;
            case 2:
                System.out.println("Select car brand");
                CarBrand carBrand = Utils.selectObject(CarBrand.values());
                List<Car> carsByBrand = carService.getCarsByBrand(carBrand);
                if (!carsByBrand.isEmpty()) {
                    carMatches.addAll(carsByBrand);
                }
                break;
            case 3:
                System.out.println("Select fuel type");
                FuelType fuelType = Utils.selectObject(FuelType.values());
                List<Car> carsByFuelType = carService.getCarsByFuelType(fuelType);
                if (!carsByFuelType.isEmpty()) {
                    carMatches.addAll(carsByFuelType);
                }
                break;
            case 4:
                String model = Utils.getStringInput("Enter car model: ");
                List<Car> carsByModel = carService.getCarsByModel(model);
                if (!carsByModel.isEmpty()) {
                    carMatches.addAll(carsByModel);
                }
                break;
            case 5:
                boolean selectingDateRange;
                LocalDate startDate;
                LocalDate endDate;
                do {
                    System.out.println("Start date for search range");
                    startDate = Utils.getLocalDateInput();
                    System.out.println("End date for search range");
                    endDate = Utils.getLocalDateInput();
                    selectingDateRange = startDate.isAfter(endDate);
                    if (selectingDateRange) {
                        System.out.println("Start date is after end date");
                    }
                } while (selectingDateRange);
                List<Car> carsByRegistrationDateRange = carService.getCarsByRegistrationDateRange(startDate, endDate);
                if (!carsByRegistrationDateRange.isEmpty()) {
                    carMatches.addAll(carsByRegistrationDateRange);
                }
                break;
            default:
                break;
        }
        return carMatches;
    }

    public Car getCarFromSearch() {
        List<Car> carList = getCarListFromSearch();
        return Utils.selectObject(carList);
    }

    @Override
    public void displayUpdateCar() {
        System.out.println("<------Update car------>");
        System.out.println("Select car to update");
        Car carToUpdate = getCarFromSearch();
        if (carToUpdate == null) {
            System.out.println("Car not found in system");
            return;
        }
        boolean updatingCar;
        do {
            System.out.println("Select what to update on the car:");
            System.out.println("1. Car brand");
            System.out.println("2. Model");
            System.out.println("3. Fuel type");
            System.out.println("4. First registration date");
            System.out.println("5. Odometer reading");
            System.out.println("6. Moter size");
            System.out.println("7. Gear type");
            System.out.println("8. Aircondition");
            System.out.println("9. Cruise control");
            System.out.println("10. Seat type");
            System.out.println("11. Seat amount");
            System.out.println("12. Horse power");
            System.out.println("13. Registration number");
            int userSelection = Utils.getIntInput("Enter the number of your choice (0 to exit): ", "Invalid selection", 0, 13);
            switch (userSelection) {
                case 0:
                    return;
                case 1:
                    System.out.println("Select car brand");
                    CarBrand carBrand = Utils.selectObject(CarBrand.values());
                    carToUpdate.setCarBrand(carBrand);
                    break;
                case 2:
                    System.out.println("Enter model");
                    String model = Utils.getStringInput("Car model: ");
                    carToUpdate.setModel(model);
                    break;
                case 3:
                    System.out.println("Select fuel type");
                    FuelType fuelType = Utils.selectObject(FuelType.values());
                    carToUpdate.setFuelType(fuelType);
                    break;
                case 4:
                    System.out.println("Select date of first registration: ");
                    LocalDate firstRegistrationDate = Utils.getLocalDateInput();
                    carToUpdate.setFirstRegistrationDate(firstRegistrationDate);
                    break;
                case 5:
                    int odometer = Utils.getIntInput("Enter odometer reading: ");
                    carToUpdate.setOdometer(odometer);
                    break;
                case 6:
                    int moterSize = Utils.getIntInput("Enter motor size in ccm: ");
                    carToUpdate.setMotorSize(moterSize);
                    break;
                case 7:
                    System.out.println("Select gear type");
                    GearType gearType = Utils.selectObject(GearType.values());
                    carToUpdate.setGearType(gearType);
                    break;
                case 8:
                    boolean hasAircondition = Utils.getStringInput("Does the car have aircondition? y/n ", "Please answer y/n", new String[]{"y", "n"}).equalsIgnoreCase("y");
                    carToUpdate.setHasAircondition(hasAircondition);
                    break;
                case 9:
                    boolean hasCruiseControl = Utils.getStringInput("Does the car have cruise control? y/n ", "Please answer y/n", new String[]{"y", "n"}).equalsIgnoreCase("y");
                    carToUpdate.setHasCruiseControl(hasCruiseControl);
                    break;
                case 10:
                    System.out.println("Select seat type");
                    SeatType seatType = Utils.selectObject(SeatType.values());
                    carToUpdate.setSeatType(seatType);
                    break;
                case 11:
                    int seatAmount = Utils.getIntInput("Enter seat amount: ");
                    carToUpdate.setSeatAmount(seatAmount);
                    break;
                case 12:
                    int horsePower = Utils.getIntInput("Enter horse power: ");
                    carToUpdate.setHorsePower(horsePower);
                    break;
                case 13:
                    String registrationNumber = Utils.getStringInput("Enter registration number: ");
                    carToUpdate.setRegistrationNumber(registrationNumber);
                    break;
                default:
                    break;
            }
            updatingCar = Utils.getStringInput("Update more on the system? y/n ", "Please answer y/n", new String[]{"y", "n"}).equalsIgnoreCase("y");
        } while (updatingCar);

        carService.updateCar(carToUpdate);
        System.out.println("The car has been updated.");
        System.out.println();
    }

    @Override
    public void displayDeleteCar() {
        System.out.println("<------Delete car------>");
        System.out.println("Select car to delete");
        Car carToUpdate = getCarFromSearch();
        if (carToUpdate == null) {
            System.out.println("Car not found in system");
            return;
        }
        carService.deleteCarById(carToUpdate.getId());
        System.out.println("The car has been deleted");
        System.out.println();
    }
    //</editor-fold>

}
