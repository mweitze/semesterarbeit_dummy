package de.nordakademie.iaa.memberadministration.angular.dao;

import de.nordakademie.iaa.memberadministration.angular.model.Member;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class MemberDAO {

    /**
     * The current entity manager.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Takes the member and stores it in the database.
     *
     * @param member The member to be persisted.
     * @throws EntityAlreadyPresentException if a member with the same building/member number
     *                                     combination is already present in the database.
     */
    public void persistMember(Member member) throws EntityAlreadyPresentException {
        try {
            entityManager.persist(member);
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
    public List<Member> listMembers() {
        return entityManager.createQuery("from Member").getResultList();
    }

    /**
     * Returns the member identified by the given id.
     *
     * @param id The identifier.
     * @return the found entity or {@code null} if no entity was found with given identifier.
     */
    public Member loadMember(Long id) {
        return entityManager.find(Member.class, id);
    }

    /**
     * Updates a member entity and stores the changes into the database.
     *
     * @param id   The identifier.
     * @param name The name of the member.
     * @throws EntityNotFoundException if no member could be found for the given id.
     */
    public void updateMember(Long id, String name) throws EntityNotFoundException {
        Member member = entityManager.find(Member.class, id);
        if (member == null) {
            throw new EntityNotFoundException();
        }
        member.setName(name);
    }

    /**
     * Deletes the member with the given id.
     *
     * @param id The identifier.
     * @throws EntityNotFoundException if no member could be fount for the given id.
     */
    public void deleteMember(Long id) throws EntityNotFoundException {
        Member member = entityManager.find(Member.class, id);
        if (member == null) {
            throw new EntityNotFoundException();
        }
        entityManager.remove(member);
    }

    /**
     * Merge the member with the given id.
     *
     * @param member The member that should be merged
     */
    public void mergeMember(Member member) {
        entityManager.merge(member);
    }

    /**
     * List all members with the given name
     * @param name Name to search
     * @return a list of Member entities. If no member was found an empty list is
     * returned.
     */
    @SuppressWarnings("unchecked")
    public List<Member> searchMember(String searchString) {
        List<Member> members = entityManager.createQuery(
                "select m from Member m where (lower(m.name) like :name) or (lower(m.address) like :name) or (lower(m.bankDetails) like :name)")
                .setParameter("name", "%" + searchString.toLowerCase() + "%")
                .getResultList();
        return members;
    }
}
