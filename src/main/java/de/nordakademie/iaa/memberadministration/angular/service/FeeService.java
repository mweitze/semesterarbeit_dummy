package de.nordakademie.iaa.memberadministration.angular.service;

import de.nordakademie.iaa.memberadministration.angular.dao.EntityAlreadyPresentException;
import de.nordakademie.iaa.memberadministration.angular.dao.EntityNotFoundException;
import de.nordakademie.iaa.memberadministration.angular.dao.FeeDAO;
import de.nordakademie.iaa.memberadministration.angular.model.Fee;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class FeeService {

    private FeeDAO feeDAO;

    @Inject
    public void setFeeDAO(FeeDAO feeDAO) {
        this.feeDAO = feeDAO;
    }

    public List<Fee> listFees() {
        return feeDAO.listFees();
    }

    public void saveFee(Fee fee) {
        try {
            if (fee.getId() == null) {
                feeDAO.persistFee(fee);
            } else {
                feeDAO.mergeFee(fee);
            }
        } catch (EntityAlreadyPresentException e) {
            e.printStackTrace();
        }
    }

    public void deleteFee(Long id) {
        try {
            feeDAO.deleteFee(id);
        } catch (EntityNotFoundException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void updateFee(Long id, Date date, Integer feeAmount) {
        try {
            feeDAO.updateFee(id, date, feeAmount);
        } catch (EntityNotFoundException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Fee loadFee(Long id) {
        return feeDAO.loadFee(id);
    }}
