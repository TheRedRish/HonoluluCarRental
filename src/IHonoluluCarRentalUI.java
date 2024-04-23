public interface IHonoluluCarRentalUI {
    void run();
    void displayMainMenu();

    //<editor-fold desc="Car">
    void displayAddCar();

    void displaySearchCars();

    void displayUpdateCar();

    void displayDeleteCar();
    //</editor-fold>

    //<editor-fold desc="renter">
    void displayAddRenter();

    void displaySearchRenters();

    void displayUpdateRenter();

    void displayDeleteRenter();
    //</editor-fold>

    //<editor-fold desc="rental contract">
    void displayAddRentalContract();

    void displaySearchRentalContract();

    void displayUpdateRentalContract();

    void displayDeleteRentalContract();
    //</editor-fold>
}
