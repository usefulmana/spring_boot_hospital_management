import {
  CREATE_NEW_PATIENT, DELETE_PATIENT_INFO,
  GET_A_PATIENT_BY_ID,
  GET_ALL_PATIENTS,
  GET_PATIENTS_BY_NAME,
  UPDATE_PATIENT_INFO
} from "../actions/types";

const initialState = {
  allItems: [],
  items: [],
  item: {}
};

export default function (state = initialState, action) {
  switch (action.type) {
    case GET_ALL_PATIENTS:
      return {
        ...state,
        allItems: action.payload
      };
    case GET_PATIENTS_BY_NAME:
      return {
        ...state,
        items: action.payload
      };
    case GET_A_PATIENT_BY_ID:
      return {
        ...state,
        item: action.payload
      };
    case CREATE_NEW_PATIENT:
      return {
        ...state,
        item: action.payload
      };
    case UPDATE_PATIENT_INFO:
      return {
        ...state,
        item: action.payload
      };
    case DELETE_PATIENT_INFO:
      return{
        ...state,
        item: action.payload
      };
    default:
      return state;

  }
}