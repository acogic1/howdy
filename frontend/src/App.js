import './App.css';
import React from 'react';
import {
  BrowserRouter,
  Switch,
  Route
} from 'react-router-dom';
import Login from './components/Login/Login';
import Registration from './components/Registration/Registration';

function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/" exact component={Login} />
        <Route path="/login" exact component={Login} />
        <Route path="/registration" component={Registration} />
      </Switch>
    </BrowserRouter>
    
  );
}

export default App;
