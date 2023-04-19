import React, { useEffect, useState } from "react";
import styles from "../css/login.scss";
import Background from "../components/login/Background";

const Login = () => {
  const [load, setLoad] = useState(false);
  useEffect(() => {
    setLoad(true);
  }, []);
  return (
    <>
      <div id="login-wrap" className="flex-box justify-cnt dir-col">
        <div className={`bg ${load ? "active" : ""}`}>
          <Background idx={1} />
          <Background idx={2} />
          <Background idx={3} />
        </div>
        <div className="txt-box">
          <h2>Eventually</h2>
          <p>
            A simple template for telling the world when you'll launch <br />
            your next big thing. Brought to you by HTML5 UP.
          </p>
        </div>
        <form>
          <div className="input-box">
            <div>
              <input type="text" placeholder="이메일을 입력해주세요"></input>
            </div>
            <div>
              <input
                type="password"
                placeholder="비밀번호를 입력해주세요"
              ></input>
            </div>
            <button className="btn-submit">Login</button>
          </div>
        </form>
      </div>
    </>
  );
};

export default Login;
