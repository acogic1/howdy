package com.example.howdyuser.User;

import com.example.howdyuser.ExceptionClasses.BadRequestException;
import com.example.howdyuser.ExceptionClasses.InternalServerException;
import com.example.howdyuser.ExceptionClasses.NotFoundException;
import org.hibernate.boot.jaxb.hbm.spi.JaxbHbmSynchronizeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserModelAssembler assembler;

    public List<User> GetAll(){
        try {
            List<User> users =userRepository.findAll();
            return  userRepository.findAll().stream()
                    .collect(Collectors.toList());
        }
        catch (Exception e){
            throw new InternalServerException();
        }
    }

    public Long findIDByUsername(String username){
        List<User> users=userRepository.findAll();
        Long id=0L;
        for (User user:users){
            if(user.getUsername().equals(username)){
                id=user.getId();
            }
        }
        if(id.equals(0L)){
            throw new InternalServerException();
        }
        return id;
    }

    public ResponseEntity<EntityModel<User>> Add(User newUser){
        try {
            EntityModel<User> entityModel = assembler.toModel(userRepository.save(newUser));

            ResponseEntity<EntityModel<User>> result = ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
            logger.debug("prije poziva");
            //HttpHeaders headers = new HttpHeaders();
            //headers.add("Content-Type", "application/json");

            UserDTO userDTO=new UserDTO(newUser.getId(), newUser.getUsername(), newUser.getPassword());
            //HttpEntity<UserDTO> request = new HttpEntity<>(userDTO, headers);
            InsertUserMFFService(userDTO);
            System.out.println("prije poziva");

            //final String URLSender = "http://messages-followers-following-service/users";
            //URI uriSender = new URI(URLSender);
            //ResponseEntity<UserDTO> resultSender = restTemplate.postForEntity(uriSender,request, UserDTO.class);
            //System.out.println("poziv "+resultSender.getStatusCode());
            return  result;
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


    private void InsertUserMFFService(UserDTO userDTO){
        logger.debug("pozvao");
        System.out.println("pozvao se");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<UserDTO> request = new HttpEntity<>(userDTO, headers);


        ResponseEntity<UserDTO> result = restTemplate.postForEntity("http://messages-followers-following-service/users", request, UserDTO.class);
    }


}
