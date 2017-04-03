package org.simbasecurity.core.domain.generator;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.simbasecurity.core.config.ConfigurationService;
import org.simbasecurity.core.domain.validator.PasswordValidatorImpl;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.simbasecurity.core.config.SimbaConfigurationParameter.*;

@RunWith(Parameterized.class)
public class PasswordGeneratorImplTest {

    private static final int NUMBER_OF_TESTS = 1000;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private ConfigurationService configurationService;

    private PasswordGeneratorImpl generator;
    private PasswordValidatorImpl validator;

    @Before
    public void setup() {
        when(configurationService.getValue(PASSWORD_VALID_CHARACTERS)).thenReturn("[\\x21-\\x7E]*");
        when(configurationService.getValue(PASSWORD_MIN_LENGTH)).thenReturn(6);
        when(configurationService.getValue(PASSWORD_MAX_LENGTH)).thenReturn(50);
        when(configurationService.getValue(PASSWORD_MINIMUM_COMPLEXITY)).thenReturn(3);
        when(configurationService.getValue(PASSWORD_COMPLEXITY_RULE)).thenReturn(asList(".*[A-Z].*", ".*[a-z].*", ".*[0-9].*", ".*[\\W_].*"));

        validator = new PasswordValidatorImpl();
        validator.setConfigurationService(configurationService);

        generator = new PasswordGeneratorImpl();
        generator.setConfigurationService(configurationService);
        generator.setPasswordValidator(validator);

    }

    public PasswordGeneratorImplTest(int run) {}

    @Parameterized.Parameters
    public static Collection<Integer> generatePasswords() {
        return IntStream.rangeClosed(1, NUMBER_OF_TESTS).boxed().collect(Collectors.toList());
    }

    @Test
    public void generatePassword() {
        String password = generator.generatePassword();
        assertThat(password).hasSize(24).matches("[\\x21-\\x7E]*");
    }
}