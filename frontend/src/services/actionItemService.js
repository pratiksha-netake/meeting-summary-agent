import API from "../api/axiosConfig";




// Assign action item
const assignActionItem = async(actionItemData)=>{


    const response = await API.post(

        "/api/action-items/assign",

        actionItemData

    );


    return response.data;


};






// Update action item status
const updateActionItemStatus = async(id,status)=>{


    const response = await API.put(

        `/api/action-items/${id}/status?status=${status}`

    );


    return response.data;


};






const actionItemService = {


    assignActionItem,

    updateActionItemStatus


};



export default actionItemService;