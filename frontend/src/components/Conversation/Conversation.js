import React, {Component} from 'react';
import classes from '../Conversation/Conversation.module.css';
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import OneMessage from './OneMessage';
import axios from "axios"

class Conversation extends Component {
  constructor(props) {
    super(props)
    this.state = {
        
        validToken: false,
        user:"",
        u1:"",
        u2:"",
        mess:[],
        message:"",
        uu1:Object,
        uu2:Object,
        currentDate:Date(),
        draz:0
    };
    this.handleChange = this.handleChange.bind(this)
    this.handleSubmit = this.handleSubmit.bind(this)
}

handleChange(event) {
  this.setState({
      [event.target.name]: event.target.value
  })
}

handleSubmit(event) {
  var url = "http://localhost:8090/user-service/users/"+this.state.u1
          axios.get(url, {
            headers: {
                Authorization: "Bearer " + localStorage.token
            }
        }).then((res)=>{
          this.state.uu1=res.data
          var url = "http://localhost:8090/user-service/users/"+this.state.u2
          axios.get(url, {
            headers: {
                Authorization: "Bearer " + localStorage.token
            }
        }).then((res)=>{
          this.state.uu2=res.data
          
          var url = "http://localhost:8090/messages-followers-following-service/messages";
          const options = {
            headers: {
              Authorization: "Bearer " + localStorage.token
          }
          };
  axios.post(url,
    {
      id_sender:this.state.uu1,
      id_reciever:this.state.uu2,
      content:this.state.message
    },options).then((res)=>{
      window.location.reload();
    })
        })
        })
    event.preventDefault()
}
  
  componentWillMount() {
    const { match: { params } } = this.props;
    this.state.user=params.username;
    var url = "http://localhost:8090/user-service/validate-token"
  axios.post(url,{
    token:localStorage.token,
    username:localStorage.username
  }).then((res)=>{
      this.setState({ validToken: true })
      var url = "http://localhost:8090/user-service/user/"+localStorage.username
          axios.get(url, {
            headers: {
                Authorization: "Bearer " + localStorage.token
            }
        }).then((res)=>{
          this.state.u1=res.data;
          var url = "http://localhost:8090/user-service/user/"+params.username;
          axios.get(url, {
            headers: {
                Authorization: "Bearer " + localStorage.token
            }
        })
          .then((res)=>{
              this.state.u2=res.data;
              var url = "http://localhost:8090/messages-followers-following-service/messages/conversation/"+this.state.u1+"/"+this.state.u2;
              
              axios.get(url, {
                headers: {
                    Authorization: "Bearer " + localStorage.token
                }
            }).then((res)=>{
              const mess = res.data;
              this.setState({ mess: mess });
              this.state.draz=mess.length;
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
            <div className={classes.title}>{this.state.user || "User"}</div>
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
              { this.state.mess &&  
              <div>
                <form className={classes.form} onSubmit={this.handleSubmit}>
                  <input className={classes.message} placeholder="Write message" name="message" onChange={this.handleChange}></input>
                  <button className={classes.btn} type="submit">Send</button>
                </form>
              </div>}
           </div>
            <Footer></Footer>
          </div>
      )
    }
  }

  export default Conversation;