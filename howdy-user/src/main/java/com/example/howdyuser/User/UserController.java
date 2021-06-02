package com.example.howdyuser.User;


import com.example.howdyuser.ExceptionClasses.BadRequestException;
import com.example.howdyuser.ExceptionClasses.InternalServerException;
import com.example.howdyuser.ExceptionClasses.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
//@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    List<User> GetAll(){
        return userService.GetAll();

    }

    @GetMapping("/user/{username}")
    Long one(@PathVariable String username){
        try {
            return userService.findIDByUsername(username);
        }
        catch (Exception e){
            throw new InternalServerException();
        }

    }

    @PostMapping("/users")
    User Add(@RequestBody User newUser)  {

        try {
            return  userService.Add(newUser);
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
    User GetById(@PathVariable Long id){
        return userService.GetById(id);

    }

    @PutMapping("/users/{id}")
    User Update(@RequestBody User newUser, @PathVariable Long id){
        try {
            return userService.Update(newUser, id);
        }
        catch (ConstraintViolationException ex) {
            throw new BadRequestException(ex.getMessage());
        }
        catch (NotFoundException ex) {
            throw ex;
        }
        catch (Exception ex) {
            throw new InternalServerException();
        }
    }

    @PutMapping("/users/picture/{id}")
    void UpdatePicture(@RequestParam("picture") MultipartFile file , @PathVariable String id){
        try {
            userService.UpdatePicture(file, Long.valueOf(id));
        }
        catch (ConstraintViolationException ex) {
            throw new BadRequestException(ex.getMessage());
        }
        catch (NotFoundException ex) {
            throw ex;
        }
        catch (Exception ex) {
            throw new InternalServerException();
        }
    }

    @DeleteMapping("/users/{id}")
    void Delete(@PathVariable Long id) {
        try {
            userService.Delete(id);
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
