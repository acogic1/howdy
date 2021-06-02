import React, {Component} from 'react';
import classes from '../MessageItem/MessageItem.module.css';
import { Link } from 'react-router-dom';
import profile_image from '../../images/user-image.png'


class MessageItem extends Component {
    render() {
      return (
          <div className={classes.container}>
           <div className={classes.top}>
                <div className={classes.profile_img}>
                <img src={profile_image} /></div>
                <div className={classes.top_right}>
                    <Link to={`/conversation/${this.props.username}`}>
                      <div className={classes.username}>{this.props.username || "dzejlansabic"}</div>
                    </Link>
                    {/* <div className={classes.date}>{this.props.date || "20.05.2021"}</div> */}
                </div>
              </div>
          </div>
      )
    }
  }

  export default MessageItem;