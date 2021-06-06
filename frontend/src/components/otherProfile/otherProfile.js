import React, {Component} from 'react';
import classes from '../Profile/Profile.module.css';
import profile_image from '../../images/user-image.png'
import Header from '../Header/Header';
import Post from '../Post/Post';
import Footer from '../Footer/Footer';
import axios from 'axios';


class otherProfile extends Component {

    constructor(props) {
        super(props)
        this.state = {
            username:  "",
            id:"",
            personalInfo: [],
            posts: [],
            followers:[],
            following:[],
            description:"",
            follbutt:false,
            f1:Object,
            f2:Object
        }
    }
    
      componentDidMount() {
        const { match: { params } } = this.props;

        this.setState({username: this.props.match.params.username});

        

        axios.get(`http://localhost:8090/newsfeed-service/api/user/`+this.props.match.params.username, {
            headers: {
                Authorization: "Bearer " + localStorage.token
            }
        })
          .then(res => {
            const id = res.data;
            this.setState({ id: id });
            axios.get(`http://localhost:8090/newsfeed-service/api/posts/`+this.state.id, {
            headers: {
                Authorization: "Bearer " + localStorage.token
            }
        })
          .then(res => {
            const posts = res.data;
            this.setState({ posts: posts });
          }).catch(err => (console.log(err)))


          var url = "http://localhost:8090/user-service/users/"+this.state.id
          axios.get(url, {
            headers: {
                Authorization: "Bearer " + localStorage.token
            }
        }).then((res)=>{
            this.state.f2=res.data
            this.state.description=res.data.description
            this.setState({})
        })

          var url = "http://localhost:8090/messages-followers-following-service/subscriptions/followers/"+this.state.id;
              axios.get(url, {
                headers: {
                    Authorization: "Bearer " + localStorage.token
                }
            }).then((res)=>{
              const followers = res.data;
                this.setState({ followers: followers });
                
            })
            var url = "http://localhost:8090/messages-followers-following-service/subscriptions/following/"+this.state.id;
            axios.get(url, {
              headers: {
                  Authorization: "Bearer " + localStorage.token
              }
          }).then((res)=>{
            const following = res.data;
              this.setState({ following: following });
          })

          }).catch(err => (console.log(err)))

          var url = "http://localhost:8090/user-service/users/"+localStorage.id
                axios.get(url, {
                  headers: {
                      Authorization: "Bearer " + localStorage.token
                  }
              }).then((res)=>{
                this.state.f1=res.data
                //this.state.description=res.data.description
                this.state.follbutt=true

                this.state.followers.map(f =>{
                    if(f.id==localStorage.id){
                        this.state.follbutt=false
                    }
                })

                this.setState({})
              })

             
        
      }

      displayContent(e){
        e.preventDefault();
        //console.log(this.props)

        
    
            const options = {
                headers: {
                  Authorization: "Bearer " + localStorage.token
              }
              };
            var url = "http://localhost:8090/messages-followers-following-service/subscriptions/"
          axios.post(url, {
            id_follower:this.state.f1,
            id_following:this.state.f2
          },options).then((res)=>{
            var url = "http://localhost:8090/messages-followers-following-service/messages";
            const options = {
              headers: {
                Authorization: "Bearer " + localStorage.token
            }
            };
    axios.post(url,
      {
        id_sender:this.state.f1,
        id_reciever:this.state.f2,
        content:"I'm following you"
      },options).then((res)=>{
        window.alert("Successfull follow "+this.props.match.params.username)
            window.location.reload();
      })
            
          })
        
        
      }


    render() {
      return (
          <div>
            
            <Header></Header>
            <div className={classes.container}>
                <div className={classes.info} >
                    <div>
                        <img className={classes.profile_img} alt="logo" src={profile_image}/>
                    </div>
                    <div className={classes.info_right} >
                        <div className={classes.info_right_top}>
                            <div className={classes.info_right_name}>{this.state.username || "unknown username"}</div>
                            { this.state.follbutt ? 
                             <div className={classes.info_right_edit}>
                             <button type="button" className={classes.info_btn} onClick={(e)=>this.displayContent(e)}>Follow</button>
                            </div> :null
                            }
                        </div>
                        <div className={classes.info_right_middle}>
                                <div>
                                    <div>Followers</div>
                                    <div>{this.state.followers.length }</div>
                                </div>
                            
                                <div>   
                                    <div>Following</div>
                                    <div>{this.state.following.length}</div>
                                </div>
                           
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

  export default otherProfile;