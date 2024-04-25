import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HonoluluCarRentalApp {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/honolulucarrental", "root", "74trEgem");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ICarRepository carRepository;
        if (connection != null) {
            carRepository = new DBCarRepository(connection);
        } else {
            carRepository = new FileCarRepository();
        }

        IRenterRepository renterRepository = new FileRenterRepository();
        IRentalContractRepository rentalContractRepository = new FileRentalContractRepository(renterRepository, carRepository);

        HonoluluCarRentalService honoluluCarRentalService = new HonoluluCarRentalService(carRepository, renterRepository, rentalContractRepository);

        IHonoluluCarRentalUI honoluluCarRentalUI = new ConsoleHonoluluCarRentalUI(honoluluCarRentalService);

        honoluluCarRentalUI.run();


    }
}
