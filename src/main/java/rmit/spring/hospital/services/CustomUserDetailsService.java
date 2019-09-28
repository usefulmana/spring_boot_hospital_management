package rmit.spring.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rmit.spring.hospital.models.User;
import rmit.spring.hospital.repositories.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(s);
        CustomUserDetails userDetails=null;
        if (user!=null){
            userDetails = new CustomUserDetails();
            userDetails.setUser(user);
        }
        else{
            throw new UsernameNotFoundException("User does not exist!");
        }
        return userDetails;
    }
}
