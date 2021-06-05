package com.example.howdyMessagesFollowersFollowing.Subscription;

import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.BadRequestException;
import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.InternalServerException;
import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.NotFoundException;
import com.example.howdyMessagesFollowersFollowing.User.User;
import com.example.howdyMessagesFollowersFollowing.User.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Subscription> GetAll(){
        List<Subscription> subscriptions=subscriptionRepository.findAll();
        return subscriptions;
    }

    public Subscription GetById(Long id){
        Subscription subscription= subscriptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("subscription",id));
        return subscription;
    }

    public List<User> GetFollowersByUser(Long id){
        List<Tuple> usrs=subscriptionRepository.findFollowersByUser(id);
        User u=userRepository.findById(id).orElseThrow(()->new NotFoundException("user",id));
        List<User> users=new ArrayList<>();
        for (Tuple usr: usrs
        ) {
            try {
                BigInteger id_foll= (BigInteger) usr.get("id_follower");
                Long id_f=id_foll.longValue();

                User user=userRepository.findById(id_f).orElseThrow(()->new NotFoundException("user",id_f));
                if(!users.contains(user))
                    users.add(user);

            }
            catch (Exception e){
                throw new BadRequestException(e.getMessage());
            }


        }
        return users;
    }

    public List<User> GetFollowingByUser(Long id){
        List<Tuple> usrs=subscriptionRepository.findFollowingByUser(id);
        User u=userRepository.findById(id).orElseThrow(()->new NotFoundException("user",id));
        List<User> users=new ArrayList<>();
        for (Tuple usr: usrs
        ) {
            try {
                BigInteger id_foll= (BigInteger) usr.get("id_following");
                Long id_f=id_foll.longValue();

                User user=userRepository.findById(id_f).orElseThrow(()->new NotFoundException("user",id_f));
                if(!users.contains(user))
                    users.add(user);

            }
            catch (Exception e){
                throw new BadRequestException(e.getMessage());
            }


        }
        return users;
    }

    public Subscription Add(Subscription newSubscription){
        try {
            Optional<User> user_follower=userRepository.findById(newSubscription.getId_follower().getId());
            Optional<User> user_following=userRepository.findById(newSubscription.getId_following().getId());
            return  subscriptionRepository.save(newSubscription);
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

    public Subscription Update(Subscription newSubscription,Long id){
        try {
            return subscriptionRepository.findById(id)
                    .map(subscription -> {
                        try {
                            subscription.setId_follower(newSubscription.getId_follower());
                            subscription.setId_following(newSubscription.getId_following());
                            return subscriptionRepository.save(subscription);
                        }
                        catch (ConstraintViolationException e){
                            throw new BadRequestException(e.getMessage());
                        }
                    })
                    .orElseGet(() ->{
                        newSubscription.setId(id);
                        return subscriptionRepository.save(newSubscription);
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
            subscriptionRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new NotFoundException("subscription",id);
            //throw new UserNotFoundException(id);
        }
        catch (Exception e){
            throw new InternalServerException();
        }
    }

    public void DeleteSubs(Long id1,Long id2){
        try {
            List<Subscription> subscriptions=subscriptionRepository.findAll();

            Long id=0L;
            for (Subscription s:subscriptions){
                if(s.getId_follower().getId().equals(id1) && s.getId_following().getId().equals(id2)){
                    id=s.getId();
                }
            }
            if(id!=0L){
                subscriptionRepository.deleteById(id);
            }
            else{
                throw new InternalServerException();
            }
        }
        catch (EmptyResultDataAccessException e){
            throw new NotFoundException("subscription",id1);
            //throw new UserNotFoundException(id);
        }
        catch (Exception e){
            throw new InternalServerException();
        }
    }
}
