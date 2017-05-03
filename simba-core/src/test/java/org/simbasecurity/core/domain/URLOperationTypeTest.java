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

import static org.junit.Assert.*;

import org.junit.Test;

public class URLOperationTypeTest {

    @Test
    public void testResolve() {
        assertEquals(URLOperationType.GET, URLOperationType.resolve("get"));
        assertEquals(URLOperationType.GET, URLOperationType.resolve("GET"));
        assertEquals(URLOperationType.POST, URLOperationType.resolve("post"));
        assertEquals(URLOperationType.HEAD, URLOperationType.resolve("head"));
        assertEquals(URLOperationType.DELETE, URLOperationType.resolve("delete"));
        assertEquals(URLOperationType.OPTIONS, URLOperationType.resolve("options"));
        assertEquals(URLOperationType.PUT, URLOperationType.resolve("put"));
        assertEquals(URLOperationType.TRACE, URLOperationType.resolve("trace"));

        assertEquals(URLOperationType.UNKNOWN, URLOperationType.resolve("unknown"));
        assertEquals(URLOperationType.UNKNOWN, URLOperationType.resolve("illegal"));
        assertEquals(URLOperationType.UNKNOWN, URLOperationType.resolve(null));
    }

}
