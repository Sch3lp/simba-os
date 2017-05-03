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
package org.simbasecurity.core.domain.repository;

import org.junit.Before;
import org.junit.Test;
import org.simbasecurity.core.domain.Role;
import org.simbasecurity.core.domain.RoleEntity;
import org.simbasecurity.core.domain.User;
import org.simbasecurity.core.domain.UserEntity;
import org.simbasecurity.test.PersistenceTestCase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserDatabaseRepositoryTest extends PersistenceTestCase {

    private static final String DUMMY_USER_NAME = "dummy";
    private UserEntity user;

    @Autowired
    private UserDatabaseRepository userDatabaseRepository;

    @Before
    public void setUp() {
        user = new UserEntity(DUMMY_USER_NAME);
        persistAndRefresh(user);
    }

    @Test
    public void test_findByName_success() {
        User result = userDatabaseRepository.findByName(DUMMY_USER_NAME);
        assertNotNull(result);
        assertEquals(DUMMY_USER_NAME, result.getUserName());
    }

    @Test
    public void findForRole() {
        Role role = new RoleEntity("aRole");
        User otherUser = new UserEntity("otherUser");
        persistAndRefresh(role, otherUser);
        role.addUser(this.user);

        Collection<User> result = userDatabaseRepository.findForRole(role);
        assertThat(result).containsOnly(user);
    }

}
