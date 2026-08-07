import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

import GlassCard from "../components/GlassCard";
import Button from "../components/Button";
import Loader from "../components/Loader";

import authService from "../services/authService";
import { useAuth } from "../context/AuthContext";

import "../styles/auth.css";


function Login() {


const navigate = useNavigate();

const { login } = useAuth();



const [formData,setFormData] = useState({

    email:"",
    password:""

});



const [loading,setLoading] = useState(false);

const [error,setError] = useState("");





const handleChange=(e)=>{


    setFormData({

        ...formData,

        [e.target.name]:e.target.value

    });


};







const handleSubmit=async(e)=>{


    e.preventDefault();


    try{


        setLoading(true);

        setError("");



        const response = await authService.login(formData);



        login(

            response.token,

            response

        );



        navigate("/dashboard");



    }

    catch(err){


        setError(

            "Invalid email or password"

        );


    }

    finally{


        setLoading(false);


    }


};






return (

    <div className="auth-page">


        <GlassCard className="auth-card">


            <h1>
                Meeting Summary Agent
            </h1>



            {
            error &&

            <p className="error">
                {error}
            </p>

            }




            <form onSubmit={handleSubmit}>


                <input

                    type="email"

                    name="email"

                    placeholder="Email"

                    value={formData.email}

                    onChange={handleChange}

                    required

                />





                <input

                    type="password"

                    name="password"

                    placeholder="Password"

                    value={formData.password}

                    onChange={handleChange}

                    required

                />






                {

                loading ?

                <Loader text="Logging in..." />

                :

                <Button type="submit">

                    Login

                </Button>

                }



            </form>






            <p>

                Don't have an account?


                <Link to="/register">

                    Register

                </Link>

            </p>



        </GlassCard>



    </div>

);


}


export default Login;