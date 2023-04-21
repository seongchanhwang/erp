import React from "react";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";

const Home = () => {
  const { auth } = useSelector((state) => state.auth);
  return (
    <div>
      메인
      <p>
        {auth === true ? (
          <Link to={"/"}>로그아웃</Link>
        ) : (
          <>
            <p>
              <Link to={"/member/login"}>로그인</Link>
            </p>
            <p>
              <Link to={"/member/join"}>회원가입</Link>
            </p>
          </>
        )}
      </p>
      <p></p>
      <p>
        <Link to={"/board"}>게시판</Link>
      </p>
    </div>
  );
};

export default Home;
