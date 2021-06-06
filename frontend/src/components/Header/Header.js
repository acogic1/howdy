import React, {Component} from 'react';
import classes from '../Header/Header.module.css';
import logo from '../../images/logo.png'
import notification_image from '../../images/notification_image.jpg';
import logout_image from '../../images/arrow.png';
import { Link } from 'react-router-dom';
import axios from 'axios';

class Header extends Component {
  constructor(props) {
    super(props)
    this.state = {
        notification: [],
        options:[],
        input: ""
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

search() {
  this.setState({input: document.getElementById("input").value});
  window.location.reload();
}

onChange() {
  this.setState({input: document.getElementById("input").value});
}

    render() {
      return (
          <div className={classes.container}>
              <div className={classes.main_div}>
                <img className={classes.logo_header} alt="howdy" src={logo}/>
                <div className={classes.searchDiv}>
                  <input  id="input" onChange={()=>this.onChange()} type="text" className={classes.search} list="tags" placeholder={"Search"}></input>
                  <button onClick={() => this.search()} className={classes.btnFind}><Link to={"/otherProfile/"+this.state.input} className={classes.find}>Find</Link></button> 
                </div>
                
                  <datalist id="tags">
                  {
                    this.state.options.map(option => (  
                      <option key={option.id} value={option.username}
                      />
                    ))
                  }
                  
                  </datalist>
                <div className={classes.meni}>
                    <Link to="/newsfeed">NewsFeed</Link>
                    <Link to="/profile">Profile</Link>
                    <Link to="/messages">Messages</Link>
                    <img className={classes.notif_img} alt="notification" src={notification_image} />
                    <Link to="/login" onClick={() => this.clickLogout()}>
                        <img className={classes.logout_img} alt="logout" src={logout_image}/>
                    </Link>
                </div>
              </div>
          </div>
      )
    }
  }

  export default Header;