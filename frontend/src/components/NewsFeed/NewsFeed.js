import React, {Component} from 'react';
import classes from '../NewsFeed/NewsFeed.module.css';
import profile_image from '../../images/profile_image.jpg'
import Header from '../Header/Header';
import Post from '../Post/Post';
import Footer from '../Footer/Footer';
import axios from 'axios';


class NewsFeed extends Component {

    constructor() {
        super()
        this.state = {
            followingId: [],
            posts: []
        }
    }
    
      componentDidMount() {

        var url = "http://localhost:8090/user-service/validate-token"
  axios.post(url,{
    token:localStorage.token,
    username:localStorage.username
  }).then((res)=>{
        axios.get(`http://localhost:8090/newsfeed-service/api/postFollowing/`+localStorage.id, {
          headers: {
              Authorization: "Bearer " + localStorage.token
          }})
          .then(res => {
            const followingId = res.data;
            const id=[];
            followingId.map(fw => ( id.push(fw.id)))
            .then()(res => {console.log("RADDDDIIII")
          }).catch(err=> (console.log("e ne radi")))

            this.setState({ followingId: id });
            console.log(this.state.followingId)
        
        }).catch(err=> (console.log("e ne radi")))

            this.state.followingId.map(m => (
                axios.get(`http://localhost:8090/newsfeed-service/api/posts/`+m)
                .then(res2 => {
                    console.log(res2.data);
                  this.setState({ posts: [...this.state.posts,...res2.data] });

                  console.log(this.state.posts)
                }).catch(err => (console.log("Error in api/posts")))
            ))

          }).catch(err => (console.log(err)))
                  
      }

    render() {
      return (
          <div>
            <Header></Header>
            <div className={classes.container}>
                <div className={classes.newPost}>
                    <div className={classes.top}>
                        <div><img className={classes.img_post} src={profile_image} /></div>
                        <div><input className={classes.post_input} placeholder="What's on your mind?"></input></div>
                    </div>
                    <div>
                        <button className={classes.btn_post}>Post</button>
                    </div>
                </div>
                <div className={classes.posts1}>
                    {
                        this.state.posts.map(post => (
                            <Post key={post.id} username={post.userId.username} content={post.content}></Post>
                        ))
                    }
                </div>
            </div>
            <Footer></Footer>
          </div>
      )
    }
  }

  export default NewsFeed;