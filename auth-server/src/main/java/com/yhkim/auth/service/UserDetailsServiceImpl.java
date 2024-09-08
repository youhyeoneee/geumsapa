package com.yhkim.auth.service;


import com.yhkim.auth.UserDetailsImpl;
import com.yhkim.domain.user.entity.User;
import com.yhkim.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final UserRepository userRepository;
    
    // TODO: UsernameNotFoundException GlobalExceptionHandler 추가
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + "not found."));
        return new UserDetailsImpl(user);
    }
}
