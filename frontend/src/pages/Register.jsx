import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

import GlassCard from "../components/GlassCard";
import Button from "../components/Button";
import Loader from "../components/Loader";

import authService from "../services/authService";

import "../styles/auth.css";


function Register(){


const navigate = useNavigate();



const [formData,setFormData] = useState({

    fullName:"",
    email:"",
    password:""

});



const [loading,setLoading] = useState(false);

const [message,setMessage] = useState("");





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

        setMessage("");



        const response = await authService.register(
            formData
        );



        setMessage(response);



        setTimeout(()=>{


            navigate("/login");


        },1500);



    }

    catch(error){


        console.log(error);


        if(error.response && error.response.data){


            setMessage(error.response.data);


        }

        else{


            setMessage("Registration failed");


        }


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
                message &&

                <p className="message">

                    {message}

                </p>
            }






            <form onSubmit={handleSubmit}>


                <input

                    type="text"

                    name="fullName"

                    placeholder="Full Name"

                    value={formData.fullName}

                    onChange={handleChange}

                    required

                />






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

                    <Loader text="Creating account..." />

                    :

                    <Button type="submit">

                        Register

                    </Button>

                }



            </form>







            <p>

                Already have account?


                <Link to="/login">

                    Login

                </Link>


            </p>





        </GlassCard>


    </div>


);


}


export default Register;