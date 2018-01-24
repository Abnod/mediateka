package abnod.mediateka.util;

import abnod.mediateka.mapper.UserMapper;
import abnod.mediateka.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserMapper userMapper;

    @Autowired
    public UserDetailService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userMapper.getUserByLogin(login);
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getAuthorities(user));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> authList = new ArrayList<>();
        String role;
        if (user.getAdmin()) {
            role = "ROLE_ADMIN";
        } else {
            role = "ROLE_USER";
        }
        SimpleGrantedAuthority sGA = new SimpleGrantedAuthority(role);
        authList.add(sGA);
        return authList;
    }
}
