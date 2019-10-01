import React, { Component } from 'react';
import './App.css';
import Timer from './Timer/Timer.js';



class App extends Component {

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <Timer />
        </header>
      </div>
    );
  }

}

export default App;
