package br.com.audsat.seguradora.service;

import br.com.audsat.seguradora.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserDetails findByLogin(String login);

    User save(User user);
}
