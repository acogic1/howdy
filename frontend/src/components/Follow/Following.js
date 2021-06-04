import React, {Component} from 'react';
import classes from '../Follow/Follow.module.css';
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import { Link } from 'react-router-dom';
import axios from "axios"


class Following extends Component {

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
            var url = "http://localhost:8090/messages-followers-following-service/subscriptions/following/"+res.data;
          axios.get(url, {
            headers: {
                Authorization: "Bearer " + localStorage.token
            }
        }).then((res)=>{
          const follow = res.data;
            this.setState({ follow: follow });
        })
          })
      })
    }

    displayContent(e, index){
      e.preventDefault();
      window.alert("id "+index)
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
           <div className={classes.title}>Following</div>
           <div className={classes.listF}>
            {this.state.follow.map(f => (
              <div className={classes.row}>
                <div className={classes.username}>{f.username}</div>
                <button type="button" className={classes.unfollowBtn} onClick={(e)=>this.displayContent(e,f.id)}>Unfollow</button>
              </div>
            ))}
           </div>
            <Footer></Footer>
          </div>
      )
    }
  }

  export default Following;