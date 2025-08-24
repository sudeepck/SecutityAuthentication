package com.org.SpringSecurity.Security;

import com.org.SpringSecurity.Model.Users;
import com.org.SpringSecurity.Repository.UserRepo;
import com.org.SpringSecurity.dto.LoginRequestDto;
import com.org.SpringSecurity.dto.LoginresponseDto;
import com.org.SpringSecurity.dto.SignUpRequestDto;
import com.org.SpringSecurity.dto.SignUpResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtAuthUtil jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);



    public LoginresponseDto verify(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword()));
        Users user = (Users) authentication.getPrincipal();
        String token="";
        if(authentication.isAuthenticated()){
           token = jwtService.generateToken(user);
            System.out.println("login Succefully the token is:" + " " + token);
        }
        return new LoginresponseDto(token,user.getId());
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

    public SignUpResponseDto signup(SignUpRequestDto signUpRequestDto) throws IllegalAccessException {
        Users user = userRepo.findByUsername(signUpRequestDto.getUsername());
        System.out.println("currentUser" + " " + user);


        if(user != null){
            throw  new IllegalAccessException("user Already Exists");
        }

        user = userRepo.save(Users.
                builder().
                username(signUpRequestDto.getUsername()).
                password(encoder.encode(signUpRequestDto.getPassword())).
                build());
        return  new SignUpResponseDto(user.getId(),user.getUsername());
    }
}
