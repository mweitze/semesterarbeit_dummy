package de.nordakademie.iaa.memberadministration.angular.controller;

import de.nordakademie.iaa.memberadministration.angular.model.Membership;
import de.nordakademie.iaa.memberadministration.angular.service.MembershipService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
public class MembershipController {

    @Inject
    private MembershipService membershipService;

    /**
     * List all existing memberships.
     *
     * @return the list of memberships.
     */
    @RequestMapping(value = "/memberships", method = RequestMethod.GET)
    public List<Membership> listMemberships(@RequestParam(value = "name", required = false) String name) {
        if (name != null) {
            return membershipService.searchMembershipByName(name);
        }

        return membershipService.listMemberships();
    }

    /**
     * Loads and returns a single membership entity.
     *
     * @param id The membership's identifier.
     * @return the membership or {@code null} if no matching membership was found.
     */
    @RequestMapping(value = "/memberships/{id}", method = RequestMethod.GET)
    public Membership loadMembership(@PathVariable Long id) {
        return membershipService.loadMembership(id);
    }

    /**
     * Saves the given membership.
     *
     * @param membership The membership to be saved.
     * @return the updated membership object.
     */
    @RequestMapping(value = "/memberships", method = RequestMethod.PUT)
    public Membership saveMembership(@RequestBody Membership membership) {
        membershipService.saveMembership(membership);
        return membership;
    }

    /**
     * Deletes the membership with the given identifier.
     *
     * @param id The membership's identifier.
     */
    @RequestMapping(value = "/memberships/{id}", method = RequestMethod.DELETE)
    public void deleteMembership(@PathVariable Long id) {
        membershipService.deleteMembership(id);
    }

}
