import API from "../api/axiosConfig";


// ============================================
// GET DASHBOARD STATISTICS
// ============================================

const getStatistics = async () => {

    const response = await API.get(
        "/api/dashboard/statistics"
    );

    return response.data;
};


// ============================================
// EXPORT SERVICE
// ============================================

const dashboardStatisticsService = {

    getStatistics

};


export default dashboardStatisticsService;