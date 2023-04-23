import IntroWrap from "./IntroWrap";
import FormWrap from "./FormWrap";
const JoinForm = ({ page }) => {
  return (
    <>
      <FormWrap page={page} />
      <IntroWrap page={page} />
    </>
  );
};

export default JoinForm;
