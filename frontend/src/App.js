import React, { Component } from 'react';
import { Router, Link } from "@reach/router";
import './App.css';
import Timer from './Timer/Timer.js';
import Graph from './Graph/Graph.js';

class App extends Component {
  render() {
    return (
      <div className="App">
        <div className="container">
          <Router>
            <Timer path="/" />
            <Graph path="graph" />
          </Router>
        </div>
        <nav className="navbar fixed-bottom navbar-dark bg-dark">
          <Link to="/" className="navbar-brand flex-fill">
            <i className="material-icons icon-l">timer</i>
          </Link>
          <Link to="graph" className="navbar-brand flex-fill">
            <i className="material-icons icon-l">emoji_events</i>
          </Link>
        </nav>
      </div>
    );
  }
}

export default App;
