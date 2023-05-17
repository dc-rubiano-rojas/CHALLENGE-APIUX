import { expect } from "vitest";
import { render, screen } from "@testing-library/react";
import store from './redux/store'
import { Provider } from "react-redux";

import App from "./App";

describe("App", () => {
  test("should work as expected", () => {
    render(
      <Provider store={store}>
        <App/>
      </Provider>
    );
  });
});
