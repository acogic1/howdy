import React, {Component} from 'react';
import classes from '../Registration/Registration.module.css';
import logo from '../../images/logo.png'
import { Link } from 'react-router-dom';


class Registration extends Component {
    render() {
      return (
          <div>
              <div className={classes.left}>
                <img className={classes.logo} src={logo}></img>
                <span className={classes.connect}>Connects people</span>
              </div>
              <form className={classes.form}>
                  <span className={classes.title}>Registration</span>
                  <label>Username: <input></input></label>
                  <label>Password: <input type="password"></input></label>
                  <label>Birthday: <input type="date" id="start" name="trip-start"  min="1960-01-01" max="2021-12-31"></input></label>
                  <label>What others should know about you?(Description) <textarea></textarea></label>
                  <button className={classes.btn}>Register</button>
                  <div className={classes.login}>
                    <div>Already have an account?</div> 
                    <Link to="/login">Log in</Link>
                  </div>
              </form>
          </div>
      )
    }
  }

  export default Registration;