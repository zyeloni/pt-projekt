package me.kacperlukasik.klptl2.services;

import me.kacperlukasik.klptl2.ProfilesName;
import me.kacperlukasik.klptl2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@Profile(ProfilesName.USERS_IN_DATABASE)
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        if (!user.isEnabled())
        {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }

}
