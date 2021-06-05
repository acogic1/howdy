import React, {Component} from 'react';
import classes from '../Header/Header.module.css';
import logo from '../../images/logo.png'
import notification_image from '../../images/notification_image.jpg';
import logout_image from '../../images/arrow.png';
import { Link } from 'react-router-dom';
import axios from 'axios';


class Header extends Component {
  constructor() {
    super()
    this.state = {
        notification: [],
        options:[]
    }
  }

  componentDidMount() {
    axios.get('http://localhost:8090/newsfeed-service/api/users', {
      headers: {
          Authorization: "Bearer " + localStorage.token
      }
  })
    .then(res => {
      const users = res.data;
      this.setState({ options: users });
    }).catch(err => (console.log(err)))
  }

clickLogout(){

  localStorage.clear();
}

    render() {
      return (
          <div className={classes.container}>
              <div className={classes.main_div}>
                <img className={classes.logo_header} src={logo}/>
                <input className={classes.search} list="tags" placeholder={"Search"}></input>

                  <datalist id="tags">
                  {
                    this.state.options.map(option => (
                      <option key={option.id} value={option.username}/>
                    ))
                  }
                  </datalist>
                <div className={classes.meni}>
                    <Link to="/newsfeed">NewsFeed</Link>
                    <Link to="/profile">Profile</Link>
                    <Link to="/messages">Messages</Link>
                    <img className={classes.notif_img} src={notification_image} />
                    <Link to="/login" onClick={() => this.clickLogout()}>
                        <img className={classes.logout_img} src={logout_image}/>
                    </Link>
                </div>
              </div>
          </div>
      )
    }
  }

  export default Header;