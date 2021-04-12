package com.example.howdyMessagesFollowersFollowing.Message;

import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.BadRequestException;
import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.InternalServerException;
import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.NotFoundException;
import com.example.howdyMessagesFollowersFollowing.User.User;
import com.example.howdyMessagesFollowersFollowing.User.UserRepository;
import com.sun.xml.bind.v2.model.core.ID;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    public List<Message> GetAll(){
        List<Message> messages=messageRepository.findAll();
        return messages;
    }

    public Message GetById(Long id){
        Message message= messageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("message", id));
        return message;
    }

    public List<User> GetInboxById(Long id){
        List<Tuple> usrs=messageRepository.findInboxByUser(id);


        List<User> users=new ArrayList<>();

        for (Tuple usr: usrs
             ) {
            try {
                BigInteger id_send= (BigInteger) usr.get("id_sender");
                Long id_s=id_send.longValue();
                BigInteger id_rec= (BigInteger) usr.get("id_reciever");
                Long id_r=id_rec.longValue();
                if(id_s == id){
                    User user=userRepository.findById(id_r).orElseThrow(()->new NotFoundException("user",id_r));
                    if(!users.contains(user))
                        users.add(user);
                }
                else if(id_r==id){
                    User user=userRepository.findById(id_s).orElseThrow(()->new NotFoundException("user",id_s));
                    if(!users.contains(user))
                        users.add(user);
                }
            }
            catch (Exception e){
                throw new BadRequestException(e.getMessage());
            }


        }
        return users;
    }

    public List<Message> GetConversation(Long user1,Long user2){
        List<Tuple> msgs=messageRepository.findConversationByUsers(user1,user2);
        List<Message> messages=new ArrayList<>();

        for (Tuple ms:msgs
             ) {
            try {
                BigInteger id_m= (BigInteger) ms.get("id");
                Long id_mm=id_m.longValue();
                BigInteger id_send= (BigInteger) ms.get("id_sender");
                Long id_s=id_send.longValue();
                BigInteger id_rec= (BigInteger) ms.get("id_reciever");
                Long id_r=id_rec.longValue();
                Date date= (Date) ms.get("date_time");
                String con= (String) ms.get("content");

                User user_s=userRepository.findById(id_s).orElseThrow(()->new NotFoundException("user",id_s));
                User user_r=userRepository.findById(id_r).orElseThrow(()->new NotFoundException("user",id_r));
                //Message m=new Message(user_s,user_r,date,con);
                Message m=messageRepository.findById(id_mm).orElseThrow(()-> new NotFoundException("message",id_mm));
                messages.add(m);
            }
            catch (Exception e){
                throw new BadRequestException(e.getMessage());
            }
        }
        return messages;
    }

    public Message Add(Message newMessage){
        try {
            Optional<User> user_sender = userRepository.findById(newMessage.getId_sender().getId());
            Optional<User> user_reciever = userRepository.findById(newMessage.getId_reciever().getId());
            final String URLSender = "http://user-service/users/"+newMessage.getId_sender().getId();
            final String URLReciever = "http://user-service/users/"+newMessage.getId_sender().getId();
            URI uriSender = new URI(URLSender);
            URI uriReciever = new URI(URLReciever);
            /*HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            HttpEntity<User> request = new HttpEntity<>(headers);*/

            ResponseEntity<User> resultSender = restTemplate.getForEntity(uriSender, User.class);
            ResponseEntity<User> resultReciever = restTemplate.getForEntity(uriReciever, User.class);
            System.out.println(resultSender.getBody().getUsername());
            System.out.println(resultReciever.getBody().getUsername());


            return messageRepository.save(newMessage);
        } catch (NotFoundException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerException();
        }
    }

    public Message Update(Message newMessage,Long id){
        try {
            return messageRepository.findById(id)
                    .map(message -> {
                        try {
                            message.setId_sender(newMessage.getId_sender());
                            message.setId_reciever(newMessage.getId_reciever());
                            message.setData_time(newMessage.getData_time());
                            message.setContent(newMessage.getContent());
                            return messageRepository.save(message);
                        } catch (ConstraintViolationException e) {
                            throw new BadRequestException(e.getMessage());
                        }
                    })
                    .orElseGet(() -> {
                        newMessage.setId(id);
                        return messageRepository.save(newMessage);
                    });

        } catch (NotFoundException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
            throw e;
        }
    }

    public void Delete(Long id){
        try {
            messageRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("message", id);
            //throw new UserNotFoundException(id);
        } catch (Exception e) {
            throw new InternalServerException();
        }
    }
}
