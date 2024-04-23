import enums.CarBrand;
import enums.FuelType;
import enums.GearType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHonoluluCarRentalUI implements IHonoluluCarRentalUI {
    private HonoluluCarRentalService honoluluCarRentalService;

    public ConsoleHonoluluCarRentalUI(HonoluluCarRentalService honoluluCarRentalService) {
        this.honoluluCarRentalService = honoluluCarRentalService;
    }

    @Override
    public void run() {
        displayMainMenu();
    }

    @Override
    public void displayMainMenu() {
        ConsoleMenu mainMenu = new ConsoleMenu();
        mainMenu.addItem("Car menu", this::displayCarMenu);
        mainMenu.addItem("Renter menu", this::displayRenterMenu);
        mainMenu.addItem("Rental contract menu", this::displayRentalContractMenu);

        mainMenu.run();
    }

    //<editor-fold desc="car">
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
        String seatType = Utils.getStringInput("Enter seat type: ");
        int seatAmount = Utils.getIntInput("Enter seat amount: ");
        int horsePower = Utils.getIntInput("Enter horse power: ");
        String registrationNumber = Utils.getStringInput("Enter registration number: ");

        Car newCar = new Car(carBrand, model, fuelType, firstRegistrationDate, odometer, moterSize, gearType, hasAircondition, hasCruiseControl, seatType, seatAmount, horsePower, registrationNumber);
        honoluluCarRentalService.addCar(newCar);
        System.out.println("New car has been added: ");
        System.out.println(newCar);
    }

    @Override
    public void displaySearchCars() {
        System.out.println("<------Search car------>");
        System.out.println("What do you want to search by?");
        System.out.println("1. Registration Number");
        System.out.println("2. Car brand");
        System.out.println("3. Fuel type");
        System.out.println("4. Car model");
        System.out.println("5. Date range");

        List<Car> carMatches = new ArrayList<>();

        int userSelection = Utils.getIntInput("Enter the number of your choice (0 to exit): ", "Invalid selection", 0,5);
        switch (userSelection) {
            case 0:
                return;
            case 1:
                String registrationNumber = Utils.getStringInput("Enter registration number: ");
                Car car = honoluluCarRentalService.getCarByRegistrationNumber(registrationNumber);
                if (car != null){
                    carMatches.add(car);
                }
                break;
            case 2:
                System.out.println("Select car brand");
                CarBrand carBrand = Utils.selectObject(CarBrand.values());
                List<Car> carsByBrand = honoluluCarRentalService.getCarsByBrand(carBrand);
                if (!carsByBrand.isEmpty()){
                    carMatches.addAll(carsByBrand);
                }
                break;
            case 3:
                System.out.println("Select fuel type");
                FuelType fuelType = Utils.selectObject(FuelType.values());
                List<Car> carsByFuelType = honoluluCarRentalService.getCarsByFuelType(fuelType);
                if (!carsByFuelType.isEmpty()){
                    carMatches.addAll(carsByFuelType);
                }
                break;
            case 4:
                String model = Utils.getStringInput("Enter car model");
                List<Car> carsByModel = honoluluCarRentalService.getCarsByModel(model);
                if (!carsByModel.isEmpty()){
                    carMatches.addAll(carsByModel);
                }
                break;
            case 5:
                System.out.println("Start date for search range");
                LocalDate startDate = Utils.getLocalDateInput();
                System.out.println("End date for search range");
                LocalDate endDate = Utils.getLocalDateInput();
                List<Car> carsByRegistrationDateRange = honoluluCarRentalService.getCarsByRegistrationDateRange (startDate, endDate);
                if (!carsByRegistrationDateRange.isEmpty()){
                    carMatches.addAll(carsByRegistrationDateRange);
                }
                break;
            default:
                break;
        }

        System.out.println("Here is the result of your search: ");
        for (Car car : carMatches){
            System.out.println(car);
        }
    }

    @Override
    public void displayUpdateCar() {
        System.out.println("<------Update car------>");
        String registrationNumberForCar = Utils.getStringInput("Enter registration number of car: ");
        Car carToUpdate = honoluluCarRentalService.getCarByRegistrationNumber(registrationNumberForCar);
        if (carToUpdate == null){
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
                    String seatType = Utils.getStringInput("Enter seat type: ");
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
        } while(updatingCar);

        honoluluCarRentalService.updateCar(carToUpdate);
        System.out.println("The car has been updated.");
    }

    @Override
    public void displayDeleteCar() {
        System.out.println("<------Update car------>");
        String registrationNumberForCar = Utils.getStringInput("Enter registration number of car: ");
        honoluluCarRentalService.deleteCarByRegistrationNumber(registrationNumberForCar);
        System.out.println("The car has been deleted");
    }
    //</editor-fold>

    //<editor-fold desc="renter">
    public void displayRenterMenu() {
        ConsoleMenu renterMenu = new ConsoleMenu();
        renterMenu.addItem("Add renter", this::displayAddRenter);
        renterMenu.addItem("Search renters", this::displaySearchRenters);
        renterMenu.addItem("Update renter", this::displayUpdateRenter);
        renterMenu.addItem("Delete renter", this::displayDeleteRenter);

        renterMenu.run();
    }

    @Override
    public void displayAddRenter() {

    }

    @Override
    public void displaySearchRenters() {

    }

    @Override
    public void displayUpdateRenter() {

    }

    @Override
    public void displayDeleteRenter() {

    }
    //</editor-fold>

    //<editor-fold desc="rental contract">

    public void displayRentalContractMenu() {
        ConsoleMenu rentalContractMenu = new ConsoleMenu();
        rentalContractMenu.addItem("Add rental contract", this::displayAddRentalContract);
        rentalContractMenu.addItem("Search rental contracts", this::displaySearchRentalContract);
        rentalContractMenu.addItem("Update rental contract", this::displayUpdateRentalContract);
        rentalContractMenu.addItem("Delete rental contract", this::displayDeleteRentalContract);

        rentalContractMenu.run();
    }

    @Override
    public void displayAddRentalContract() {

    }

    @Override
    public void displaySearchRentalContract() {

    }

    @Override
    public void displayUpdateRentalContract() {

    }

    @Override
    public void displayDeleteRentalContract() {

    }
    //</editor-fold>
}
