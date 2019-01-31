import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import projectReduer from "./projectReducer";

export default combineReducers({
  errors: errorReducer,
  project: projectReduer
});
