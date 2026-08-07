import { useState } from "react";

import Navbar from "../components/Navbar";
import GlassCard from "../components/GlassCard";
import Button from "../components/Button";
import Loader from "../components/Loader";

import meetingService from "../services/meetingService";

import "../styles/dashboard.css";



function Meeting(){


    const [formData,setFormData] = useState({

        meetingTitle:"",

        notes:""

    });



    const [message,setMessage] = useState("");

    const [loading,setLoading] = useState(false);







    const handleChange = (e)=>{


        setFormData({

            ...formData,

            [e.target.name]:e.target.value

        });


    };







    const saveMeetingNotes = async(e)=>{


        e.preventDefault();



        try{


            setLoading(true);


            setMessage("");



            const response = await meetingService.addNotes(
                formData
            );



            console.log(response);



            setMessage(
                "Meeting notes saved successfully"
            );



            setFormData({

                meetingTitle:"",

                notes:""

            });


        }


        catch(error){


            console.log(error);



            if(error.response){

                setMessage(
                    error.response.data.message ||
                    "Failed to save notes"
                );

            }

            else{

                setMessage(
                    "Server not reachable"
                );

            }


        }


        finally{


            setLoading(false);


        }


    };








    return (


        <div className="dashboard-page">



            <Navbar />




            <div className="dashboard-container">



                <GlassCard>



                    <h1>

                        Enter Meeting Notes

                    </h1>



                    <p>


                        Add your meeting details manually

                        without uploading a transcript.


                    </p>






                    <form onSubmit={saveMeetingNotes}>


                        <input


                            type="text"


                            name="meetingTitle"


                            placeholder="Meeting Title"


                            value={formData.meetingTitle}


                            onChange={handleChange}


                        />






                        <textarea


                            name="notes"


                            placeholder="Enter meeting notes"


                            rows="8"


                            value={formData.notes}


                            onChange={handleChange}


                        />







                        {


                        loading ?


                        <Loader text="Saving notes..." />


                        :


                        <Button type="submit">


                            Save Meeting Notes


                        </Button>


                        }



                    </form>







                    {


                    message &&


                    <p className="message">


                        {message}


                    </p>


                    }



                </GlassCard>




            </div>



        </div>



    );


}



export default Meeting;