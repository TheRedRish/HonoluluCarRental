import java.time.LocalDateTime;
import java.util.List;

public interface IRentalContractRepository {
    void addRentalContract(RentalContract rentalContract);
    List<RentalContract> getAllRentalContracts();
    RentalContract getRentalContractByRenter(Renter renter);
    List<RentalContract> getRentalContractByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    void updateRentalContract(RentalContract updatedRentalContract);
    void deleteRentalContractByRenter(Renter renter);
    void deleteRentalContractByCar(Car car);
}
