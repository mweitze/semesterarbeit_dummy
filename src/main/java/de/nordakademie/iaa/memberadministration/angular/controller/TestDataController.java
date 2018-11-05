package de.nordakademie.iaa.memberadministration.angular.controller;

import de.nordakademie.iaa.memberadministration.angular.model.Fee;
import de.nordakademie.iaa.memberadministration.angular.model.Member;
import de.nordakademie.iaa.memberadministration.angular.model.Membership;
import de.nordakademie.iaa.memberadministration.angular.service.FeeService;
import de.nordakademie.iaa.memberadministration.angular.service.MemberService;
import de.nordakademie.iaa.memberadministration.angular.service.MembershipService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class TestDataController {

    @Inject
    private MemberService memberService;

    @Inject
    private MembershipService membershipService;

    @Inject
    private FeeService feeService;

    @RequestMapping(value = "/testMemberMembershipFee", method = RequestMethod.GET)
    public void testMemberMembershipFee() {

        String[] names = {"James Hetfield", "John Lennon", "Freddie Mercury", "Angus Young", "Ozzy Osbourne"};

        List<Membership> memberships = new LinkedList<>();

        memberships.add(new Membership(
                "Vollmitglied",
                25
        ));
        memberships.add(new Membership(
                "Ermäßigt",
                23
        ));
        memberships.add(new Membership(
                "Jugendlich",
                15
        ));
        memberships.add(new Membership(
                "Fördermitglied",
                10
        ));

        for (Membership membership : memberships) {
            membershipService.saveMembership(membership);
        }

        Random random = new Random();
        for (String name : names) {
            Member member = new Member(
                    name,
                    "Teststraße 12a",
                    getRandomDate(),
                    getRandomDate(),
                    getRandomDate(),
                    getRandomDate(),
                    memberships.get(Math.abs(random.nextInt() % 4)),
                    null,
                    "DKB",
                    null
            );
            memberService.saveMember(member);
        }
    }

    public Date getRandomDate() {
        Random random = new Random();

        // Get an Epoch value roughly between 1940 and 2010
        // -946771200000L = January 1, 1940
        // Add up to 70 years to it (using modulus on the next long)
        // Source: https://stackoverflow.com/questions/3985392/generate-random-date-of-birth
        long ms = -946771200000L + (Math.abs(random.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
        return new Date(ms);
    }
}