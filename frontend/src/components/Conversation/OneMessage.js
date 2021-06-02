import React, {Component} from 'react';
import classes from '../Conversation/OneMessage.module.css';
import Header from '../Header/Header';
import Footer from '../Footer/Footer';


class OneMessage extends Component {

    constructor() {
        super()
        this.state = {
            
        }
    }
    componentDidMount() {

    }

    render() {
      return (
          <div className={classes.container}>
                <div className={classes.myMsg}>
                    <span className={classes.username}>{this.props.username + ": "}</span>
                    <span>
                        {this.props.content}
                    </span>
                </div> 
          </div>
      )
    }
  }

  export default OneMessage;