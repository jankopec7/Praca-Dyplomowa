package project.ztpai.shop.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.ztpai.shop.security.model.ShopUserDetails;
import project.ztpai.shop.security.model.User;
import project.ztpai.shop.security.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;



    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.parseLong(username)).orElseThrow();
        ShopUserDetails shopUserDetails = new ShopUserDetails(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities().stream()
                        .map(userRole -> (GrantedAuthority) userRole::name)
                        .toList()
        );
        shopUserDetails.setId(user.getId());
        return shopUserDetails;
    }
}
