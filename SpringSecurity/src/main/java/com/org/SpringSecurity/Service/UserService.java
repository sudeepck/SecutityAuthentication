package com.org.SpringSecurity.Service;

import com.org.SpringSecurity.Model.Users;
import com.org.SpringSecurity.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        return  userRepo.save(user);
    }

    public  String verify(Users user) {
        Authentication authentication = authenticationManager.
                authenticate( new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()){
            System.out.println("login Succefully" + " " + user);
           return jwtService.generateToken(user);
        }
        return  "failed";
    }

    public Users updatePassword(Users user) throws UsernameNotFoundException {
        Users currentUser = userRepo.findByUsername(user.getUsername());
        System.out.println("currentUser" + " " + currentUser);
        if(currentUser == null){
            System.out.println("UsernameNotFoundException 404 !");
             throw new UsernameNotFoundException( "UsernameNotFoundException 404!");
        }
        currentUser.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(currentUser);
    }
}
