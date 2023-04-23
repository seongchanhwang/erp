import React from "react";
import { Link, useParams } from "react-router-dom";

const IntroMsg = () => {
  const { page } = useParams();
  return (
    <>
      <article className="intro-wrap">
        <div>
          <div className="logo">
            <img src={`${process.env.PUBLIC_URL}/img/logo.png`} alt="" />
            {/* <span></span> */}
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
            {page === "join" && <Link to={"/member/login"}>SIGN IN</Link>}
          </div>
        </div>
      </article>
    </>
  );
};

export default IntroMsg;
