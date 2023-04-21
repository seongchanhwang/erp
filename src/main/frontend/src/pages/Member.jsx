import React, { useEffect, useState } from "react";
import styles from "../css/member.scss";
import Background from "../components/member/Background";
import LoginForm from "../components/member/LoginForm";
import { useSelector } from "react-redux";
import { JoinForm } from "../components/member/JoinForm";
import { useParams } from "react-router-dom";

const Member = () => {
  const [load, setLoad] = useState(false);
  const { page } = useParams();

  useEffect(() => {
    setLoad(true);
  }, []);

  return (
    <>
      <div id="login-wrap" className="flex-box justify-cnt dir-col">
        <section>
          <div className={`bg ${load ? "active" : ""}`}>
            <Background idx={1} />
            <Background idx={2} />
            <Background idx={3} />
          </div>
          <div className="content">
            {page === "join" && <JoinForm />}
            {page === "login" && <LoginForm />}
          </div>
        </section>
      </div>
    </>
  );
};

export default Member;
