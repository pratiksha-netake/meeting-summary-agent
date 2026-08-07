import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import Button from "./Button";

import "../styles/navbar.css";


function Navbar() {


    const { logout } = useAuth();

    const navigate = useNavigate();



    const handleLogout = ()=>{


        logout();

        navigate("/login");


    };




    return (


        <nav className="navbar">



            <div className="nav-links">


                <Link to="/dashboard">
                    Dashboard
                </Link>



                <Link to="/upload">
                    Upload Transcript
                </Link>



                <Link to="/meeting">
                    Manual Notes
                </Link>



                <Link to="/history">
                    History
                </Link>



                <Link to="/summary">
                    Summary
                </Link>



                <Link to="/actions">
                    Action Items
                </Link>



                <Link to="/statistics">
                    Statistics
                </Link>


            </div>





            <div className="nav-user">


                <Button
                    onClick={handleLogout}
                >

                    Logout

                </Button>


            </div>




        </nav>


    );

}


export default Navbar;