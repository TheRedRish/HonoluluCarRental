import service.CarService;
import repository.*;
import service.RentalContractService;
import service.RenterService;
import view.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HonoluluCarRentalApp {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/honolulucarrental", "root", "74trEgem");
        } catch (SQLException e) {
            System.out.println("Unable to connect to database");
            System.out.println(e.getMessage());
            // TODO add logging system
        }

        // Repositories
        ICarRepository carRepository;
        IRenterRepository renterRepository;
        IRentalContractRepository rentalContractRepository;
        if (connection != null) {
            System.out.println("Started program with connection to DB");
            renterRepository = new DBRenterRepository(connection);
            carRepository = new DBCarRepository(connection);
            rentalContractRepository = new DBRentalContractRepository(connection, renterRepository, carRepository);
        } else {
            System.out.println("Started program with no connection to DB");
            carRepository = new FileCarRepository();
            renterRepository = new FileRenterRepository();
            rentalContractRepository = new FileRentalContractRepository(renterRepository, carRepository);
        }

        // Service
        CarService carService = new CarService(carRepository);
        RenterService renterService = new RenterService(renterRepository);
        RentalContractService rentalContractService = new RentalContractService(rentalContractRepository);
        // UI
        ConsoleCarUI carUI = new ConsoleCarUI(carService);
        ConsoleRenterUI renterUI = new ConsoleRenterUI(renterService);
        ConsoleRentalContractUI rentalContractUI = new ConsoleRentalContractUI(rentalContractService, carService, carUI,renterUI);
        IHonoluluCarRentalUI honoluluCarRentalUI = new ConsoleHonoluluCarRentalUI(carUI,renterUI,rentalContractUI);
        honoluluCarRentalUI.run();
    }
}
