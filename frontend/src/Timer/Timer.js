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
    Pause: "start",
    Resume: "start",
    Start: "start",
    Stop: "stop"
  };
  let btnType = map[props.label];
  let className = ["btn", "btn-circle", `btn-${btnType}`].join(" ");
  return (
    <button
      onClick={props.handleClick}
      className={className}
      data-testid={props.label}
    >
    {btnType === 'start' ? 
    <i className="material-icons icon-l">play_arrow</i> :
    <i className="material-icons icon-l">stop</i>
}
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

  toggleStartTimer = () => {
    this.setState({
      started: true,
      paused: !this.state.paused
    });
  };

  stopTimer = time => {
    this.setState({
      started: false,
      paused: true
    });
  };

  onSave = () => {
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

  onTimerStop = time => {
    this.savedTime = time.m * 60 + time.s;
  };

  onChange = e => {
    this.setState({ selectedUser: e.target.value });
  };

  render() {
    const { started, paused } = this.state;

    return (
      <>
        <div className="d-flex align-items-center justify-content-center">
          <Button handleClick={this.stopTimer} label={"Stop"} />
          <div className="timer">
            <TimerMachine
              timeStart={3000000}
              timeEnd={0}
              started={started}
              paused={paused}
              countdown={false}
              interval={1000}
              formatTimer={(time, ms) =>
                moment.duration(ms, "milliseconds").format("h:mm:ss")
              }
              onStop={this.onTimerStop}
            />
          </div>
          <Button
            handleClick={this.toggleStartTimer}
            label={!started ? "Start" : paused ? "Resume" : "Pause"}
          />
        </div>

        <div className="mt-5">
          <UserSelection
            handleChange={event => this.onChange(event)}
            selectedUser={this.state.selectedUser}
          />
          <button
            type="button"
            onClick={this.onSave}
            disabled={!this.state.selectedUser}
            className="btn btn-success btn-block mt-4"
          >
            Save
          </button>
        </div>
      </>
    );
  }
}
