import React, { Component } from "react";
import { Router } from "@reach/router";
import "./App.css";
import Timer from "./Timer/Timer.js";
import Graph from "./Graph/Graph.jsx";
import NavigationMenu from "./NavigationMenu/NavigationMenu";

class App extends Component {
  render() {
    return (
      <div className="App">
        <Router>
          <Timer path="/" />
          <Graph path="graph" />
        </Router>
        <NavigationMenu />
      </div>
    );
  }
}

export default App;
