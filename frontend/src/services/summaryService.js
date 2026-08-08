import API from "../api/axiosConfig";

const testSummaryController = async () => {
    const response = await API.get(
        "/api/summaries/test"
    );
    return response.data;
};
const generateSummary = async (meetingId) => {
    const response = await API.post(
        `/api/summaries/generate/${meetingId}`
    );
    return response.data;
};

const getSummaryHistory = async () => {
    const response = await API.get(
        "/api/summaries/history"
    );
    return response.data;
};

const downloadSummaryPDF = async (meetingId) => {
    const response = await API.get(
        `/api/summaries/download/${meetingId}`,
        {
            responseType: "blob"
        }
    );
    return response.data;
};
const summaryService = {
    testSummaryController,
    generateSummary,
    getSummaryHistory,
    downloadSummaryPDF
};
export default summaryService;