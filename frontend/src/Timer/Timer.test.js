import React from "react";
import { render, unmountComponentAtNode } from "react-dom";
import Button from "./Timer";
import { toggleStartTimer, stopTimer } from "./Timer";
import { act } from "react-dom/test-utils";
import MockAxios from "axios";

let container = null;
beforeEach(() => {
  container = document.createElement("div");
  document.body.appendChild(container);
});

afterEach(() => {
  unmountComponentAtNode(container);
  container.remove();
  container = null;
  MockAxios.reset();
});

it("renders start/end button with correct icon", () => {
  act(() => {
    render(<Button handleClick={toggleStartTimer} label="Start" />, container);
  });
  const button = document.querySelector("[data-testid=Start]");
  const icon = button.querySelector(".material-icons");
  expect(icon.innerHTML).toBe("play_arrow");

  act(() => {
    button.dispatchEvent(new MouseEvent("click", { bubbles: true }));
  });

  expect(icon.innerHTML).toBe("pause");

  act(() => {
    button.dispatchEvent(new MouseEvent("click", { bubbles: true }));
  });

  expect(icon.innerHTML).toBe("play_arrow");
  expect(MockAxios.get).toHaveBeenCalledTimes(1);
});

it("renders stop button with correct icon", () => {
  act(() => {
    render(<Button handleClick={stopTimer} label="Stop" />, container);
  });
  const button = document.querySelector("[data-testid=Stop]");
  const icon = button.querySelector(".material-icons");
  expect(icon.innerHTML).toBe("stop");
  expect(MockAxios.get).toHaveBeenCalledTimes(1);
});
