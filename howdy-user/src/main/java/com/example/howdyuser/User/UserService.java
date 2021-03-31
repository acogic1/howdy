package com.example.howdyuser.User;

import com.example.howdyuser.ExceptionClasses.BadRequestException;
import com.example.howdyuser.ExceptionClasses.InternalServerException;
import com.example.howdyuser.ExceptionClasses.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> GetAll(){
        try {
            return  userRepository.findAll();
        }
        catch (Exception e){
            throw new InternalServerException();
        }
    }

    public User Add(User newUser){
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

    public User GetById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user",id));
    }

    public void UpdatePicture(MultipartFile file,Long id){
        try {
            User user=userRepository.findById(id).orElseThrow(()->new NotFoundException("user",id));
            Byte[] pic=new Byte[file.getBytes().length];
            int i=0;
            for (byte b: file.getBytes()
                 ) {
                pic[i++]=b;
            }

            user.setPicture(pic);
            userRepository.save(user);
        }
        catch (IOException e){

        }
    }

    public User Update(User newUser,Long id){
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

    public void Delete(Long id){
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
