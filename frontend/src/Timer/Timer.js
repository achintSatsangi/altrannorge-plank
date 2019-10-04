import React, { Component } from "react";
import { navigate } from "@reach/router";

import TimerMachine from "react-timer-machine";
import "./Timer.css";
import UserSelection from "./UserSelection";
import Axios from "axios";

import moment from "moment";
import momentDurationFormatSetup from "moment-duration-format";

momentDurationFormatSetup(moment);

const Button = props => {
  let map = {
    Pause: "success",
    Resume: "success",
    Start: "success",
    Stop: "outline-light"
  };
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
      started: false,
      selectedUser: ""
    };
  }

  render() {
    const { started, paused } = this.state;
    let savedTime = 0;

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

    const onSave = () => {
      Axios.post("plank/postData", {
        user: this.state.selectedUser,
        date: moment().format("YYYY-MM-DD"),
        plankTimeInSeconds: this.savedTime || 0
      })
        .then(function(response) {
          navigate("graph");
        })
        .catch(function(error) {
          console.log(error);
        });
    };

    const onTimerStop = time => {
      this.savedTime = time.m * 60 + time.s;
    };

    const onChange = e => {
      this.setState({ selectedUser: e.target.value });
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
            onStop={time => onTimerStop(time)}
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
          <UserSelection
            handleChange={event => onChange(event)}
            selectedUser={this.state.selectedUser}
          />
          <button
            type="button"
            onClick={onSave}
            disabled={!this.state.selectedUser}
            className="btn btn-success btn-block mt-3"
          >
            Save
          </button>
        </div>
      </section>
    );
  }
}
