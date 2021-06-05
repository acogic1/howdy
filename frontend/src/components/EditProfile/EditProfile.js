import React, {Component} from 'react';
import classes from '../EditProfile/EditProfile.module.css';
import logo from '../../images/logo.png'
import notification_image from '../../images/notification_image.jpg';
import logout_image from '../../images/arrow.png';
import { Link } from 'react-router-dom';
import axios from 'axios';
import Header from '../Header/Header';


class EditProfile extends Component {
  constructor() {
    super()
    this.state = {
        
    }
  }

  componentDidMount() {
    
  }

    render() {
      return (
          <div className={classes.container}>
              <Header></Header>
              <div className={classes.title}>Edit profile</div>
              <form className={classes.forma}>
              <label><input placeholder={localStorage.username} ></input>Username: </label>
              <label><input ></input>Password: </label>
              <label><input ></input>Email: </label>
              <label><input ></input>Description: </label>
              <button  className={classes.btnSave}>Save changes</button>
              </form>
          </div>
      )
    }
  }

  export default EditProfile;