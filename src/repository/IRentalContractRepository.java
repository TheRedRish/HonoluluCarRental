package repository;

import entity.RentalContract;
import entity.Renter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IRentalContractRepository {
    void addRentalContract(RentalContract rentalContract);
    List<RentalContract> getAllRentalContracts();
    List<RentalContract> getRentalContractsByRenter(Renter renter);
    RentalContract getRentalContractById(UUID id);
    List<RentalContract> getRentalContractByDateRange(LocalDate startDate, LocalDate endDate);
    void updateRentalContract(RentalContract updatedRentalContract);
    void deleteRentalContractByRenter(Renter renter);
    void deleteRentalContractById(UUID id);
}
