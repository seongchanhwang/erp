import { Route, Routes, useLocation } from "react-router-dom";

import Login from "./pages/Member";
import Home from "./pages/Home";
import Board from "./pages/Board";
import PrivateRoute from "./router/PrivateRoute";
import Member from "./pages/Member";
import { CSSTransition, TransitionGroup } from "react-transition-group";
import LoginForm from "./components/member/LoginForm";
import JoinForm from "./components/member/JoinForm";

function App() {
  const location = useLocation();
  return (
    <div id="wrap">
      <TransitionGroup className="transitions-wrapper">
        <CSSTransition key={location.key} classNames={"right"} timeout={300}>
          <Routes location={location}>
            <Route path="/" element={<Home />}></Route>
            <Route path="/member/join" element={<Member />}></Route>
            <Route path="/login" element={<Member />}></Route>
            <Route path="/board" element={<PrivateRoute />}></Route>
          </Routes>
        </CSSTransition>
      </TransitionGroup>
    </div>
  );
}

export default App;
