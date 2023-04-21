import React from "react";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";

const Home = () => {
  const { auth } = useSelector((state) => state.auth);
  return (
    <div>
      메인
      {!auth && (
        <p>
          <Link to={"/login"}>로그인</Link>
        </p>
      )}
      <p>
        <Link to={"/board"}>게시판</Link>
      </p>
    </div>
  );
};

export default Home;
