package de.nordakademie.iaa.memberadministration.angular.service;

import de.nordakademie.iaa.memberadministration.angular.dao.EntityAlreadyPresentException;
import de.nordakademie.iaa.memberadministration.angular.dao.EntityNotFoundException;
import de.nordakademie.iaa.memberadministration.angular.dao.MembershipDAO;
import de.nordakademie.iaa.memberadministration.angular.model.Membership;

import javax.inject.Inject;
import java.util.List;

public class MembershipService {

    private MembershipDAO membershipDAO;

    @Inject
    public void setMembershipDAO(MembershipDAO membershipDAO) {
        this.membershipDAO = membershipDAO;
    }

    public List<Membership> listMemberships() {
        return membershipDAO.listMemberships();
    }

    public void saveMembership(Membership membership) {
        try {
            if (membership.getId() == null) {
                membershipDAO.persistMembership(membership);
            } else {
                membershipDAO.mergeMembership(membership);
            }
        } catch (EntityAlreadyPresentException e) {
            e.printStackTrace();
        }
    }

    public void deleteMembership(Long id) {
        try {
            membershipDAO.deleteMembership(id);
        } catch (EntityNotFoundException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Membership loadMembership(Long id) {
        return membershipDAO.loadMembership(id);
    }

    public List<Membership> searchMembershipByName(String name) {
        return membershipDAO.searchMembershipByName(name);
    }
}
