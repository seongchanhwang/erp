import { Navigate, useLocation, useNavigate } from "react-router-dom";
import Board from "../pages/Board";
import { useSelector } from "react-redux";
import { useEffect } from "react";

const PrivateRoute = () => {
  const { auth } = useSelector((state) => state.auth);
  const navigate = useNavigate();
  const pathname = useLocation();
  useEffect(() => {
    if (!auth) {
      navigate("/login", { state: pathname });
    }
  }, []);

  return auth === true && <Board />;
};

export default PrivateRoute;
