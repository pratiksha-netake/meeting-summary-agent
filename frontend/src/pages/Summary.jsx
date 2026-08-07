import { useState } from "react";

import Navbar from "../components/Navbar";
import GlassCard from "../components/GlassCard";
import Button from "../components/Button";
import Loader from "../components/Loader";

import summaryService from "../services/summaryService";

import "../styles/dashboard.css";



function Summary(){


    const [meetingId,setMeetingId] = useState("");

    const [summary,setSummary] = useState(null);

    const [loading,setLoading] = useState(false);





    const generate = async()=>{


        if(!meetingId){
            return;
        }



        try{

            setLoading(true);


            const response =
                await summaryService.generateSummary(
                    meetingId
                );


            setSummary(response);


        }
        catch(error){

            console.log(
                "Generate Summary Error:",
                error.response?.data || error.message
            );

        }
        finally{

            setLoading(false);

        }


    };







    const downloadPDF = async()=>{


        try{


            const file =
                await summaryService.downloadSummaryPDF(
                    meetingId
                );



            const url =
                window.URL.createObjectURL(
                    new Blob(
                        [file],
                        {
                            type:"application/pdf"
                        }
                    )
                );



            const link =
                document.createElement("a");



            link.href=url;

            link.download="MeetingReport.pdf";


            document.body.appendChild(link);


            link.click();


            link.remove();


            window.URL.revokeObjectURL(url);



        }
        catch(error){

            console.log(
                "PDF Download Error:",
                error
            );

        }


    };







    return (

        <div className="dashboard-page">


            <Navbar />



            <div className="dashboard-container">



                <GlassCard>


                    <h1>
                        Generate AI Summary
                    </h1>



                    <input

                        type="number"

                        placeholder="Enter Meeting ID"

                        value={meetingId}

                        onChange={
                            (e)=>
                            setMeetingId(
                                e.target.value
                            )
                        }

                    />



                    {

                        loading ?

                        <Loader text="Generating summary..." />

                        :

                        <Button onClick={generate}>

                            Generate Summary

                        </Button>

                    }



                </GlassCard>






                {

                    summary &&


                    <GlassCard>


                        <h2>
                            Summary
                        </h2>



                        <p>
                            {summary.summary}
                        </p>



                        <p>

                            <b>
                                Discussion Points:
                            </b>

                            <br/>

                            {summary.discussionPoints}

                        </p>



                        <p>

                            <b>
                                Decisions:
                            </b>

                            <br/>

                            {summary.decisions}

                        </p>



                        <p>

                            <b>
                                Action Items:
                            </b>

                            <br/>

                            {summary.actionItems}

                        </p>




                        <Button onClick={downloadPDF}>

                            Download PDF

                        </Button>



                    </GlassCard>

                }



            </div>


        </div>

    );


}



export default Summary;