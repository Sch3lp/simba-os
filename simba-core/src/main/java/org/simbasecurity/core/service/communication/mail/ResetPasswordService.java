package org.simbasecurity.core.service.communication.mail;

import org.simbasecurity.core.domain.User;
import org.simbasecurity.core.domain.communication.token.Token;
import org.simbasecurity.core.service.communication.token.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;

@Transactional
@Service
public class ResetPasswordService {

    @Autowired
    private MailService mailService;
    @Autowired
    private TokenGenerator tokenGenerator;
    @Autowired
    private UrlGenerator urlGenerator;

    public void sendMessage(User user) {
        Token token = tokenGenerator.generateToken(user);
        URL link = urlGenerator.generateResetPasswordUrl(user, token);
        mailService.sendMail(user.getEmail(), link);
    }

}
