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
    .factory('$session', ['$rest', function($rest) {
        var currentUserName = "";
        return {
            getCurrentUser: function() {
                return $rest.newGet('session/getCurrentUser');
            },
            setCurrentUserName: function(userName) {
                currentUserName = userName;
            },
            getCurrentUserName: function() {
                return currentUserName;
            },
            findAllActive : function() {
                return $rest.newGet('session/findAllActive');
            },
            removeAllButMine : function() {
                return $rest.newGet('session/removeAllButMine');
            },
            remove : function(session) {
                return $rest.newPost('session/remove', session);
            }
        };
    }]);
