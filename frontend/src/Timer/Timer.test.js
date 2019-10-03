import React from 'react';
import {render, unmountComponentAtNode} from 'react-dom';
import Button from './Timer';
import { toggleStartTimer, stopTimer } from './Timer';
import { act } from "react-dom/test-utils";


let container = null;
beforeEach(() => {
  container = document.createElement("div");
  document.body.appendChild(container);
});

afterEach(() => {
  unmountComponentAtNode(container);
  container.remove();
  container = null;
});


it("renders start/end button with correct label", () => {
  act(() => {
    render(<Button handleClick={toggleStartTimer} label="Start"/>, container);
  });
  const button = document.querySelector("[data-testid=Start]");
  expect(button.innerHTML).toBe("Start");

  act(() => {
    button.dispatchEvent(new MouseEvent("click", { bubbles: true }));
  });

  expect(button.innerHTML).toBe("Pause");

  act(() => {
    button.dispatchEvent(new MouseEvent("click", { bubbles: true }));
  });

  expect(button.innerHTML).toBe("Resume");
});

it("renders stop button with correct label", () => {
  act(() => {
    render(<Button handleClick={stopTimer} label="Stop"/>, container);
  });
  const button = document.querySelector("[data-testid=Stop]");
  expect(button.innerHTML).toBe("Stop");
});