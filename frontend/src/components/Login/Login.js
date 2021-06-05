import React, {Component} from 'react';
import classes from '../Login/Login.module.css';
import { Link } from 'react-router-dom';
import logo from '../../images/logo.png'
import axios from 'axios'

class Login extends React.Component {
  constructor() {
    super()
    this.state = {
        username: "",
        password: "",
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

    window.alert("jesam");
    axios.post('http://localhost:8090/user-service/authenticate', {
        username: this.state.username,
        password: this.state.password
    })
        .then((response) => {
            window.alert(response);
            this.setState({ errorMessage: "" })
            localStorage.token = response.data.token
            localStorage.username = JSON.parse(atob(localStorage.token.split('.')[1])).sub

            var url = "http://localhost:8090/user-service/validate-token"
            axios.post(url, {
                token: localStorage.token,
                username: localStorage.username
            })
                .then((response) => {
                    console.log(response.data);
                    this.props.history.push('/profile')
                    /*if (response.data.role == "STUFF") {
                        this.props.history.push('/stuff')
                    }
                    else if (response.data.role == "ADMIN") {
                        this.props.history.push('/adminpage')
                    }
                    else if (response.data.role == "MEMBER") {
                        this.props.history.push('/member')
                    }*/
                })

        }, (error) => {
            this.setState({ errorMessage: "Incorrect password or username" });
        });

        axios.get(`http://localhost:8090/newsfeed-service/api/user/`+this.state.username)
          .then(res => {
            localStorage.id=res.data;
            console.log(localStorage.id);
          }).catch(err => (console.log(err)))

    event.preventDefault()
}  
  
  render() {
      return (
          <div className={classes.container}>
            <div className={classes.left}>
                <img className={classes.logo} src={logo}></img>
                <span className={classes.connect}>Connects people</span>
            </div>
            <div>
                <form className={classes.form} onSubmit={this.handleSubmit}>
                    <span className={classes.title}>Log In</span>
                    <label>Username: <input name="username" onChange={this.handleChange}></input></label>
                    <label>Password: <input name="password" type="password" onChange={this.handleChange}></input></label>
                    {this.state.errorMessage ? 
                        <div className={classes.error}>{this.state.errorMessage}</div>
                    :
                    null
                    }
                    <button className={classes.btn} type="submit">Log in</button>
                    <Link to="/registration" className={classes.forget}>Forget password?</Link>
                </form>
              </div>
          </div>
      )
    }
  }

  export default Login;