import { BrowserRouter, Route, Routes, Navigate } from "react-router-dom";

import Home from "./components/Home";
import EditTask from "./components/EditTask";
import AddTask from "./components/EditTask";

function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />}></Route>
        </Routes>
      </BrowserRouter>
  );
}

export default App;
