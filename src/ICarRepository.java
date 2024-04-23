import enums.CarBrand;
import enums.FuelType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ICarRepository {
    void addCar(Car car);
    List<Car> getAllCars();
    Car getCarByRegistrationNumber(String registrationNumber);
    List<Car> getCarsByBrand(CarBrand carBrand);
    List<Car> getCarsByFuelType(FuelType fuelType);
    List<Car> getCarsByModel(String model);
    List<Car> getCarsByRegistrationDateRange(LocalDate startDate, LocalDate endDate);
    void updateCar(Car updatedCar);
    void deleteCarByRegistrationNumber(String registrationNumber);
}
