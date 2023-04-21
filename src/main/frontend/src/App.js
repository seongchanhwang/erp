import { Route, Routes } from "react-router-dom";

import Login from "./pages/Login";
import Home from "./pages/Home";
import Board from "./pages/Board";
import PrivateRoute from "./router/PrivateRoute";

function App() {
  return (
    <div id="wrap">
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/board" element={<PrivateRoute />}></Route>
      </Routes>
    </div>
  );
}

export default App;
