package de.nordakademie.iaa.memberadministration.angular.dao;

import de.nordakademie.iaa.memberadministration.angular.model.Fee;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

public class FeeDAO {

    /**
     * The current entity manager.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Takes the fee and stores it in the database.
     *
     * @param fee The fee to be persisted.
     * @throws EntityAlreadyPresentException if a fee with the same building/fee number
     *                                     combination is already present in the database.
     */
    public void persistFee(Fee fee) throws EntityAlreadyPresentException {
        try {
            entityManager.persist(fee);
        }
        catch (ConstraintViolationException ex) {
            throw new EntityAlreadyPresentException();
        }
    }

    /**
     * List all fees currently stored in the database.
     *
     * @return a list of Fee entities. If no fee was found an empty list is
     * returned.
     */
    @SuppressWarnings("unchecked")
    public List<Fee> listFees() {
        return entityManager.createQuery("from Fee").getResultList();
    }

    /**
     * Returns the fee identified by the given id.
     *
     * @param id The identifier.
     * @return the found entity or {@code null} if no entity was found with given identifier.
     */
    public Fee loadFee(Long id) {
        return entityManager.find(Fee.class, id);
    }

    /**
     * Updates a fee entity and stores the changes into the database.
     *
     * @param id   The identifier.
     * @param date The date of the fee.
     * @param feeAmount The amount of the fee
     * @throws EntityNotFoundException if no fee could be found for the given id.
     */
    public void updateFee(Long id, Date date, Integer feeAmount) throws EntityNotFoundException {
        Fee fee = entityManager.find(Fee.class, id);
        if (fee == null) {
            throw new EntityNotFoundException();
        }
        fee.setDate(date);
        fee.setFee(feeAmount);
    }

    /**
     * Deletes the fee with the given id.
     *
     * @param id The identifier.
     * @throws EntityNotFoundException if no fee could be fount for the given id.
     */
    public void deleteFee(Long id) throws EntityNotFoundException {
        Fee fee = entityManager.find(Fee.class, id);
        if (fee == null) {
            throw new EntityNotFoundException();
        }
        entityManager.remove(fee);
    }

    /**
     * Merge the fee with the given id.
     *
     * @param fee The fee that should be merged
     */
    public void mergeFee(Fee fee) {
        entityManager.merge(fee);
    }

    /**
     * List all fees with the given name
     * @param name Name to search
     * @return a list of Fee entities. If no fee was found an empty list is
     * returned.
     */
    @SuppressWarnings("unchecked")
    public List<Fee> searchFeeByName(String name) {
        List<Fee> fees = entityManager.createQuery(
                "select f from Fee f where f.name like :name")
                .setParameter("name", "%" + name + "%")
                .getResultList();
        return fees;
    }}
