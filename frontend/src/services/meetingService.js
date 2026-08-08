import API from "../api/axiosConfig";

// =====================================================
// CREATE MEETING
// =====================================================

const createMeeting = async (meetingData) => {

    const response = await API.post(
        "/api/meetings",
        meetingData
    );

    return response.data;
};


// =====================================================
// ADD MANUAL MEETING
// =====================================================

const addMeeting = async (meetingData) => {

    const response = await API.post(
        "/api/meetings/add",
        meetingData
    );

    return response.data;
};


// =====================================================
// GET MEETING BY ID
// =====================================================

const getMeeting = async (id) => {

    const response = await API.get(
        `/api/meetings/${id}`
    );

    return response.data;
};


// =====================================================
// VIEW MEETING
// Used by ViewMeeting.jsx
// =====================================================

const viewMeeting = async (id) => {

    const response = await API.get(
        `/api/meetings/view/${id}`
    );

    return response.data;
};


// =====================================================
// UPLOAD TRANSCRIPT
// =====================================================

const uploadTranscript = async (file) => {

    const formData = new FormData();

    formData.append("file", file);

    const response = await API.post(
        "/api/meetings/upload",
        formData,
        {
            headers: {
                "Content-Type": "multipart/form-data"
            }
        }
    );

    return response.data;
};


// =====================================================
// MEETING HISTORY
// =====================================================

const getHistory = async () => {

    const response = await API.get(
        "/api/meetings/history"
    );

    return response.data;
};


// =====================================================
// SEARCH MEETINGS
// =====================================================

const searchMeetings = async (keyword) => {

    const response = await API.get(
        "/api/meetings/history",
        {
            params: {
                keyword: keyword
            }
        }
    );

    return response.data;
};


// =====================================================
// DOWNLOAD MEETING PDF
// Used by MeetingHistory.jsx
// =====================================================

const downloadReport = async (id) => {

    const response = await API.get(
        `/api/meetings/download/${id}`,
        {
            responseType: "blob"
        }
    );

    return response.data;
};


// =====================================================
// EXPORT ALL METHODS
// =====================================================

const meetingService = {

    createMeeting,

    addMeeting,

    getMeeting,

    viewMeeting,

    uploadTranscript,

    getHistory,

    searchMeetings,

    downloadReport

};


export default meetingService;