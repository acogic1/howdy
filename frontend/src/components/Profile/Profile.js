import React, {Component} from 'react';
import classes from '../Profile/Profile.module.css';
import profile_image from '../../images/profile_image.jpg'
import { Link } from 'react-router-dom';
import Header from '../Header/Header';
import Post from '../Post/Post';
import Footer from '../Footer/Footer';


class Profile extends Component {

    state = {
        personalInfo: [],

      }
    
      componentDidMount() {
        axios.get(`localhost:8090/mewsfeed-service/api/user`, {
            headers: { Authorization: "Bearer " + localStorage.token}
        })
          .then(res => {
            const personalInfo = res.data;
            this.setState({ personalInfo });
          })
      }

    render() {
      return (
          <div>
            
            <Header></Header>
            <div className={classes.container}>
                <div className={classes.info} >
                    <div>
                        <img className={classes.profile_img} src={profile_image}/>
                    </div>
                    <div className={classes.info_right} >
                        <div className={classes.info_right_top}>
                            <div className={classes.info_right_name}>{this.props.username || "dzejlansabic"}</div>
                            <div className={classes.info_right_edit}>
                                <button className={classes.info_btn}>Edit profile</button>
                            </div>
                        </div>
                        <div className={classes.info_right_middle}>
                            <div>
                                <div>Followers</div>
                                <div>{this.props.followers || "843"}</div>
                            </div>
                            <div>   
                                <div>Following</div>
                                <div>{this.props.following || "522"}</div>
                            </div>
                        </div>
                        <div className={classes.info_right_bottom}>
                            {this.props.description || "ETF Sarajevo. Ovo je neki opis profila."}
                        </div>
                    </div>
                </div>  
                <div className={classes.posts}>
                    <Post></Post>
                    <Post></Post>
                    <Post></Post>
                    <Post></Post>
                    <Post></Post>
                    <Post></Post>
                    <Post></Post>

                </div>
            </div>
            <Footer></Footer>
          </div>
      )
    }
  }

  export default Profile;