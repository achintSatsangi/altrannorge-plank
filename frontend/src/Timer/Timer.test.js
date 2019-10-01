import React from 'react';
import {render, unmountComponentAtNode} from 'react-dom';
import Button from './Timer';
import { toggleStartTimer, togglePauseTimer } from './Timer';
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

  expect(button.innerHTML).toBe("End");
});

it("renders pause/resume button with correct label", () => {
  act(() => {
    render(<Button handleClick={togglePauseTimer} label="Pause"/>, container);
  });
  const button = document.querySelector("[data-testid=Pause]");
  expect(button.innerHTML).toBe("Pause");

  act(() => {
    button.dispatchEvent(new MouseEvent("click", { bubbles: true }));
  });

  expect(button.innerHTML).toBe("Resume");
});