import React, {Component} from 'react';
import classes from '../Registration/Registration.module.css';
import logo from '../../images/logo.png'
import { Link } from 'react-router-dom';
import axios from "axios";


class Registration extends Component {
  constructor() {
    super()
    this.state = {
        email: "",
        username: "",
        password: "",
        description:"",
        errorMessage: ""
    }

    this.handleChange = this.handleChange.bind(this)
    this.handleSubmit = this.handleSubmit.bind(this)
}

handleChange(event) {
    this.setState({
        [event.target.name]: event.target.value
    })
}

handleSubmit(event) {

    window.alert(this.state.email+" "+this.state.username+" "+this.state.password+" "+this.state.description);
    axios.post('http://localhost:8090/user-service/register', {
        email: this.state.email,
        username: this.state.username,
        password: this.state.password,
        description: this.state.description
    })
        .then((response) => {
          
        }, (error) => {
            this.setState({ errorMessage: "Pogrešni podaci" })
        });
        window.alert("Success registration please login with your data");
        this.props.history.push('/login')

    event.preventDefault()
}
    render() {
      return (
          <div>
              <div className={classes.left}>
                <img className={classes.logo} src={logo}></img>
                <span className={classes.connect}>Connects people</span>
              </div>
              <form className={classes.form} onSubmit={this.handleSubmit}>
                  <span className={classes.title}>Registration</span>
                  <label>Email: <input name="email" type="email" onChange={this.handleChange}></input></label>
                  <label>Username: <input name="username" onChange={this.handleChange}></input></label>
                  <label>Password: <input name="password" type="password" onChange={this.handleChange}></input></label>
                  <label>What others should know about you?(Description) <textarea name="description" onChange={this.handleChange}></textarea></label>
                  <button className={classes.btn} type="submit">Register</button>
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