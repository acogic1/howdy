import React, {Component} from 'react';
import classes from '../Post/Post.module.css';
import profile_image from '../../images/profile_image.jpg'



class Post extends Component {
    render() {
      return (
          <div className={classes.container}>
              <div className={classes.top}>
                <div className={classes.profile_img}><img src={profile_image} /></div>
                <div className={classes.top_right}>
                    <div className={classes.username}>{this.props.username || "dzejlansabic"}</div>
                    <div className={classes.date}>{this.props.date || "20.05.2021"}</div>
                </div>
              </div>
              <div className={classes.text}>
                {this.props.content || "Ovo je neki status za testiranje.Ovo je neki status za testiranje.Ovo je neki status za testiranje. "}
              </div>
              <div>
                <button>REACT</button>
              </div>
          </div>
      )
    }
  }

  export default Post;