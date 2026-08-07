import API from "../api/axiosConfig";


// Test summary controller
const testSummaryController = async()=>{

    const response = await API.get(
        "/api/summaries/test"
    );

    return response.data;
};




// Generate summary
const generateSummary = async(meetingId)=>{

    const response = await API.post(
        `/api/summaries/generate/${meetingId}`
    );

    return response.data;

};




// Download PDF
const downloadSummaryPDF = async(meetingId)=>{

    const response = await API.get(

        `/api/summaries/download/${meetingId}`,

        {
            responseType:"blob"
        }

    );


    return response.data;

};




// History
const getCombinedHistory = async()=>{

    const response = await API.get(
        "/api/notes/combined-history"
    );

    return response.data;

};





const summaryService = {

    testSummaryController,

    generateSummary,

    downloadSummaryPDF,

    getCombinedHistory

};



export default summaryService;