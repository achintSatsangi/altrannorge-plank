import React, { Component } from 'react'

import TimerMachine from 'react-timer-machine'

import moment from "moment";
import momentDurationFormatSetup from "moment-duration-format";

momentDurationFormatSetup(moment);


let map = {
    Start: 'success',
    End: 'success',
    Pause: 'outline-light',
    Resume: 'outline-light'
}

const Button = (props) => {
    let btnType = map[props.label];
    let className = ['btn', `btn-${btnType}`, 'mr-1'].join(' ');
    return <button onClick={props.handleClick} className={className} data-testid={props.label}>{props.label}</button>;
}

export default class Timer extends Component {

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
            <>
                <div className="d-flex justify-content-center">
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
                </div>
                <div className="d-flex justify-content-center">
                    <Button handleClick={toggleStartTimer} label={started ? 'End' : 'Start'} />
                    <Button handleClick={togglePauseTimer} label={paused ? 'Resume' : 'Pause'} />
                </div>
            </>
        )
    }
}