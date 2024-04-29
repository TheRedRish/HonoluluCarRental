import enums.CarBrand;
import enums.FuelType;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CarService {
    private final ICarRepository carRepository;

    public CarService(ICarRepository carRepository) {
        this.carRepository = carRepository;
    }

    //<editor-fold desc="car">
    public void addCar(Car car) {
        carRepository.addCar(car);
    }

    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    public Car getCarByRegistrationNumber(String registrationNumber) {
        return carRepository.getCarByRegistrationNumber(registrationNumber);
    }

    public List<Car> getCarsByBrand(CarBrand carBrand) {
        return carRepository.getCarsByBrand(carBrand);
    }

    public List<Car> getCarsByFuelType(FuelType fuelType) {
        return carRepository.getCarsByFuelType(fuelType);
    }

    public List<Car> getCarsByModel(String model) {
        return carRepository.getCarsByModel(model);
    }

    public List<Car> getCarsByRegistrationDateRange(LocalDate startDate, LocalDate endDate) {
        return carRepository.getCarsByRegistrationDateRange(startDate, endDate);
    }

    public void updateCar(Car updatedCar) {
        carRepository.updateCar(updatedCar);
    }

    public void deleteCarByRegistrationNumber(String registrationNumber) {
        carRepository.deleteCarByRegistrationNumber(registrationNumber);
    }

    public void deleteCarById(UUID id){
        carRepository.deleteCarById(id);
    }
    //</editor-fold>

}
