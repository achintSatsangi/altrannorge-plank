import React, { Component } from 'react';
import { Router } from "@reach/router";
import './App.css';
import Timer from './Timer/Timer.js';
import Graph from './Graph/Graph.js';
import NavigationMenu from './NavigationMenu/NavigationMenu';

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
        <NavigationMenu />
      </div>
    );
  }
}

export default App;
