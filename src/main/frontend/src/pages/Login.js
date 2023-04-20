import React, { useEffect, useState } from "react";
import styles from "../css/login.scss";
import Background from "../components/login/Background";
import axios from "axios";

const Login = () => {
	const [load, setLoad] = useState(false);
	const [id, setId] = useState('');
	const [pw, setPw] = useState('');
	const [post, setPost] = useState({
		loginId: "",
		password: ""
	})
	const handleSubmit = async (e) => {
		e.preventDefault();
		try {
			console.log(id)
			console.log(pw)
			setPost({ ...post, loginId: id, password: pw })

//			var formData = new FormData();
//			formData.append("loginId", id);
//			formData.append("password", pw);
//			const response = await axios({
//				method: 'post',
//				url: '/api/login',
//				data: formData,
//			})
			 const response = await axios.post('/api/login', JSON.stringify({ post }), {
			 	headers: {
			 		"Content-Type": "application/json",
			 	}
			 });
			console.log(response);
		} catch (error) {
			console.error(error);
		}

	}
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
				<form onSubmit={handleSubmit}>
					<div className="input-box">
						<div><input name="loginId " type="text" placeholder="이메일을 입력해주세요" value={id} onChange={e => setId(e.target.value)} ></input></div>
						<div>
							<input
								name="Password"
								type="password"
								placeholder="비밀번호를 입력해주세요"
								value={pw}
								onChange={e => setPw(e.target.value)}
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
