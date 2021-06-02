import React, {Component} from 'react';
import classes from '../Follow/Follow.module.css';
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import MessageItem from '../MessageItem/MessageItem';
import axios from "axios"


class Followers extends Component {

    constructor() {
        super()
        this.state = {
            follow: []
        }
    }

    componentWillMount(){
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
            var url = "http://localhost:8090/messages-followers-following-service/subscriptions/followers/"+res.data;
          axios.get(url, {
            headers: {
                Authorization: "Bearer " + localStorage.token
            }
        }).then((res)=>{
          const follow = res.data;
          window.alert(res.data)
            this.setState({ follow: follow });
            console.log(follow);
        })
          })
      })
    }

    render() {
      return (
          <div className={classes.container}>
            <Header></Header>
           <div className={classes.title}>Followers</div>
           <div className={classes.listF}>

            {this.state.follow.map(f => (
                <div className={classes.username}>{f.username}</div>
            ))}

           </div>
            <Footer></Footer>
          </div>
      )
    }
  }

  export default Followers;