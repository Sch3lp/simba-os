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
package org.simbasecurity.core.domain;

import java.util.Set;

import org.simbasecurity.core.service.AuthorizationRequestContext;

/**
 * Represents a condition which should be respected by a policy in order to
 * apply. A policy applies if all of it's conditions applies. Implementations of
 * this interface usually check some conditions based on data inside context
 * object.
 *
 * @see org.simbasecurity.core.domain.Policy
 */
public interface Condition extends Versionable {

    /**
     * @return true if condition is respected, false otherwise.
     */
    boolean applies(AuthorizationRequestContext context);

    String getName();

    void setName(String name);

    /**
     * @return the collection of users for which the condition should not be checked.
     */
    Set<User> getExemptedUsers();

    void setExemptedUsers(Set<User> exemptedUsers);

    /**
     * @return the expiration timestamp
     * @see System#currentTimeMillis()
     */
    long getExpirationTimestamp(AuthorizationRequestContext context);
}
