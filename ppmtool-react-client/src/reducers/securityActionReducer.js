import { SET_CURRENT_USER } from "../actions/types";

const initialState = {
  user: {},
  validToken: false
};

const actionPayloadPresent = payload => {
  if (payload) {
    return true;
  } else {
    return false;
  }
};

export default (state = initialState, action) => {
  switch (action.type) {
    case SET_CURRENT_USER:
      return {
        ...state,
        validToken: actionPayloadPresent(action.payload),
        user: action.payload
      };
    default:
      return state;
  }
};
