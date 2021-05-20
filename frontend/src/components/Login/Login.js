import React, {Component} from 'react';
import classes from '../Login/Login.module.css';
import { Link } from 'react-router-dom';
import logo from '../../images/logo.png'

class Login extends Component {
    render() {
      return (
          <div className={classes.container}>
            <div className={classes.left}>
                <img className={classes.logo} src={logo}></img>
                <span className={classes.connect}>Connects people</span>
            </div>
            <div>
                <form className={classes.form}>
                    <span className={classes.title}>Log In</span>
                    <label>Username: <input></input></label>
                    <label>Password: <input type="password"></input></label>
                    <button className={classes.btn}>Log in</button>
                    <Link to="/registration" className={classes.forget}>Forget password?</Link>
                </form>
              </div>
          </div>
      )
    }
  }

  export default Login;