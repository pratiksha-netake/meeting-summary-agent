import { useState } from "react";

import Navbar from "../components/Navbar";
import GlassCard from "../components/GlassCard";
import Button from "../components/Button";
import Loader from "../components/Loader";

import meetingService from "../services/meetingService";

import "../styles/dashboard.css";



function FileUpload() {


    const [file, setFile] = useState(null);

    const [response, setResponse] = useState("");

    const [loading, setLoading] = useState(false);





    const handleFileChange = (e) => {


        setFile(e.target.files[0]);


        setResponse("");

    };







    const handleUpload = async () => {


        if (!file) {


            setResponse(
                "Please select a file first"
            );


            return;

        }





        try {


            setLoading(true);


            setResponse("");



            const result = await meetingService.uploadTranscript(file);



            console.log(result);



            setResponse(
                "Transcript uploaded successfully"
            );


        }

        catch(error) {


            console.log(error);


            setResponse(
                "File upload failed"
            );


        }

        finally {


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

                        Upload your meeting transcript file to generate AI summary.

                    </p>





                    <input

                        type="file"

                        accept=".txt,.pdf,.doc,.docx"

                        onChange={handleFileChange}

                    />





                    {

                    file &&

                    <p>

                        Selected File:

                        {" "}

                        {file.name}

                    </p>

                    }





                    {


                    loading ?

                    <Loader text="Uploading transcript..." />

                    :

                    <Button

                        onClick={handleUpload}

                    >

                        Upload File

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


export default FileUpload;