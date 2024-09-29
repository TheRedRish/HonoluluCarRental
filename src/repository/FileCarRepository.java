package repository;

import entity.Car;
import enums.CarBrand;
import enums.FuelType;
import enums.GearType;
import enums.SeatType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class FileCarRepository implements ICarRepository {
    private static final String FILE_PATH = "src/repository.repositoryFiles/carRepository.txt";
    private static final String argSeparator = "//##//";

    public FileCarRepository() {
    }

    /**
     * Adds car to file
     *
     * @param car not null
     */
    @Override
    public void addCar(Car car) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(car.getConsoleSaveString(argSeparator) + "\n");
            writer.close(); // It is not redundant to close the writer, it wont write if its not closed.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets list of cars from file
     *
     * @return List of cars, empty list if no cars in file
     */
    @Override
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        try (Scanner reader = new Scanner(new File(FILE_PATH))) {
            String line;
            while (reader.hasNextLine()) {
                line = reader.nextLine();
                String[] parts = line.split(argSeparator);

                // Order of parts seen in Cars class in getConsoleSaveString method
                UUID id = UUID.fromString(parts[0]);
                CarBrand carBrand = CarBrand.valueOf(parts[1]);
                String model = parts[2];
                FuelType fuelType = FuelType.valueOf(parts[3]);
                LocalDate firstRegistrationDate = LocalDate.parse(parts[4]);
                int odometer = Integer.parseInt(parts[5]);
                int moterSize = Integer.parseInt(parts[6]);
                GearType gearType = GearType.valueOf(parts[7]);
                boolean hasAircondition = Boolean.parseBoolean(parts[8]);
                boolean hasCruiseControl = Boolean.parseBoolean(parts[9]);
                SeatType seatType = SeatType.valueOf(parts[10]);
                int seatAmount = Integer.parseInt(parts[11]);
                int horsePower = Integer.parseInt(parts[12]);
                String registrationNumber = parts[13];

                cars.add(new Car(id, carBrand, model, fuelType, firstRegistrationDate, odometer, moterSize, gearType, hasAircondition, hasCruiseControl, seatType, seatAmount, horsePower, registrationNumber));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cars;
    }

    /**
     * Gets car by registrationNumber
     *
     * @param registrationNumber not null
     * @return Car or null if no car found
     */
    @Override
    public Car getCarByRegistrationNumber(String registrationNumber) {
        List<Car> allCars = getAllCars();
        for (Car car : allCars) {
            if (car.getRegistrationNumber().equals(registrationNumber)) {
                return car;
            }
        }
        return null;
    }

    @Override
    public Car getCarById(UUID id) {
        List<Car> allCars = getAllCars();
        for (Car car : allCars) {
            if (car.getId().equals(id)) {
                return car;
            }
        }
        return null;
    }

    /**
     * Gets all cars matching CarBrand given
     *
     * @param carBrand not null
     * @return List of cars, empty list if no cars matching in file
     */
    @Override
    public List<Car> getCarsByBrand(CarBrand carBrand) {
        List<Car> allCars = getAllCars();
        List<Car> carMatchingCarBrand = new ArrayList<>();
        for (Car car : allCars) {
            if (car.getCarBrand() == carBrand) {
                carMatchingCarBrand.add(car);
            }
        }
        return carMatchingCarBrand;
    }

    /**
     * Gets all cars matching FuelType given
     *
     * @param fuelType not null
     * @return List of cars, empty list if no cars matching in file
     */
    @Override
    public List<Car> getCarsByFuelType(FuelType fuelType) {
        List<Car> allCars = getAllCars();
        List<Car> carMatchingFuelType = new ArrayList<>();
        for (Car car : allCars) {
            if (car.getFuelType() == fuelType) {
                carMatchingFuelType.add(car);
            }
        }
        return carMatchingFuelType;
    }

    /**
     * Gets all cars matching model given
     *
     * @param model not null
     * @return List of cars, empty list if no cars matching in file
     */
    @Override
    public List<Car> getCarsByModel(String model) {
        List<Car> allCars = getAllCars();
        List<Car> carMatchingModel = new ArrayList<>();
        for (Car car : allCars) {
            if (car.getModel().equalsIgnoreCase(model)) {
                carMatchingModel.add(car);
            }
        }
        return carMatchingModel;
    }

    /**
     * @param startDate not null
     * @param endDate   not null
     * @return List of cars, empty list if no cars matching range in file
     */
    @Override
    public List<Car> getCarsByRegistrationDateRange(LocalDate startDate, LocalDate endDate) {
        List<Car> allCars = getAllCars();
        List<Car> carMatchingModel = new ArrayList<>();
        for (Car car : allCars) {
            if (car.getFirstRegistrationDate().isAfter(startDate) && car.getFirstRegistrationDate().isBefore(endDate)) {
                carMatchingModel.add(car);
            }
        }
        return carMatchingModel;
    }

    /**
     * Updates car with same ID as updatedCar
     *
     * @param updatedCar with changes to values
     */
    @Override
    public void updateCar(Car updatedCar) {
        List<Car> allCars = getAllCars();
        for (Car car : allCars) {
            if (car.getId().equals(updatedCar.getId())) {
                car.updateValues(updatedCar);
                break;
            }
        }
        saveAllCars(allCars);
    }

    /**
     * Removes car if it exists in file
     * @param registrationNumber of car to delete
     */
    @Override
    public void deleteCarByRegistrationNumber(String registrationNumber) {
        List<Car> allCars = getAllCars();
        if (allCars.removeIf(car -> car.getRegistrationNumber().equalsIgnoreCase(registrationNumber))) {
            saveAllCars(allCars);
        }
    }

    @Override
    public void deleteCarById(UUID id) {
        List<Car> allCars = getAllCars();
        if (allCars.removeIf(car -> car.getId().equals(id))) {
            saveAllCars(allCars);
        }
    }

    private void saveAllCars(List<Car> carList) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Car car : carList) {
                writer.write(car.getConsoleSaveString(argSeparator) + "\n");
            }
            writer.close(); // Have to close writer to actually write
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
