import axios from "axios";
import { GET_ERRORS, GET_PROJECTS, GET_PROJECT } from "./types";

export const createProject = (project, history) => async dispatch => {
  try {
    const res = await axios.post("http://localhost:8090/api/project", project);
    history.push("/dashboard");

    // Problem : Encountered a problem where validation error showed up on other projects when updating any existing project.
    // soluton - everytime when it creates new project and if its good data, don't propoget the error
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

export const getProjects = () => async dispatch => {
  const res = await axios.get("http://localhost:8090/api/project/all");
  dispatch({
    type: GET_PROJECTS,
    payload: res.data
  });
};

export const getProject = (id, history) => async dispatch => {
  try {
    const res = await axios.get(`http://localhost:8090/api/project/${id}`);
    dispatch({
      type: GET_PROJECT,
      payload: res.data
    });
  } catch (error) {
    // redirect to dashboard if bad request in URL like : http://localhost:3000/dashboard/react32423423423
    history.push("/dashboard");
  }
};
