package com.example.howdyuser.User;


import com.example.howdyuser.ExceptionClasses.BadRequestException;
import com.example.howdyuser.ExceptionClasses.InternalServerException;
import com.example.howdyuser.ExceptionClasses.NotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
//@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    UserController(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @GetMapping("/users")
    List<User> all(){
        try {
            return  userRepository.findAll();
        }
        catch (Exception e){
            throw new InternalServerException();
        }

    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser)  {

        try {
            return  userRepository.save(newUser);
        } catch (NotFoundException e){
            throw e;
        }
        catch (ConstraintViolationException e){
            throw new BadRequestException(e.getMessage());
        }
        catch (Exception e){
            throw new InternalServerException();
        }

    }

    @GetMapping("/users/{id}")
    User one(@PathVariable Long id){
        return userRepository.findById(id)
               .orElseThrow(() -> new NotFoundException("user",id));
        /*try{
            return userRepository.findById(id).orElseThrow(()->new NotFoundException("user",id));
        }
        catch (NotFoundException e){
            throw new NotFoundException("user",id);
        }catch (Exception e){
            throw new InternalServerException();
        }*/
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id){
        try {
            return userRepository.findById(id)
                    .map(user -> {
                        try {
                            user.setEmail(newUser.getEmail());
                            user.setUsername(newUser.getUsername());
                            user.setPassword(newUser.getPassword());
                            user.setDescription(newUser.getDescription());
                            return userRepository.save(user);
                        }
                        catch (ConstraintViolationException e){
                            throw new BadRequestException(e.getMessage());
                        }
                    })
                    .orElseGet(() ->{
                        newUser.setId(id);
                        return userRepository.save(newUser);
                    });

        }
        catch (NotFoundException e){
            throw e;
        }
        catch (ConstraintViolationException e){
            throw new BadRequestException(e.getMessage());
        }
        catch (Exception e){
            throw e;
        }
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new NotFoundException("user",id);
            //throw new UserNotFoundException(id);
        }
        catch (Exception e){
            throw new InternalServerException();
        }
    }
}
