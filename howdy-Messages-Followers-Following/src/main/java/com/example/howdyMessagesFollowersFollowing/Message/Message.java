package com.example.howdyMessagesFollowersFollowing.Message;

import com.example.howdyMessagesFollowersFollowing.User.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @NotNull(message = "Variable id_sender must not be null")
    @JoinColumn(name = "id_sender")
    private User id_sender;

    @ManyToOne
    @NotNull(message = "Variable id_reciever must not be null")
    @JoinColumn(name = "id_reciever")
    private User id_reciever;

    private Date date_time;
    @NotNull(message = "Variable content must not be null")
    private String content;

    public Message(Long id,@NotNull(message = "Variable id_sender must not be null") User id_sender,@NotNull(message = "Variable id_reciever must not be null") User id_reciever,@NotNull(message = "Variable date_time must not be null") Date date_time,@NotNull(message = "Variable content must not be null") String content) {
        this.id = id;
        this.id_sender = id_sender;
        this.id_reciever = id_reciever;
        this.date_time = date_time;
        this.content = content;
    }

    public Message(@NotNull(message = "Variable id_sender must not be null") User id_sender,@NotNull(message = "Variable id_reciever must not be null") User id_reciever,@NotNull(message = "Variable content must not be null") String content) {
        this.id_sender = id_sender;
        this.id_reciever = id_reciever;
        Calendar cal=Calendar.getInstance();
        Date date=cal.getTime();
        this.date_time = date;
        this.content = content;
    }
    public Message(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getId_sender() {
        return id_sender;
    }

    public void setId_sender(User id_sender) {
        this.id_sender = id_sender;
    }

    public User getId_reciever() {
        return id_reciever;
    }

    public void setId_reciever(User id_reciever) {
        this.id_reciever = id_reciever;
    }

    public Date getData_time() {
        return date_time;
    }

    public void setData_time(Date data_time) {
        this.date_time = data_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static void getCurrentTimeUsingCalendar() {
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate=dateFormat.format(date);
        System.out.println("Current time of the day using Calendar - 24 hour format: "+ formattedDate);
    }
}
