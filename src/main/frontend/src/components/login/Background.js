import { useEffect, useState } from "react";

const Background = ({ idx }) => {
  const [bgIdx, setBgIdx] = useState(1);
  useEffect(() => {
    const timer = setInterval(() => {
      setBgIdx(bgIdx >= 3 ? 1 : bgIdx + 1);
    }, 5000);
    return () => clearInterval(timer);
  }, [bgIdx]);
  return (
    <article className={`item${idx} ${idx === bgIdx ? "active" : ""}`}>
      {bgIdx}
    </article>
  );
};

export default Background;
