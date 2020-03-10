package com.fends.login.service;

import com.fends.login.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserModel user = userService.findUserByUsername(username);
        List<GrantedAuthority> authorities = getUserAuthority(user.getRole());
        return buildUserFOrAuthentication(user, authorities);
    }

    private List<GrantedAuthority> getUserAuthority(String userRole){
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(userRole));
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserFOrAuthentication(UserModel user, List<GrantedAuthority> authorities){
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
    }
}
