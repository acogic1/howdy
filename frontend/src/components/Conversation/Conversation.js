import React, {Component} from 'react';
import classes from '../Conversation/Conversation.module.css';
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import OneMessage from './OneMessage';


class Conversation extends Component {
    render() {
      return (
          <div className={classes.container}>
            <Header></Header>
           <div className={classes.title}>User</div>
           <div className={classes.listMsg}>
               <OneMessage username={"adem cogic"} msg={"Dje si sta ima"}></OneMessage>
               <OneMessage username={"dsabic1"} msg={"evo nista"}></OneMessage>
               <OneMessage username={"adem cogic"} msg={"super hehe"}></OneMessage>

           </div>
            <Footer></Footer>
          </div>
      )
    }
  }

  export default Conversation;