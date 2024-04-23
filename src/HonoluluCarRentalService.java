public class HonoluluCarRentalService {
    private final ICarRepository carRepository;
    private final IRentalContractRepository rentalContractRepository;
    private final IRenterRepository renterRepository;

    public HonoluluCarRentalService(ICarRepository carRepository, IRentalContractRepository rentalContractRepository, IRenterRepository renterRepository) {
        this.carRepository = carRepository;
        this.rentalContractRepository = rentalContractRepository;
        this.renterRepository = renterRepository;
    }
}
