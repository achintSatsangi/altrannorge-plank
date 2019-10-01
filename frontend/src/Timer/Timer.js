import React, { Component } from 'react'

import TimerMachine from 'react-timer-machine'

import moment from "moment";
import momentDurationFormatSetup from "moment-duration-format";

momentDurationFormatSetup(moment);

export default class Timer extends Component {

    render() {
        return (
            <>
                <TimerMachine
                    timeStart={0} 
                    timeEnd={0} 
                    started={this.props.started}
                    paused={this.props.paused}
                    countdown={this.props.countdown} 
                    interval={1000} 
                    formatTimer={(time, ms) =>
                        moment.duration(ms, "milliseconds").format("h:mm:ss")
                    }
                    onStop={time =>
                        console.info(`Timer stopped: ${JSON.stringify(time)}`)
                    }
                />
            </>
        )
    }
}