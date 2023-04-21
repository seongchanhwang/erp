import React from "react";

export const JoinForm = () => {
  return (
    <>
      <div className="txt-box">
        <h2>회원가입</h2>
        <p>
          A simple template for telling the world when you'll launch your next
          big thing. Brought to you by HTML5 UP.
        </p>
      </div>
      <form>
        <div className="input-box">
          <div>
            <input type="text" name="name" placeholder="이름 " />
          </div>
          <div>
            <input type="text" name="email" placeholder="이메일 " />
          </div>
          <div>
            <input type="password" name="pw" placeholder="비밀번호" />
          </div>
          <div>
            <input type="password" name="pwCheck" placeholder="비밀번호 확인" />
          </div>
          <button className="btn-submit">Join</button>
        </div>
      </form>
    </>
  );
};
