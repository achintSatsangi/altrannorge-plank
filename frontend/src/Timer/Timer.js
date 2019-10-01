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
                    timeStart={60 * 1000} // start at 10 seconds
                    timeEnd={0} // end at 20 seconds
                    started={this.props.started}
                    paused={this.props.paused}
                    countdown={this.props.countdown} // use as stopwatch
                    interval={1000} // tick every 1 second
                    formatTimer={(time, ms) =>
                        moment.duration(ms, "milliseconds").format("h:mm:ss")
                    }
                    onStart={time =>
                        console.info(`Timer started: ${JSON.stringify(time)}`)
                    }
                    onStop={time =>
                        console.info(`Timer stopped: ${JSON.stringify(time)}`)
                    }
                    onTick={time =>
                        console.info(`Timer ticked: ${JSON.stringify(time)}`)
                    }
                    onPause={time =>
                        console.info(`Timer paused: ${JSON.stringify(time)}`)
                    }
                    onResume={time =>
                        console.info(`Timer resumed: ${JSON.stringify(time)}`)
                    }
                    onComplete={time =>
                        console.info(`Timer completed: ${JSON.stringify(time)}`)
                    }
                />

            </>
        )
    }
}