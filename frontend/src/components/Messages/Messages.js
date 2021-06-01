import React, {Component} from 'react';
import classes from '../Messages/Messages.module.css';
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import MessageItem from '../MessageItem/MessageItem';


class Messages extends Component {
    render() {
      return (
          <div className={classes.container}>
            <Header></Header>
           <div className={classes.title}>Messages</div>
           <div className={classes.listMsg}>
               <MessageItem></MessageItem>
               <MessageItem></MessageItem>
           </div>
            <Footer></Footer>
          </div>
      )
    }
  }

  export default Messages;