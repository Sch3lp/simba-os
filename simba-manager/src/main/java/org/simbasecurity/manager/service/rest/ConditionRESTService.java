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
package org.simbasecurity.manager.service.rest;

import org.simbasecurity.api.service.thrift.ConditionService;
import org.simbasecurity.client.configuration.SimbaConfiguration;
import org.simbasecurity.manager.service.rest.dto.ConditionWithPoliciesAndExcludedUsersDTO;
import org.simbasecurity.manager.service.rest.dto.PolicyDTO;
import org.simbasecurity.manager.service.rest.dto.TimeConditionDTO;
import org.simbasecurity.manager.service.rest.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

import static org.simbasecurity.common.request.RequestConstants.SIMBA_SSO_TOKEN;
import static org.simbasecurity.manager.service.rest.assembler.DTOAssembler.assemble;
import static org.simbasecurity.manager.service.rest.assembler.DTOAssembler.list;

@Controller
@RequestMapping("condition")
public class ConditionRESTService extends BaseRESTService<ConditionService.Client> {

    public ConditionRESTService() {
        super(new ConditionService.Client.Factory(), SimbaConfiguration.getConditionServiceURL());
    }

    @RequestMapping("findAll")
    @ResponseBody
    public Collection<TimeConditionDTO> findAll(@CookieValue(value = SIMBA_SSO_TOKEN, required = false) String ssoToken) {
        return list($(() -> cl(ssoToken).findAll()));
    }

    @RequestMapping("findPolicies")
    @ResponseBody
    public Collection<PolicyDTO> findPolicies(@RequestBody TimeConditionDTO condition,
                                              @CookieValue(value = SIMBA_SSO_TOKEN, required = false) String ssoToken) {
        return list($(() -> cl(ssoToken).findPolicies(assemble(condition))));
    }

    @RequestMapping("findExemptedUsers")
    @ResponseBody
    public Collection<UserDTO> findExemptedUsers(@RequestBody TimeConditionDTO condition,
                                                 @CookieValue(value = SIMBA_SSO_TOKEN, required = false) String ssoToken) {
        return list($(() -> cl(ssoToken).findExemptedUsers(assemble(condition))));
    }

    @RequestMapping("refresh")
    @ResponseBody
    public TimeConditionDTO refresh(@RequestBody TimeConditionDTO condition,
                                    @CookieValue(value = SIMBA_SSO_TOKEN, required = false) String ssoToken) {
        return assemble($(() -> cl(ssoToken).refresh(assemble(condition))));
    }

    @RequestMapping("addOrUpdate")
    @ResponseBody
    public TimeConditionDTO addOrUpdate(@RequestBody ConditionWithPoliciesAndExcludedUsersDTO condition,
                                        @CookieValue(value = SIMBA_SSO_TOKEN, required = false) String ssoToken) {
        return assemble($(() -> cl(ssoToken).addOrUpdate(assemble(condition.getCondition()), list(condition.getPolicies()), list(condition.getExcludedUsers()))));
    }

    @RequestMapping("remove")
    @ResponseBody
    public void remove(@RequestBody TimeConditionDTO condition,
                       @CookieValue(value = SIMBA_SSO_TOKEN, required = false) String ssoToken) {
        $(() -> cl(ssoToken).remove(assemble(condition)));
    }

    @RequestMapping("validateTimeCondition")
    @ResponseBody
    public boolean validateTimeCondition(@RequestBody TimeConditionDTO timeCondition,
                                         @CookieValue(value = SIMBA_SSO_TOKEN, required = false) String ssoToken) {
        return $(() -> cl(ssoToken).validateTimeCondition(assemble(timeCondition)));
    }
}
