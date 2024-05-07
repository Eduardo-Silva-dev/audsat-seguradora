package br.com.audsat.seguradora.service.impl;

import br.com.audsat.seguradora.model.User;
import br.com.audsat.seguradora.repository.UserRepository;
import br.com.audsat.seguradora.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
