'use strict';

var resources = angular.module('resources', []);

resources.factory('Member', function () {
    return function (id, name, address, birthday, joinDate, cancelDate, leaveDate, paidFees, bankDetails, membership, familyMember) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.joinDate = joinDate;
        this.cancelDate = cancelDate;
        this.leaveDate = leaveDate;
        this.membership = membership;
        this.paidFees = paidFees;
        this.bankDetails = bankDetails;
        this.familyMember = familyMember;
    };
});

resources.factory('Membership', function () {
    return function (id, name, fee) {
        this.id = id;
        this.name = name;
        this.fee = fee;
    }
});

resources.factory('Fee', function () {
    return function(id, date, fee) {
        this.id = id;
        this.date = date;
        this.fee = fee;
    }
});