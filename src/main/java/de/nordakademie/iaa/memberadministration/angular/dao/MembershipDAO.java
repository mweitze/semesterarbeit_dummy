package de.nordakademie.iaa.memberadministration.angular.dao;

import de.nordakademie.iaa.memberadministration.angular.model.Membership;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class MembershipDAO {

    /**
     * The current entity manager.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Takes the membership and stores it in the database.
     *
     * @param membership The membership to be persisted.
     * @throws EntityAlreadyPresentException if a membership with the same number
     *                                     combination is already present in the database.
     */
    public void persistMembership(Membership membership) throws EntityAlreadyPresentException {
        try {
            entityManager.persist(membership);
        }
        catch (ConstraintViolationException ex) {
            throw new EntityAlreadyPresentException();
        }
    }

    /**
     * List all members currently stored in the database.
     *
     * @return a list of Member entities. If no member was found an empty list is
     * returned.
     */
    @SuppressWarnings("unchecked")
    public List<Membership> listMemberships() {
        return entityManager.createQuery("select m from Membership m").getResultList();
    }

    /**
     * Returns the member identified by the given id.
     *
     * @param id The identifier.
     * @return the found entity or {@code null} if no entity was found with given identifier.
     */
    public Membership loadMembership(Long id) {
        return entityManager.find(Membership.class, id);
    }

    /**
     * Updates a member entity and stores the changes into the database.
     *
     * @param id   The identifier.
     * @param name The name of the member.
     * @throws EntityNotFoundException if no member could be found for the given id.
     */
    public void updateMembership(Long id, String name) throws EntityNotFoundException {
        Membership membership = entityManager.find(Membership.class, id);
        if (membership == null) {
            throw new EntityNotFoundException();
        }
        membership.setName(name);
    }

    /**
     * Deletes the member with the given id.
     *
     * @param id The identifier.
     * @throws EntityNotFoundException if no member could be fount for the given id.
     */
    public void deleteMembership(Long id) throws EntityNotFoundException {
        Membership membership = entityManager.find(Membership.class, id);
        if (membership == null) {
            throw new EntityNotFoundException();
        }
        entityManager.remove(membership);
    }

    /**
     * Merge the member with the given id.
     *
     * @param membership The member that should be merged
     */
    public void mergeMembership(Membership membership) {
        entityManager.merge(membership);
    }

    /**
     * List all members with the given name
     * @param name Name to search
     * @return a list of Member entities. If no member was found an empty list is
     * returned.
     */
    @SuppressWarnings("unchecked")
    public List<Membership> searchMembershipByName(String name) {
        List<Membership> members = entityManager.createQuery(
                "select m from Membership m where m.name like :name")
                .setParameter("name", "%" + name + "%")
                .getResultList();
        return members;
    }
}
