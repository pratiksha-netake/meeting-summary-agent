import { useState } from "react";

import Navbar from "../components/Navbar";
import GlassCard from "../components/GlassCard";
import Button from "../components/Button";
import Loader from "../components/Loader";

import meetingService from "../services/meetingService";

import "../styles/dashboard.css";



function UploadTranscript(){


    const [file,setFile] = useState(null);

    const [response,setResponse] = useState("");

    const [loading,setLoading] = useState(false);





    const handleFileChange=(e)=>{


        setFile(
            e.target.files[0]
        );


    };






    const uploadFile=async()=>{


        if(!file){

            setResponse(
                "Please select a file"
            );

            return;

        }




        try{


            setLoading(true);

            setResponse("");



            const result = await meetingService.uploadTranscript(
                file
            );



            setResponse(

                "Transcript uploaded successfully"

            );



            console.log(result);



        }

        catch(error){


            setResponse(

                "Upload failed"

            );


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

                        Upload Meeting Transcript

                    </h1>



                    <p>

                        Upload your meeting file to generate AI summary.

                    </p>




                    <input

                        type="file"

                        onChange={handleFileChange}

                        accept=".txt,.pdf,.doc,.docx"

                    />





                    {

                    loading ?

                    <Loader text="Uploading transcript..." />

                    :

                    <Button onClick={uploadFile}>

                        Upload Transcript

                    </Button>


                    }





                    {

                    response &&

                    <p className="message">

                        {response}

                    </p>

                    }



                </GlassCard>



            </div>


        </div>


    );


}



export default UploadTranscript;