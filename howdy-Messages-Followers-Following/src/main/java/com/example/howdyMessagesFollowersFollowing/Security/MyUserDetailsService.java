package com.example.howdyMessagesFollowersFollowing.Security;

//import com.example.howdyuser.User.User;
import com.example.howdyMessagesFollowersFollowing.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Connect with database
        com.example.howdyMessagesFollowersFollowing.User.User user = userRepository.findByUsername(username);
        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getUsername()));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
