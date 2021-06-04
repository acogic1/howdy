import React, {Component} from 'react';
import classes from '../Conversation/Conversation.module.css';
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import OneMessage from './OneMessage';
import {Link, useLocation, useParams} from "react-router-dom";
import axios from "axios"



class Conversation extends Component {
  constructor(props) {
    super(props)
    this.state = {
        
        validToken: false,
        user:"",
        u1:"",
        u2:"",
        mess:[]
    };

}


  componentWillMount() {
    const { match: { params } } = this.props;
    window.alert(params.username)
    this.state.user=params.username;
    var url = "http://localhost:8090/user-service/validate-token"
  axios.post(url,{
    token:localStorage.token,
    username:localStorage.username
  }).then((res)=>{
      this.setState({ validToken: true })
      var url = "http://localhost:8090/user-service/user/"+localStorage.username
          //window.alert(url)
          axios.get(url, {
            headers: {
                Authorization: "Bearer " + localStorage.token
            }
        }).then((res)=>{
          this.state.u1=res.data;
          var url = "http://localhost:8090/user-service/user/"+params.username;
          //window.alert(url)
          axios.get(url, {
            headers: {
                Authorization: "Bearer " + localStorage.token
            }
        })
          .then((res)=>{
          this.state.u2=res.data;
          var url = "http://localhost:8090/messages-followers-following-service/messages/conversation/"+this.state.u1+"/"+this.state.u2;
          window.alert(url);
          
          axios.get(url, {
            headers: {
                Authorization: "Bearer " + localStorage.token
            }
        }).then((res)=>{
          const mess = res.data;
          window.alert(res.data)
            this.setState({ mess: mess });
            console.log(mess);
        })
          
        })
        })
  })
  }
    render() {
      if (!this.state.validToken) {
        return (
            <div></div>
        )
    }
      return (
          <div className={classes.container}>
            <Header></Header>
           <div className={classes.title}>User</div>
           <div className={classes.listMsg}>
           {
               this.state.mess.map(inb =>(
                 <OneMessage
                  key={inb.id}
                  username={inb.id_sender.username}
                  content={inb.content}>

                  </OneMessage>
               ))
             }
               

           </div>
            <Footer></Footer>
          </div>
      )
    }
  }

  export default Conversation;