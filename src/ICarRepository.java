import enums.CarBrand;
import enums.FuelType;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ICarRepository {
    void addCar(Car car);
    List<Car> getAllCars();
    Car getCarByRegistrationNumber(String registrationNumber) ;
    Car getCarById(UUID id);
    List<Car> getCarsByBrand(CarBrand carBrand);
    List<Car> getCarsByFuelType(FuelType fuelType);
    List<Car> getCarsByModel(String model);
    List<Car> getCarsByRegistrationDateRange(LocalDate startDate, LocalDate endDate);
    void updateCar(Car updatedCar);
    void deleteCarByRegistrationNumber(String registrationNumber);
    void deleteCarById(UUID id);
}
