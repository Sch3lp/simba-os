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

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.simbasecurity.core.domain.*;
import org.simbasecurity.core.domain.user.EmailAddress;
import org.simbasecurity.test.PersistenceTestCase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.simbasecurity.core.domain.UserTestBuilder.aDefaultUser;
import static org.simbasecurity.core.domain.user.EmailAddress.email;

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

    @Test
    public void findAllOrderedByName() throws Exception {
        User userB = new UserEntity("b");
        User userC = new UserEntity("c");
        User userA = new UserEntity("a");
        persistAndRefresh(userB, userC, userA);

        Collection<User> result = userDatabaseRepository.findAllOrderedByName();

        assertThat(result).containsExactly(userA, userB, userC, user);
    }

    @Test
    public void searchUsersOrderedByName() throws Exception {
        User userB = new UserEntity("banaan", "Jan", "Klaas", null, Language.en_US, Status.ACTIVE, false, false);
        User userC = new UserEntity("citroen", "Piet", "Hein", null, Language.en_US, Status.ACTIVE, false, false);
        User userA = new UserEntity("appel", "Kevin", "Nobin", null, Language.en_US, Status.ACTIVE, false, false);
        persistAndRefresh(userB, userC, userA);

        assertThat(userDatabaseRepository.searchUsersOrderedByName("a")).containsExactly(userA, userB);
        assertThat(userDatabaseRepository.searchUsersOrderedByName("z")).isEmpty();
        assertThat(userDatabaseRepository.searchUsersOrderedByName("")).containsExactly(userA, userB, userC, user);
        assertThat(userDatabaseRepository.searchUsersOrderedByName("aa")).containsExactly(userB);
        assertThat(userDatabaseRepository.searchUsersOrderedByName("v")).containsExactly(userA);
        assertThat(userDatabaseRepository.searchUsersOrderedByName("s")).containsExactly(userB);
    }

    @Test
    public void searchUsersOrderedByName_NotCaseSensitive() throws Exception {
        User user = new UserEntity("banaan", "Jan", "Klaas", null, Language.en_US, Status.ACTIVE, false, false);
        persistAndRefresh(user);

        assertThat(userDatabaseRepository.searchUsersOrderedByName("j")).containsExactly(user);
        assertThat(userDatabaseRepository.searchUsersOrderedByName("S")).containsExactly(user);
        assertThat(userDatabaseRepository.searchUsersOrderedByName("B")).containsExactly(user);
    }

    @Test
    public void findUserByMail_WillReturnUser_IfPresentInDatabase() throws Exception {
        EmailAddress email = email("bruce@wayneindustries.com");

        User expectedUser = aDefaultUser().withEmail(email).build();
        persistAndRefresh(expectedUser);

        User user = userDatabaseRepository.findByEmail(email);

        Assertions.assertThat(user).isEqualTo(expectedUser);
    }
}
