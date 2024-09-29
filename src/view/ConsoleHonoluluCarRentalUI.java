package view;

import utils.ConsoleMenu;

public class ConsoleHonoluluCarRentalUI implements IHonoluluCarRentalUI {
    private final ICarUI carUI;
    private final IRenterUI renterUI;
    private final IRentalContractUI rentalContractUI;

    public ConsoleHonoluluCarRentalUI( ICarUI carUI, IRenterUI renterUI, IRentalContractUI rentalContractUI) {
        this.carUI = carUI;
        this.renterUI = renterUI;
        this.rentalContractUI = rentalContractUI;
    }

    @Override
    public void run() {
        displayMainMenu();
    }

    @Override
    public void displayMainMenu() {
        ConsoleMenu mainMenu = new ConsoleMenu();
        mainMenu.addItem("Car menu", carUI::displayCarMenu);
        mainMenu.addItem("Renter menu", renterUI::displayRenterMenu);
        mainMenu.addItem("Rental contract menu", rentalContractUI::displayRentalContractMenu);

        mainMenu.run();
    }
}
