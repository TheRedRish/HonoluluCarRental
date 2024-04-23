public class ConsoleHonoluluCarRentalUI implements IHonoluluCarRentalUI{
    private HonoluluCarRentalService honoluluCarRentalService;

    public ConsoleHonoluluCarRentalUI(HonoluluCarRentalService honoluluCarRentalService) {
        this.honoluluCarRentalService = honoluluCarRentalService;
    }

    @Override
    public void run() {
        System.out.println("Hallo");
    }
}
