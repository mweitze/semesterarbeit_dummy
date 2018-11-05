'use strict';

var controllers = angular.module('controllers', ['resources', 'services']);

controllers.controller('mainController', ['$scope', function ($scope) {

    $scope.screens = {
        mainScreen: ['mainScreen', 'mainScreen.html'],
        membershipsScreen: ['membershipsScreen', 'membershipScreen.html'],
        memberDetailScreen: ['memberDetailScreen', 'memberDetailScreen.html'],
        membershipDetailScreen: ['membershipDetailScreen', 'membershipDetailScreen.html']
    };

    $scope.model = {
        members: [],
        selectedMember: null,
        memberships: [],
        selectedMembership: null,
        screen: $scope.screens.mainScreen
    };

    $scope.switchToScreen = function (newScreen) {
        if (angular.isArray(newScreen) && newScreen.length === 2) {
            $scope.model.screen = newScreen;
            if (!$scope.$$phase) {
                $scope.$apply('model.screen');
            }
        }
    };

    $scope.getCurrentScreen = function () {
        return $scope.model.screen;
    };

    this.getCurrentScreenSource = function () {
        return $scope.model.screen[1];
    };

}]);

controllers.controller('memberListController', ['$scope', 'Member', 'memberService', function ($scope, Member, memberService) {

    this.formModel = {
        searchString: ''
    };

    this.getFeesTotal = function() {
        var total = 0;
        for(var i = 0; i < $scope.model.members.length; i++) {
            total += $scope.model.members[i].membership.fee;
        }
        return total;
    };

    this.searchMember = function (searchString) {
        memberService.searchMembersWithPromise(searchString)
            .then(function (response) {
                $scope.model.members = response.data;
            })
            .catch(function (response) {
                alert("an error occured while loading");
            });
    };

    this.selectMember = function (selected) {
        $scope.model.selectedMember = selected;
    };

    this.newMember = function () {
        $scope.model.selectedMember = new Member(0, '', '');
        $scope.switchToScreen($scope.screens.memberDetailScreen);
    };

    this.editMember = function (selected) {
        this.selectMember(selected);
        $scope.switchToScreen($scope.screens.memberDetailScreen);
    };

    memberService.listMembersWithPromise()
        .then(function (response) {
            $scope.model.members = response.data;
        })
        .catch(function (response) {
            alert("an error occured while loading");
        });
}]);

controllers.controller('membershipListController', ['$scope', 'Membership', 'membershipService', function ($scope, Membership, membershipService) {

    this.selectMembership = function (selected) {
        $scope.model.selectedMembership = selected;
    };

    this.newMembership = function () {
        $scope.model.selectedMembership = new Membership(0, '', '');
        $scope.switchToScreen($scope.screens.membershipDetailScreen);
    };

    this.editMembership = function (selected) {
        this.selectMembership(selected);
        $scope.switchToScreen($scope.screens.membershipDetailScreen);
    };

    membershipService.listMembershipsWithPromise()
        .then(function (response) {
            $scope.model.memberships = response.data;
        })
        .catch(function (response) {
            alert("an error occured while loading");
        });
}]);

controllers.controller('memberDetailController', ['$scope', 'Member', 'memberService', function ($scope, Member, memberService) {

    this.formModel = {
        formMember: angular.copy($scope.model.selectedMember)
    };

    this.cancel = function () {
        $scope.switchToScreen($scope.screens.mainScreen);
    };

    this.saveMember = function (memberForm) {
        var selected = $scope.model.selectedMember;
        var edited = this.formModel.formMember;
        if (memberForm.$valid && selected && edited) {
            selected = angular.copy(edited);
            // do save data
            memberService.saveMemberWithPromise(selected)
                .then(function (response) {
                    if ($scope.model.members.indexOf(selected) === -1) {
                        $scope.model.members.push(response.data);
                    }
                    $scope.switchToScreen($scope.screens.mainScreen);
                })
                .catch(function (response) {
                    alert("an error occured while saving");
                });
        }
    };
}]);

controllers.controller('membershipDetailController', ['$scope', 'Membership', 'membershipService', function ($scope, Membership, membershipService) {

    this.formModel = {
        formMembership: new Membership($scope.model.selectedMembership.id, $scope.model.selectedMembership.name, $scope.model.selectedMembership.fee)
    };

    this.cancel = function () {
        $scope.switchToScreen($scope.screens.membershipsScreen);
    };

    this.saveMembership = function (membershipForm) {
        var selected = $scope.model.selectedMembership;
        var edited = this.formModel.formMembership;
        if (membershipForm.$valid && selected && edited) {
            selected.id = edited.id;
            selected.name = edited.name;
            selected.fee = edited.fee;
            // do save data
            membershipService.saveMembershipWithPromise(selected)
                .then(function (response) {
                    if ($scope.model.memberships.indexOf(selected) === -1) {
                        $scope.model.memberships.push(response.data);
                    }
                    $scope.switchToScreen($scope.screens.membershipsScreen);
                })
                .catch(function (response) {
                    alert("an error occured while saving");
                });
        }
    };
}]);
