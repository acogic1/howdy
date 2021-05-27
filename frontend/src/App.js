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
      </Switch>
    </BrowserRouter>
    
  );
}

export default App;
