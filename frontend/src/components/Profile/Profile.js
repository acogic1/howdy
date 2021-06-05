import React, {Component} from 'react';
import classes from '../Profile/Profile.module.css';
import profile_image from '../../images/user-image.png'
import { Link } from 'react-router-dom';
import Header from '../Header/Header';
import Post from '../Post/Post';
import Footer from '../Footer/Footer';
import axios from 'axios';


class Profile extends Component {


    constructor() {
        super()
        this.state = {
            personalInfo: [],
            posts: [],
            followers:[],
            following:[],
            user:Object,
            img:null,
            description:""
        }
    }
    
      componentDidMount() {
        axios.get(`http://localhost:8090/newsfeed-service/api/userId/`+localStorage.id, {
          headers: {
              Authorization: "Bearer " + localStorage.token
          }
      })
          .then(res => {
            const personalInfo = res.data;
            this.setState({ personalInfo: personalInfo });
            console.log(personalInfo);
          }).catch(err => (console.log(err)))

          axios.get(`http://localhost:8090/newsfeed-service/api/posts/`+localStorage.id, {
            headers: {
                Authorization: "Bearer " + localStorage.token
            }
        })
          .then(res => {
            const posts = res.data;
            this.setState({ posts: posts });
            console.log(posts);
          }).catch(err => (console.log(err)))
        

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
                var url = "http://localhost:8090/messages-followers-following-service/subscriptions/followers/"+res.data;
              axios.get(url, {
                headers: {
                    Authorization: "Bearer " + localStorage.token
                }
            }).then((res)=>{
              const followers = res.data;
                this.setState({ followers: followers });
                
            })
              })
          })


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
                var url = "http://localhost:8090/messages-followers-following-service/subscriptions/following/"+res.data;
              axios.get(url, {
                headers: {
                    Authorization: "Bearer " + localStorage.token
                }
            }).then((res)=>{
              const following = res.data;
                this.setState({ following: following });
            })
              })
          })


          var url = "http://localhost:8090/user-service/users/"+localStorage.id
                //window.alert(url)
                axios.get(url, {
                  headers: {
                      Authorization: "Bearer " + localStorage.token
                  }
              }).then((res)=>{
                this.state.user=res.data
                this.state.description=res.data.description
                //var blob=res.data.picture.blob()
                //console.log(blob)
                this.setState({img:res.data.picture})
                this.setState({})
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
                            <div className={classes.info_right_name}>{this.props.username || localStorage.username || "unknown username"}</div>
                            <div className={classes.info_right_edit}>
                                <Link to="/edit">
                                <button  className={classes.info_btn}>Edit profile</button>
                                </Link>
                            </div>
                        </div>
                        <div className={classes.info_right_middle}>
                            <Link className={classes.LinkF} to="/followers">
                                <div>
                                    <div>Followers</div>
                                    <div>{this.state.followers.length }</div>
                                </div>
                            </Link>
                            <Link className={classes.LinkF} to="/following">
                                <div>   
                                    <div>Following</div>
                                    <div>{this.state.following.length }</div>
                                </div>
                            </Link>
                        </div>
                        <div className={classes.info_right_bottom}>
                            {this.state.description }
                        </div>
                    </div>
                </div>  
                <div className={classes.posts}>
                    {
                        this.state.posts.map(post => (
                            <Post
                                key={post.id}
                                username={post.userId.username}
                                content={post.content} >
                            </Post>
                        ))
                    }

                </div>
            </div>
            <Footer></Footer>
          </div>
      )
    }
  }

  export default Profile;