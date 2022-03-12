package com.example.demo.Mvc;


import com.example.demo.entity.User;
import com.example.demo.payload.UserDto;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/library/enter/check")
public class UserPageController {
    @Autowired
    UserRepository userRepository;
    @PostMapping
    public String checkIsAdmin(Model model, UserDto userDto){
        for (User user : userRepository.findAll()) {
            if(user.getUserName().equals(userDto.getUserName()) && user.getPassword().equals(userDto.getPassword())){
               if(user.getRole()){
                   return "pageAdmin";
               }else{
                   return "pageUser";
               }
            }
        }
        return "registration";


    }
}
