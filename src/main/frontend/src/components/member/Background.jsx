import { useEffect, useRef, useState } from "react";
import { CSSTransition, Transition } from "react-transition-group";

const Background = ({ idx }) => {
  const [bgIdx, setBgIdx] = useState(1);
  const [inProp, setInProp] = useState(false);
  useEffect(() => {
    setInProp(idx === bgIdx ? true : false);
    const timer = setInterval(() => {
      setBgIdx(bgIdx >= 3 ? 1 : bgIdx + 1);
    }, 5000);
    return () => {
      clearInterval(timer);
    };
  }, [bgIdx]);

  return (
    <>
      <CSSTransition in={inProp} timeout={8000} classNames="my-node">
        <article className={`item${idx}`}>{bgIdx}</article>
      </CSSTransition>
    </>
  );
};

export default Background;
