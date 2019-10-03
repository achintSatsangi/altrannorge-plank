import React, { Component } from "react";

import TimerMachine from "react-timer-machine";
import "./Timer.css";
import UserSelection from "./UserSelection";

import moment from "moment";
import momentDurationFormatSetup from "moment-duration-format";

momentDurationFormatSetup(moment);
const axios = require("axios");


let map = {
  Pause: "success",
  Resume: "success",
  Start: "success",
  Stop: "outline-light"
};

const Button = props => {
  let btnType = map[props.label];
  let className = ["btn", "btn-circle", `btn-${btnType}`].join(" ");
  return (
    <button
      onClick={props.handleClick}
      className={className}
      data-testid={props.label}
    >
      {props.label}
    </button>
  );
};


export default class Timer extends Component {
  constructor(props) {
    super(props);

    this.state = {
      paused: true,
      started: false
    };
  }

  render() {
    const { started, paused } = this.state;

    const toggleStartTimer = () => {
      this.setState({
        started: true,
        paused: !this.state.paused
      });
    };

    const stopTimer = () => {
      this.setState({
        started: false,
        paused: true
      });
    };

    return (
      <section className="timerMachine">
        <span className="timer">
          <TimerMachine
            timeStart={0}
            timeEnd={0}
            started={started}
            paused={paused}
            countdown={false}
            interval={1000}
            formatTimer={(time, ms) =>
              moment.duration(ms, "milliseconds").format("h:mm:ss")
            }
            onStop={time =>
              console.info(`Timer stopped: ${JSON.stringify(time)}`)
            }
          />
        </span>
        <div className="d-flex justify-content-between">
          <Button handleClick={stopTimer} label={"Stop"} />
          <Button
            handleClick={toggleStartTimer}
            label={!started ? "Start" : paused ? "Resume" : "Pause"}
          />
        </div>
        <div className="mt-5">
          <UserSelection />
          <button type="button" className="btn btn-success btn-block mt-3">Save</button>
        </div>
      </section>
    );
  }
}
