/*
 * Copyright 2013-2017 Simba Open Source
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

'use strict';

const VIEW_USER_SIZE = 100;

angular.module('SimbaApp')
    .controller('UserCtrl', ['$scope', '$modal', '$log', '$user', '$error', '$translate', '$timeout', '$rootScope', '$rule', function ($scope, $modal, $log, $user, $error, $translate, $timeout, $rootScope, $rule) {
        $scope.tabs;
        $scope.searchText = "";
        $scope.searchBoxPlaceholderText = "users.filter";

        $scope.headers = [
            'useroverview.username',
            'useroverview.active',
            'useroverview.blocked',
            'useroverview.name',
            'useroverview.firstname'
        ];

        $scope.users = [];
        $scope.viewUsers = [];

        $scope.init = function () {
        };

        $scope.showMoreToggle = false;

        $scope.showMoreUsers = function () {
            $scope.showMoreToggle = false;
            $scope.viewUsers = $scope.users;
        };

        $scope.assignQueriedUsers = function (data) {
            $scope.users = data;
            $scope.viewUsers = $scope.users.slice(0, VIEW_USER_SIZE);
            $scope.showMoreToggle = $scope.users.length > VIEW_USER_SIZE;
        };

        $scope.findUsers = function () {
            $rootScope.loading++;
            if ($scope.searchText.length === 0) {
                $user.getAll()
                    .then($scope.assignQueriedUsers)
                    .finally(function () {
                            $rootScope.loading--;
                        }
                    );
            } else {
                $user.searchUsers($scope.searchText)
                    .success($scope.assignQueriedUsers)
                    .finally(function () {
                            $rootScope.loading--;
                        }
                    );
            }
        };

        $scope.openUserDetails = function (selectedUser) {
            var modalInstance = $modal.open({
                templateUrl: 'views/modals/user/userDetailTemplate.html',
                controller: 'UserDetailsCtrl',
                resolve: {
                    selectedUser: function () {
                        return jQuery.extend(true, {}, selectedUser);
                    }
                }
            });

            modalInstance.result.then(function (user) {
                $user.update(user)
                    .success(function (data) {
                        if (user.resetPassword) {
                            $user.resetPassword(data);
                        }
                        var i = $scope.viewUsers.indexOf(selectedUser);
                        $scope.users[i] = data;
                        $scope.viewUsers[i] = data;
                    })
                    .error(function () {
                        $error.showError('error.update.failed');
                    });
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
                $user.refresh(selectedUser)
                    .success(function (data) {
                        var i = $scope.viewUsers.indexOf(selectedUser);
                        $scope.users[i] = data;
                        $scope.viewUsers[i] = data;
                    })
                    .error(function () {
                        $error.showError('error.refresh.failed');
                    });
            });
        };


        $scope.copyUser = function (user) {
            $user.findRoles(user).then(function (roles) {
                var modalInstance = $modal.open({
                    templateUrl: 'views/modals/user/addUserTemplate.html',
                    controller: 'UserCreationCtrl',
                    resolve: {
                        selectedUser: function () {
                            return {
                                language: user.language,
                                status: user.status,
                                successURL: user.successURL,
                                changePassword: true
                            };
                        },
                        roles: function () {
                            return roles.data;
                        }
                    }
                });

                modalInstance.result.then(function (addUserResult) {
                    console.log(addUserResult);
                    $user.add(addUserResult.user)
                        .success(function (data) {
                            $scope.users.push(data);
                            $scope.viewUsers.push(data);
                            $user.addRoles(data, addUserResult.roles)
                                .catch(function () {
                                    $error.showError('error.update.failed');
                                });
                        })
                        .error(function () {
                            $error.showError('error.create.failed');
                        });
                });
            });
        };
        $scope.openAddUser = function () {
            var modalInstance = $modal.open({
                templateUrl: 'views/modals/user/addUserTemplate.html',
                controller: 'UserCreationCtrl',
                resolve: {
                    selectedUser: function () {
                        return {language: "en_US", status: "ACTIVE"};
                    },
                    roles: function () {
                        return [];
                    }
                }
            });

            modalInstance.result.then(function (addUserResult) {
                $user.add(addUserResult.user)
                    .success(function (data) {
                        $scope.users.push(data);
                        $scope.viewUsers.push(data);
                        $user.addRoles(data, addUserResult.roles)
                            .catch(function () {
                                $error.showError('error.update.failed');
                            });
                    })
                    .error(function () {
                        $error.showError('error.create.failed');
                    });
            });
        };

        $scope.openAddUserRest = function () {
            var modalInstance = $modal.open({
                templateUrl: 'views/modals/user/addUserRest.html',
                controller: 'UserCreationCtrl',
                resolve: {
                    selectedUser: function () {
                        return {};
                    },
                    roles: function () {
                        return [];
                    }
                }
            });

            modalInstance.result.then(function (addUserResult) {
                addRestUser(addUserResult.user);
            });
        };

        $scope.isUserInactive = function (user) {
            return user.status === 'INACTIVE';
        };

        $scope.isUserBlocked = function (user) {
            return user.status === 'BLOCKED';
        };

        var addRestUser = function (creationData) {
            $user.addRest(creationData)
                .success(function (data) {
                    $scope.users.push(creationData);
                    $modal.open({
                        templateUrl: 'views/modals/user/generatedPasswordTemplate.html',
                        controller: 'GeneratedPasswordCtrl',
                        resolve: {
                            password: function () {
                                return data;
                            }
                        }
                    });
                })
                .error(function () {
                    $error.showError('error.create.failed');
                });
        };

        $scope.isConfigurationAdmin = function () {
            return $rule.evaluateRule('manage-configuration', 'WRITE');
        };

    }]);
