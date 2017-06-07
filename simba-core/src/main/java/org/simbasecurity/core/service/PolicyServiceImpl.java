package org.simbasecurity.core.service;

import org.apache.thrift.TException;
import org.owasp.esapi.errors.ValidationException;
import org.simbasecurity.api.service.thrift.PolicyService;
import org.simbasecurity.api.service.thrift.TPolicy;
import org.simbasecurity.api.service.thrift.TRole;
import org.simbasecurity.api.service.thrift.TRule;
import org.simbasecurity.core.domain.Policy;
import org.simbasecurity.core.domain.PolicyEntity;
import org.simbasecurity.core.domain.Role;
import org.simbasecurity.core.domain.Rule;
import org.simbasecurity.core.domain.repository.PolicyRepository;
import org.simbasecurity.core.domain.repository.RoleRepository;
import org.simbasecurity.core.domain.repository.RuleRepository;
import org.simbasecurity.core.service.filter.EntityFilterService;
import org.simbasecurity.core.service.thrift.ThriftAssembler;
import org.simbasecurity.core.service.validation.DTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service("policyService")
public class PolicyServiceImpl implements PolicyService.Iface {

    private final PolicyRepository policyRepository;
    private final RoleRepository roleRepository;
    private final RuleRepository ruleRepository;
    private final EntityFilterService filterService;
    private final ThriftAssembler assembler;

    @Autowired
    public PolicyServiceImpl(PolicyRepository policyRepository, RoleRepository roleRepository,
                             RuleRepository ruleRepository, EntityFilterService filterService,
                             ThriftAssembler assembler) {
        this.policyRepository = policyRepository;
        this.roleRepository = roleRepository;
        this.ruleRepository = ruleRepository;
        this.filterService = filterService;
        this.assembler = assembler;
    }

    @Override
    public List<TPolicy> findAll() throws TException {
        return assembler.list(filterService.filterPolicies(policyRepository.findAllOrderedByName()));

    }

    @Override
    public List<TRole> findRoles(TPolicy policy) throws TException {
        return assembler.list(
                filterService.filterRoles(roleRepository.findForPolicy(policyRepository.lookUp(policy.getId()))));
    }

    @Override
    public List<TRole> findRolesNotLinked(TPolicy policy) throws TException {
        return assembler.list(
                filterService.filterRoles(roleRepository.findNotLinked(policyRepository.lookUp(policy.getId()))));
    }

    @Override
    public void addRoles(TPolicy policy, Set<TRole> roles) throws TException {
        Policy attachedPolicy = policyRepository.refreshWithOptimisticLocking(policy.getId(), policy.getVersion());
        Set<Role> attachedRoles = roles.stream()
                                       .map(r -> roleRepository.refreshWithOptimisticLocking(r.getId(), r.getVersion()))
                                       .collect(Collectors.toSet());

        attachedPolicy.addRoles(attachedRoles);
    }

    @Override
    public void removeRole(TPolicy policy, TRole role) throws TException {
        Policy attachedPolicy = policyRepository.refreshWithOptimisticLocking(policy.getId(), policy.getVersion());
        Role attachedRole = roleRepository.refreshWithOptimisticLocking(role.getId(), role.getVersion());

        attachedPolicy.removeRole(attachedRole);
    }

    @Override
    public void addRules(TPolicy policy, Set<TRule> rules) throws TException {
        Policy attachedPolicy = policyRepository.refreshWithOptimisticLocking(policy.getId(), policy.getVersion());
        Set<Rule> attachedRules = rules.stream()
                                       .map(r -> ruleRepository.refreshWithOptimisticLocking(r.getId(), r.getVersion()))
                                       .collect(Collectors.toSet());

        attachedPolicy.addRules(attachedRules);
    }

    @Override
    public List<TRule> findRules(TPolicy policy) throws TException {
        return assembler.list(policyRepository.lookUp(policy.getId()).getRules());
    }

    @Override
    public List<TRule> findRulesNotLinked(TPolicy policy) throws TException {
        return assembler.list(ruleRepository.findNotLinked(policyRepository.lookUp(policy.getId())));
    }

    @Override
    public void removeRule(TPolicy policy, TRule rule) throws TException {
        Policy attachedPolicy = policyRepository.refreshWithOptimisticLocking(policy.getId(), policy.getVersion());
        Rule attachedRule = ruleRepository.refreshWithOptimisticLocking(rule.getId(), rule.getVersion());

        attachedPolicy.removeRule(attachedRule);
    }

    @Override
    public TPolicy refresh(TPolicy policy) throws TException {
        return assembler.assemble(policyRepository.lookUp(policy.getId()));
    }

    @Override
    public TPolicy createPolicy(String policyName) throws TException {
        try {
            DTOValidator.assertValidString("createRole", policyName);
            if (roleRepository.findByName(policyName) != null) {
                throw new IllegalArgumentException("Policy with name " + policyName + " already exists");
            }
            Policy newPolicy = new PolicyEntity(policyName);
            policyRepository.persist(newPolicy);
            return assembler.assemble(newPolicy);
        } catch (ValidationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void deletePolicy(TPolicy policy) throws TException {
        Policy policyToRemove = policyRepository.lookUp(policy.getId());
        policyRepository.remove(policyToRemove);
    }
}
