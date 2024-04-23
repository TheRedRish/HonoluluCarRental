public class HonoluluCarRentalApp {
    public static void main(String[] args) {
        System.out.println("Hallo world");
        ICarRepository carRepository = new FileCarRepository();
        IRentalContractRepository rentalContractRepository = new FileRentalContractRepository();
        IRenterRepository renterRepository = new FileRenterRepository();

        HonoluluCarRentalService honoluluCarRentalService = new HonoluluCarRentalService(carRepository, rentalContractRepository, renterRepository);

        IHonoluluCarRentalUI honoluluCarRentalUI = new ConsoleHonoluluCarRentalUI(honoluluCarRentalService);

        honoluluCarRentalUI.run();
    }
}
