'use strict';

var services = angular.module('services', ['resources']);

services.service('memberService', ['$http', function ($http) {
    this.listMembersWithPromise = function () {
        return $http.get('rest/members');
    };

    this.searchMembersWithPromise = function (searchString) {
        return $http({
            url: 'rest/members',
            method: "GET",
            params: {searchString: searchString}
        });
    };

    this.saveMemberWithPromise = function (member) {
        return $http.put('rest/members', member);
    };
}]);

services.service('membershipService', ['$http', function ($http) {
    this.listMembershipsWithPromise = function () {
        return $http.get('rest/memberships');
    };

    this.saveMembershipWithPromise = function (membership) {
        return $http.put('rest/memberships', membership);
    };
}]);

services.service('feeService', ['$http', function ($http) {
    this.listFeesWithPromise = function () {
        return $http.get('rest/fees');
    };

    this.saveFeeWithPromise = function (fee) {
        return $http.put('rest/fees', fee);
    };
}]);