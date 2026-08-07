import API from "../api/axiosConfig";



// Upload transcript file
const uploadTranscript = async (file) => {


    const formData = new FormData();


    formData.append(
        "file",
        file
    );



    const response = await API.post(

        "/api/transcripts/upload",

        formData,

        {
            headers:{
                "Content-Type":"multipart/form-data"
            }
        }

    );


    return response.data;

};






// Add meeting notes
const addNotes = async (notesData)=>{


    const response = await API.post(

        "/api/notes/add",

        notesData

    );


    return response.data;

};






// Get old meeting history
const getMeetingHistory = async ()=>{


    const response = await API.get(

        "/api/notes/history"

    );


    return response.data;

};






// Get combined history
// Notes + Transcript in one table
const getCombinedHistory = async()=>{


    const response = await API.get(

        "/api/notes/combined-history"

    );


    return response.data;

};







// Search meetings
const searchMeetings = async(keyword)=>{


    const response = await API.get(

        `/api/notes/search?keyword=${keyword}`

    );


    return response.data;

};







// Download PDF report
const downloadReport = async(meetingId)=>{


    const response = await API.get(

        `/api/notes/download/${meetingId}`,

        {
            responseType:"blob"
        }

    );


    return response.data;

};







const meetingService = {


    uploadTranscript,


    addNotes,


    getMeetingHistory,


    getCombinedHistory,


    searchMeetings,


    downloadReport


};



export default meetingService;