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
package org.simbasecurity.core.service;

import org.simbasecurity.api.service.thrift.*;
import org.simbasecurity.core.domain.Condition;
import org.simbasecurity.core.domain.Policy;
import org.simbasecurity.core.domain.User;
import org.simbasecurity.core.domain.repository.ConditionRepository;
import org.simbasecurity.core.domain.repository.PolicyRepository;
import org.simbasecurity.core.domain.repository.UserRepository;
import org.simbasecurity.core.exception.SimbaException;
import org.simbasecurity.core.exception.SimbaMessageKey;
import org.simbasecurity.core.service.thrift.ThriftAssembler;
import org.simbasecurity.core.spring.quartz.ExtendedCronExpression;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service("conditionService")
public class ConditionServiceImpl implements ConditionService.Iface {

    private final ConditionRepository conditionRepository;
    private final PolicyRepository policyRepository;
    private final UserRepository userRepository;
    private final ThriftAssembler assembler;

    @Autowired
    public ConditionServiceImpl(ConditionRepository conditionRepository, PolicyRepository policyRepository,
                                UserRepository userRepository, ThriftAssembler assembler) {
        this.conditionRepository = conditionRepository;
        this.policyRepository = policyRepository;
        this.userRepository = userRepository;
        this.assembler = assembler;
    }

    public List<TCondition> findAll() {
        return assembler.list(conditionRepository.findAll());
    }

    public List<TPolicy> findPolicies(TCondition condition) {
        final Condition attachedCondition = conditionRepository.refreshWithOptimisticLocking(condition.getId(),
                                                                                             condition.getVersion());
        return assembler.list(conditionRepository.findPolicies(attachedCondition));
    }

    public List<TUser> findExemptedUsers(TCondition condition) {
        final Condition attachedCondition = conditionRepository.refreshWithOptimisticLocking(condition.getId(),
                                                                                             condition.getVersion());
        return assembler.list(attachedCondition.getExemptedUsers());
    }

    public TCondition refresh(TCondition condition) {
        return assembler.assemble(conditionRepository.lookUp(condition.getId()));
    }

    public TCondition addOrUpdate(TCondition condition, List<TPolicy> policies, List<TUser> exemptedUsers) {
        Condition attachedCondition = findOrCreate(condition);

        Set<User> attachedUsers = exemptedUsers.stream()
                                               .map(u -> userRepository.refreshWithOptimisticLocking(u.getId(),
                                                                                                     u.getVersion()))
                                               .collect(Collectors.toSet());
        attachedCondition.setExemptedUsers(attachedUsers);

        List<Policy> attachedPolicies = policies.stream()
                                                .map(p -> policyRepository.refreshWithOptimisticLocking(p.getId(),
                                                                                                        p.getVersion()))
                                                .collect(Collectors.toList());

        conditionRepository.updatePolicies(attachedCondition, attachedPolicies);
        conditionRepository.flush();
        return assembler.assemble(attachedCondition);
    }

    private Condition findOrCreate(TCondition condition) {
        final Condition attachedCondition;

        if (condition.getId() == 0L) {
            attachedCondition = conditionRepository.persist(assembler.assemble(condition));
        } else {
            attachedCondition = conditionRepository.refreshWithOptimisticLocking(condition.getId(),
                                                                                 condition.getVersion());
            BeanUtils.copyProperties(condition, attachedCondition);
        }

        return attachedCondition;
    }

    public void remove(TCondition condition) {
        Condition attachedCondition = conditionRepository.refreshWithOptimisticLocking(condition.getId(), condition.getVersion());

        conditionRepository.updatePolicies(attachedCondition, Collections.emptySet());
        conditionRepository.remove(attachedCondition);
    }

    public boolean validateTimeCondition(TCondition condition) {
        if (condition.getType() != TConditionType.TIME) {
            throw new IllegalArgumentException("Can only validate TIME conditions");
        }
        if (!isExpressionValid(condition.getStartExpression())) {
            throw new SimbaException(SimbaMessageKey.INVALID_START_CONDITION);
        }

        if (!isExpressionValid(condition.getEndExpression())) {
            throw new SimbaException(SimbaMessageKey.INVALID_END_CONDITION);
        }

        return true;
    }

    private static boolean isExpressionValid(final String expression) {
        try {
            new ExtendedCronExpression(expression);
            return true;
        } catch (ParseException ignored) {
            return false;
        }
    }
}
