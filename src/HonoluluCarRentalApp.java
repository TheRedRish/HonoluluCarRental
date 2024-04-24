public class HonoluluCarRentalApp {
    public static void main(String[] args) {
        ICarRepository carRepository = new FileCarRepository();
        IRenterRepository renterRepository = new FileRenterRepository();
        // Ask if this is the right way to do injection of Cay
        IRentalContractRepository rentalContractRepository = new FileRentalContractRepository(renterRepository, carRepository);

        HonoluluCarRentalService honoluluCarRentalService = new HonoluluCarRentalService(carRepository, renterRepository, rentalContractRepository);

        IHonoluluCarRentalUI honoluluCarRentalUI = new ConsoleHonoluluCarRentalUI(honoluluCarRentalService);

        honoluluCarRentalUI.run();
    }
}
