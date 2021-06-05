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
      validToken: false,
      uid:0,
      email: "",
      username: "",
      password: "",
      description:"",
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
  var url = "http://localhost:8090/user-service/users/"+this.state.uid
            //window.alert(url)
            const options = {
              headers: {
                Authorization: "Bearer " + localStorage.token
            }
            };
            axios.put(url, {
              email:this.state.email,
              username:this.state.username,
              password:this.state.password,
              description:this.state.description
          },options).then((res)=>{
            window.alert("Successifull edit your profile")
            localStorage.clear();
            window.location.replace("/login")
          })
          event.preventDefault()
}


  componentWillMount() {
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
            var url = "http://localhost:8090/user-service/users/"+res.data
            //window.alert(url)
            this.state.uid=res.data
            axios.get(url, {
              headers: {
                  Authorization: "Bearer " + localStorage.token
              }
          }).then((res)=>{
            this.state.username=res.data.username
            this.state.password=res.data.password
            this.state.email=res.data.email
            this.state.description=res.data.description
            this.setState({});
          })
            
          })
        })
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
              <div className={classes.title}>Edit profile</div>
              <form className={classes.forma} onSubmit={this.handleSubmit}>
              <label><input placeholder={localStorage.username} name="username" onChange={this.handleChange}></input>Username: </label>
              <label><input name="password" onChange={this.handleChange} type="password"></input>Password: </label>
              <label><input placeholder={this.state.email} name="email" onChange={this.handleChange}></input>Email: </label>
              <label><input placeholder={this.state.description} name="description" onChange={this.handleChange}></input>Description: </label>
              <button  className={classes.btnSave} type="submit">Save changes</button>
              </form>
          </div>
      )
    }
  }

  export default EditProfile;