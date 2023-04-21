import { Route, Routes } from "react-router-dom";

import Login from "./pages/Member";
import Home from "./pages/Home";
import Board from "./pages/Board";
import PrivateRoute from "./router/PrivateRoute";
import Member from "./pages/Member";

function App() {
  return (
    <div id="wrap">
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/member/:page" element={<Member />}></Route>
        <Route path="/member/:page" element={<Member />}></Route>
        <Route path="/board" element={<PrivateRoute />}></Route>
      </Routes>
    </div>
  );
}

export default App;
