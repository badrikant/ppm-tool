import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import projectReduer from "./projectReducer";
import securityReducer from "./securityReducer";

export default combineReducers({
  errors: errorReducer,
  project: projectReduer,
  security: securityReducer
});
