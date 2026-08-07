import API from "../api/axiosConfig";



const getStatistics = async()=>{


    const response =
        await API.get(
            "/api/dashboard/statistics"
        );


    return response.data;

};



const dashboardStatisticsService = {

    getStatistics

};


export default dashboardStatisticsService;