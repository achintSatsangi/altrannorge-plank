import React, { Component } from 'react';
import './App.css';
import Timer from './Timer/Timer.js';


let map = {
  Start: 'success',
  End: 'success',
  Pause: 'outline-light',
  Resume: 'outline-light'
}

const Button = ( props ) => {
  let btnType = map[props.label];
  let className = ['btn', `btn-${btnType}`, 'mr-1'].join(' ');
  return < button onClick={props.handleClick} className={className}>{props.label}</button>;
}


class App extends Component {

  constructor(props) {
    super(props);

    this.state = {
      paused: false,
      started: false
    };
  }

  render() {
    const { started, paused } = this.state;

    const toggleStartTimer = () => {
      this.setState({
        started: !this.state.started
      });
    }

    const togglePauseTimer = () => {
      this.setState({
        paused: !this.state.paused
      });
    }

    return (
      <div className="App">
        <header className="App-header">
          <Timer started={started} paused={paused} />
          <div className="d-flex">
            <Button handleClick={toggleStartTimer} label={this.state.started ? 'End': 'Start'} />
            <Button handleClick={togglePauseTimer} label={this.state.paused ? 'Resume': 'Pause'} />
          </div>
        </header>
      </div>
    );
  }

}

export default App;
