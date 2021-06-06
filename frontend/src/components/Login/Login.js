import React from 'react';
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
    axios.post('http://localhost:8090/user-service/authenticate', {
        username: this.state.username,
        password: this.state.password
    })
        .then((response) => {
            this.setState({ errorMessage: "" })
            localStorage.token = response.data.token
            localStorage.username = JSON.parse(atob(localStorage.token.split('.')[1])).sub

            var url = "http://localhost:8090/user-service/validate-token"
            axios.post(url, {
                token: localStorage.token,
                username: localStorage.username
            })
                .then((response) => {

                    axios.get(`http://localhost:8090/newsfeed-service/api/user/`+this.state.username, {
                        headers: {
                            Authorization: "Bearer " + localStorage.token
                        }
                    })
                      .then(res => {
                        localStorage.id=res.data;
                        this.props.history.push('/profile')
                      }).catch(err => (console.log(err)))
                })

        }, (error) => {
            this.setState({ errorMessage: "Incorrect password or username" });
        });
    event.preventDefault()
}  
  
  render() {
      return (
          <div className={classes.container}>
            <div className={classes.left}>
                <img className={classes.logo} alt="logo" src={logo}></img>
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