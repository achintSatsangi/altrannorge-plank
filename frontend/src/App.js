import React, { Component } from "react";
import { Router } from "@reach/router";
import "./App.scss";
import Timer from "./Timer/Timer.js";
import Graph from "./Graph/Graph.jsx";
import NavigationMenu from "./NavigationMenu/NavigationMenu";

class App extends Component {
  render() {
    return (
      <main className="App">
        <section className="App-header">
          <a className="navbar-brand" href="/">
            Plank App (Test)
          </a>
        </section>
        <section className="App-body">
          <Router>
            <Timer path="/" />
            <Graph path="graph" />
          </Router>
        </section>
        <section className="App-footer">
          <NavigationMenu />
        </section>
      </main>
    );
  }
}

export default App;
