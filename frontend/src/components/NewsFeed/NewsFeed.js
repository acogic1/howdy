import React, {Component} from 'react';
import classes from '../NewsFeed/NewsFeed.module.css';
import profile_image from '../../images/profile_image.jpg'
import Header from '../Header/Header';
import Post from '../Post/Post';
import Footer from '../Footer/Footer';


class NewsFeed extends Component {
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
                <div className={classes.posts}>
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

  export default NewsFeed;