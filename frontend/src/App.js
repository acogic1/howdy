import './App.css';
import React from 'react';
import {
  BrowserRouter,
  Switch,
  Route 
} from 'react-router-dom';
import Login from './components/Login/Login';
import Registration from './components/Registration/Registration';
import Profile from './components/Profile/Profile';
import NewsFeed from './components/NewsFeed/NewsFeed';
import Messages from './components/Messages/Messages';
import Conversation from './components/Conversation/Conversation';
import Followers from './components/Follow/Followers';
import Following from './components/Follow/Following';
import otherProfile from './components/otherProfile/otherProfile';
import EditProfile from './components/EditProfile/EditProfile';

function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/" exact component={Login} />
        <Route path="/login" exact component={Login} />
        <Route path="/registration" component={Registration} />        
        <Route path="/profile" component={Profile} />
        <Route path="/newsfeed" component={NewsFeed} />
        <Route path="/messages" component={Messages} />
        <Route path="/conversation/:username" component={Conversation} />
        <Route path="/followers" component={Followers} />
        <Route path="/following" component={Following} />
        <Route path="/edit" component={EditProfile} />
        <Route path="/otherProfile/:username" component={otherProfile} />

      </Switch>
    </BrowserRouter>
    
  );
}

export default App;
