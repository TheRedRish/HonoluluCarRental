import enums.CarBrand;
import enums.FuelType;
import enums.GearType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class FileCarRepository implements ICarRepository {
    private static final String FILE_PATH = "src/repositoryFiles/carRepository.txt";
    private static final String argSeparator = "//##//";

    @Override
    public void addCar(Car car) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(car.getConsoleSaveString(argSeparator) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Car> getAllCars() {
        ArrayList<Car> cars = new ArrayList<>();
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
                String seatType = parts[10];
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

    @Override
    public Car getCarByRegistrationNumber(String registrationNumber) {
        return null;
    }

    @Override
    public List<Car> getCarsByBrand(CarBrand carBrand) {
        return null;
    }

    @Override
    public List<Car> getCarsByFuelType(FuelType fuelType) {
        return null;
    }

    @Override
    public List<Car> getCarsByModel(String model) {
        return null;
    }

    @Override
    public List<Car> getCarsByRegistrationDateRange(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public void updateCar(Car updatedCar) {

    }

    @Override
    public void deleteCarByRegistrationNumber(String registrationNumber) {

    }
}
