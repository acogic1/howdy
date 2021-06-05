import React, {Component} from 'react';
import classes from '../Profile/Profile.module.css';
import profile_image from '../../images/profile_image.jpg'
import { Link } from 'react-router-dom';
import Header from '../Header/Header';
import Post from '../Post/Post';
import Footer from '../Footer/Footer';
import axios from 'axios';


class otherProfile extends Component {


    constructor(props) {
        super(props)
        this.state = {
            username:  "",
            id:"",
            personalInfo: [],
            posts: []
        }
    }
    
      componentDidMount() {
        const { match: { params } } = this.props;
        console.log("parametri");

        console.log(this.props);
        this.setState({username: this.props.match.params.username});

        axios.get(`http://localhost:8090/newsfeed-service/api/user/`+this.props.match.params.username, {
            headers: {
                Authorization: "Bearer " + localStorage.token
            }
        })
          .then(res => {
            const id = res.data;
            this.setState({ id: id });
            console.log(this.state.id);
            axios.get(`http://localhost:8090/newsfeed-service/api/posts/`+this.state.id, {
            headers: {
                Authorization: "Bearer " + localStorage.token
            }
        })
          .then(res => {
            const posts = res.data;
            this.setState({ posts: posts });
            console.log(posts);
          }).catch(err => (console.log(err)))
          }).catch(err => (console.log(err)))
        
      }

    render() {
      return (
          <div>
            
            <Header></Header>
            <div className={classes.container}>
                <div className={classes.info} >
                    <div>
                        <img className={classes.profile_img} src={profile_image}/>
                    </div>
                    <div className={classes.info_right} >
                        <div className={classes.info_right_top}>
                            <div className={classes.info_right_name}>{this.state.username || "unknown username"}</div>
                            <div className={classes.info_right_edit}>
                                <button className={classes.info_btn}>Follow</button>
                            </div>
                        </div>
                        <div className={classes.info_right_middle}>
                            <Link className={classes.LinkF} to="/followers">
                                <div>
                                    <div>Followers</div>
                                    <div>{this.props.followers || "843"}</div>
                                </div>
                            </Link>
                            <Link className={classes.LinkF} to="/following">
                                <div>   
                                    <div>Following</div>
                                    <div>{this.props.following || "522"}</div>
                                </div>
                            </Link>
                        </div>
                        <div className={classes.info_right_bottom}>
                            {this.props.description || "ETF Sarajevo. Ovo je neki opis profila."}
                        </div>
                    </div>
                </div>  
                <div className={classes.posts}>
                    {
                        this.state.posts.map(post => (
                            <Post
                                key={post.id}
                                username={post.userId.username}
                                content={post.content} >
                            </Post>
                        ))
                    }

                </div>
            </div>
            <Footer></Footer>
          </div>
      )
    }
  }

  export default otherProfile;