package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.model.Account;
import com.example.casestudymodule4.model.Company;
import com.example.casestudymodule4.model.DTO.RoleName;
import com.example.casestudymodule4.model.DTO.request.SignupForm;
import com.example.casestudymodule4.model.DTO.response.JwtResponse;
import com.example.casestudymodule4.model.DTO.response.ResponseMessage;
import com.example.casestudymodule4.model.Role;
import com.example.casestudymodule4.model.User;
import com.example.casestudymodule4.service.JwtService;
import com.example.casestudymodule4.service.ext.IAccountService;
import com.example.casestudymodule4.service.ext.ICompanyService;
import com.example.casestudymodule4.service.ext.IRoleService;
import com.example.casestudymodule4.service.ext.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    @Autowired
    private IUserService userService;
    @Autowired
    private ICompanyService companyService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getGmail(), account.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Account account1 = accountService.findByGmail(account.getGmail()).get();
        return ResponseEntity.ok(new JwtResponse(account1.getId(),jwt, userDetails.getUsername(), account1.getRoleName(), userDetails.getAuthorities()));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignupForm signupForm){
        if (accountService.existsByGmail(signupForm.getGmail())){
            return new ResponseEntity<>(new ResponseMessage("the gmail existed!"), HttpStatus.OK);
        }
        Account account = new Account(signupForm.getGmail(), signupForm.getPassword());
        Set<String> strRoles = signupForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role){
                case "ROLE_USER" -> {
                    Role userRole = roleService.findByName(String.valueOf(RoleName.ROLE_USER));
                    roles.add(userRole);
                    User user = new User(signupForm.getGmail());
                    userService.save(user);
                    account.setUser(user);
                }
                case "ROLE_COMPANY" ->{
                    Role companyRole = roleService.findByName(String.valueOf(RoleName.ROLE_COMPANY));
                    Company company = new Company(signupForm.getGmail());
                    companyService.save(company);
                roles.add(companyRole);
                account.setCompany(company);
                }
//                case "ROLE_ADMIN" -> {
//                    Role adminRole = roleService.findByName(String.valueOf(RoleName.ROLE_ADMIN));
//
//                }
            }
                }
        );
        account.setRoles(roles);
        accountService.save1(account);
        return new ResponseEntity<>(new ResponseMessage("Create user success!"), HttpStatus.CREATED);
    }
    @GetMapping("/logout")
    public ResponseEntity<?> logout(){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie c:
                 cookies) {
                if (c.getName().equals("token")){
                    c.setValue(null);
                    c.setMaxAge(0);
                    c.setPath("/");
                    response.addCookie(c);
                    break;
                }
            }
        }
        return new ResponseEntity<>("Logout successfully!", HttpStatus.OK);
    }
    @GetMapping("/user")
    public ResponseEntity<String> getName(){
        return new ResponseEntity<>("doan cho dien", HttpStatus.OK);
    }
    @GetMapping("/company")
    public ResponseEntity<String> get(){
        return new ResponseEntity<>("A nguyen", HttpStatus.OK);
    }
    @GetMapping("/listRole")
    public ResponseEntity<Iterable<Role>> listRoles(){
        return new ResponseEntity<>(roleService.findAllRole(), HttpStatus.OK);
    }
    @GetMapping("/user/viewRole/{id}")
    public ResponseEntity<Role> viewRole(@PathVariable Long id){
        return new ResponseEntity<>(roleService.findOne(id), HttpStatus.OK);
    }
}
