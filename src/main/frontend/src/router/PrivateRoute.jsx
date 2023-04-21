import { Navigate } from "react-router-dom";
import Board from "../pages/Board";
import { useSelector } from "react-redux";

const PrivateRoute = () => {
  const { auth } = useSelector((state) => state.auth);
  return auth === true ? <Board /> : <Navigate to="/member/login" />;
};

export default PrivateRoute;
