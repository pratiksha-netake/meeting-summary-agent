import axios from "../api/axiosConfig";



const authService = {




    register: async(userData)=>{


        const response = await axios.post(

            "/auth/register",

            userData

        );


        return response.data;


    },







    login: async(credentials)=>{


        const response = await axios.post(

            "/auth/login",

            credentials

        );



        return response.data;


    }





};



export default authService;