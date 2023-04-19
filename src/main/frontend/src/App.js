import { Route, Routes } from "react-router-dom";

import Login from "./pages/Login";
import Home from "./pages/Home";

function App() {
  return (
    <div id="wrap">
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/login" element={<Login />}></Route>
      </Routes>
    </div>
  );
}

export default App;
