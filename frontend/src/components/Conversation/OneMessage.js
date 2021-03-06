import React, {Component} from 'react';
import classes from '../Conversation/OneMessage.module.css';


class OneMessage extends Component {

    constructor() {
        super()
        this.state = {
            
        }
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