import React, {Component} from 'react';
import classes from '../Follow/Follow.module.css';
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import MessageItem from '../MessageItem/MessageItem';


class Followers extends Component {

    constructor() {
        super()
        this.state = {
            follow: ["foll1", "foll2", "foll333"]
        }
    }

    render() {
      return (
          <div className={classes.container}>
            <Header></Header>
           <div className={classes.title}>Followers</div>
           <div className={classes.listF}>

            {this.state.follow.map(f => (
                <div className={classes.username}>{f}</div>
            ))}

           </div>
            <Footer></Footer>
          </div>
      )
    }
  }

  export default Followers;