import React, {Component} from 'react';
import classes from '../Messages/Messages.module.css';
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import MessageItem from '../MessageItem/MessageItem';
import axios from "axios"


class Messages extends Component {

  constructor(props) {
    super(props)
    this.state = {
        
        validToken: false,
        inbox:[]//lista usera
    };

}

componentWillMount() {
  console.log("USAO")
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
          window.alert("id od "+localStorage.username+" je "+res.data)
          var url = "http://localhost:8090/messages-followers-following-service/messages/inbox/"+res.data;
          axios.get(url, {
            headers: {
                Authorization: "Bearer " + localStorage.token
            }
        }).then((res)=>{
          const inbox = res.data;
          window.alert(res.data)
            this.setState({ inbox: inbox });
            console.log(inbox);
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
           <div className={classes.title}>Messages</div>
           <div className={classes.listMsg}>
             {
               this.state.inbox.map(inb =>(
                 <MessageItem
                  key={inb.id}
                  username={inb.username}
                  picture={inb.picture}>

                  </MessageItem>
               ))
             }
               
           </div>
            <Footer></Footer>
          </div>
      )
    }
  }

  export default Messages;