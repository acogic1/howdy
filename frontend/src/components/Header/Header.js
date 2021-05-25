import React, {Component} from 'react';
import classes from '../Header/Header.module.css';
import logo from '../../images/logo.png'
import notification_image from '../../images/notification_image.jpg';
import logout_image from '../../images/arrow.png';
import { Link } from 'react-router-dom';


class Header extends Component {
    render() {
      return (
          <div className={classes.container}>
              <div className={classes.main_div}>
                <img className={classes.logo_header} src={logo}/>
                <input className={classes.search} placeholder={"Search"}></input>
                <div className={classes.meni}>
                    <Link to="/newsfeed">NewsFeed</Link>
                    <Link to="/profile">Profile</Link>
                    <Link to="/messages">Messages</Link>
                    <img className={classes.notif_img} src={notification_image} />
                    <Link to="/login">
                        <img className={classes.logout_img} src={logout_image} />
                    </Link>
                </div>
              </div>
          </div>
      )
    }
  }

  export default Header;