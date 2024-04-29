import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HonoluluCarRentalApp {
    // TODO Add package structure
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/honolulucarrental", "root", "74trEgem");
        } catch (SQLException e) {
            System.out.println("Unable to connect to database");
            // TODO add logging system
        }

        // Repositories
        ICarRepository carRepository;
        IRenterRepository renterRepository;
        IRentalContractRepository rentalContractRepository;
        if (connection != null) {
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
        ConsoleRentalContractUI rentalContractUI = new ConsoleRentalContractUI(rentalContractService, carUI,renterUI);
        IHonoluluCarRentalUI honoluluCarRentalUI = new ConsoleHonoluluCarRentalUI(carUI,renterUI,rentalContractUI);
        honoluluCarRentalUI.run();
    }
}
