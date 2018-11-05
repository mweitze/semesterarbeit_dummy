package de.nordakademie.iaa.memberadministration.angular.controller;

import de.nordakademie.iaa.memberadministration.angular.model.Member;
import de.nordakademie.iaa.memberadministration.angular.service.MemberService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
public class MemberController {

    @Inject
    private MemberService memberService;

    /**
     * List all existing members.
     *
     * @return the list of members.
     */
    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public List<Member> listMembers(@RequestParam(value = "searchString", required = false) String searchString) {
        if (searchString != null) {
            return memberService.searchMember(searchString);
        }

        return memberService.listMembers();
    }

    /**
     * Loads and returns a single member entity.
     *
     * @param id The member's identifier.
     * @return the member or {@code null} if no matching member was found.
     */
    @RequestMapping(value = "/members/{id}", method = RequestMethod.GET)
    public Member loadMember(@PathVariable Long id) {
        return memberService.loadMember(id);
    }

    /**
     * Saves the given member.
     *
     * @param member The member to be saved.
     * @return the updated member object.
     */
    @RequestMapping(value = "/members", method = RequestMethod.PUT)
    public Member saveMember(@RequestBody Member member) {
        memberService.saveMember(member);
        return member;
    }

    /**
     * Deletes the member with the given identifier.
     *
     * @param id The member's identifier.
     */
    @RequestMapping(value = "/members/{id}", method = RequestMethod.DELETE)
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}
