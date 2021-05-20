import React, {Component} from 'react';
import classes from '../Footer/Footer.module.css';



class Footer extends Component {
    render() {
      return (
          <div className={classes.container}>
            © 2021 Howdy (Džejlan, Adem, Vedad)
          </div>
      )
    }
  }

  export default Footer;