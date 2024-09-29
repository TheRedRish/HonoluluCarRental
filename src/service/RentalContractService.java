package service;

import entity.RentalContract;
import entity.Renter;
import repository.IRentalContractRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class RentalContractService {
    private final IRentalContractRepository rentalContractRepository;

    public RentalContractService(IRentalContractRepository rentalContractRepository) {
        this.rentalContractRepository = rentalContractRepository;
    }

    //<editor-fold desc="rental contract">
    public void addRentalContract(RentalContract rentalContract) {
        rentalContractRepository.addRentalContract(rentalContract);
    }

    public List<RentalContract> getAllRentalContracts() {
        return rentalContractRepository.getAllRentalContracts();
    }

    public List<RentalContract> getRentalContractsByRenter(Renter renter) {
        return rentalContractRepository.getRentalContractsByRenter(renter);
    }

    public List<RentalContract> getRentalContractByDateRange(LocalDate fromDate, LocalDate endDate) {
        return rentalContractRepository.getRentalContractByDateRange(fromDate, endDate);
    }

    public void updateRentalContract(RentalContract updatedRentalContract) {
        rentalContractRepository.updateRentalContract(updatedRentalContract);
    }

    public void deleteRentalContractByRenter(Renter renter) {
        rentalContractRepository.deleteRentalContractByRenter(renter);
    }

    public void deleteRentalContractById(UUID id){
        rentalContractRepository.deleteRentalContractById(id);
    }
    //</editor-fold>
}
