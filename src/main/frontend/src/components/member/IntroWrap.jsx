import React from "react";
import { Link } from "react-router-dom";

const IntroMsg = ({ page }) => {
  return (
    <>
      <article className="intro-wrap">
        <div>
          <div className="logo">
            <img src={`${process.env.PUBLIC_URL}/img/logo.png`} alt="" />
          </div>
          <div className="txt-box">
            {page === "login" && (
              <>
                <h2>Hello, Friend!</h2>
                <p>do you have our account?</p>
              </>
            )}
            {page === "join" && (
              <>
                <h2>Welcome back!!</h2>
                <p>Start your project</p>
              </>
            )}
          </div>
          <div className="link-btn">
            {page === "login" && <Link to={"/member/join"}>SIGN UP</Link>}
            {page === "join" && <Link to={"/login"}>로그인</Link>}
          </div>
        </div>
      </article>
    </>
  );
};

export default IntroMsg;
