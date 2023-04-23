import React, { useEffect, useState } from "react";
import styles from "../css/member.scss";
import Background from "../components/member/Background";
import LoginForm from "../components/member/LoginForm";
import { useSelector } from "react-redux";
import JoinForm from "../components/member/JoinForm";

import { useParams } from "react-router-dom";

const Member = () => {
  const [load, setLoad] = useState(false);
  const { page } = useParams();
  // test
  useEffect(() => {
    setLoad(true);
  }, []);

  return (
    <>
      <div id="member-wrap" className="flex-box justify-cnt dir-col">
        <section>
          <div className="content flex-box ">
            {page === "login" && <LoginForm />}
            {page === "join" && <JoinForm />}
          </div>
        </section>
      </div>
    </>
  );
};

export default Member;
