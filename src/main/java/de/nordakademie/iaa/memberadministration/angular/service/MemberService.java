package de.nordakademie.iaa.memberadministration.angular.service;

import de.nordakademie.iaa.memberadministration.angular.dao.EntityAlreadyPresentException;
import de.nordakademie.iaa.memberadministration.angular.dao.EntityNotFoundException;
import de.nordakademie.iaa.memberadministration.angular.dao.MemberDAO;
import de.nordakademie.iaa.memberadministration.angular.model.Member;

import javax.inject.Inject;
import java.util.List;

public class MemberService {

    private MemberDAO memberDAO;

    @Inject
    public void setMemberDAO(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public List<Member> listMembers() {
        return memberDAO.listMembers();
    }

    public void saveMember(Member member) {
        try {
            if (member.getId() == null) {
                memberDAO.persistMember(member);
            } else {
                memberDAO.mergeMember(member);
            }
        } catch (EntityAlreadyPresentException e) {
            e.printStackTrace();
        }
    }

    public void deleteMember(Long id) {
        try {
            memberDAO.deleteMember(id);
        } catch (EntityNotFoundException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void updateMember(Long id, String name) {
        try {
            memberDAO.updateMember(id, name);
        } catch (EntityNotFoundException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Member loadMember(Long id) {
        return memberDAO.loadMember(id);
    }

    public List<Member> searchMember(String searchString) {
        return memberDAO.searchMember(searchString);
    }
}
