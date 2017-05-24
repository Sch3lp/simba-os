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

angular.module('SimbaApp')
    .factory('$property', ['$http', '$q', function ($http, $q) {
        var simbaLocation = "";
        var managerLocation = "";

        function setManagerLocation() {
            var url = window.location.href;
            var arr = url.split("/");
            managerLocation = arr[0] + "//" + arr[2];
        }

        function isLocalhost() {
            return window.location.href.indexOf("localhost") != -1;
        }

        return {
            setSimbaLocation: function (successFunction) {
                setManagerLocation();
                if (!isLocalhost()) {
                    simbaLocation = managerLocation + "/simba-ventouris";
                    successFunction();
                } else {
                    $http({method: 'GET', url: managerLocation + "/simba-manager/simba-locator"}).success(function (data) {
                        simbaLocation = data;
                        successFunction();
                    })
                        .error(function () {
                            successFunction();
                        });
                }
            },
            getSimbaLocation: function () {
                return simbaLocation;
            }
        };

    }]);



