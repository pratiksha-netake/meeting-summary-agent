import { useEffect, useState } from "react";

import Navbar from "../components/Navbar";
import GlassCard from "../components/GlassCard";
import Button from "../components/Button";
import Loader from "../components/Loader";

import meetingService from "../services/meetingService";

import "../styles/dashboard.css";


function MeetingHistory(){


    const [meetings,setMeetings] = useState([]);

    const [keyword,setKeyword] = useState("");

    const [loading,setLoading] = useState(false);





    const loadHistory = async()=>{

        try{

            setLoading(true);


            const data =
                await meetingService.getCombinedHistory();


            setMeetings(data || []);


        }
        catch(error){

            console.log(error);

        }
        finally{

            setLoading(false);

        }

    };









    const search = async()=>{


        try{


            setLoading(true);



            const data =
                await meetingService.searchMeetings(
                    keyword
                );



            setMeetings(data || []);



        }
        catch(error){


            console.log(error);


        }
        finally{


            setLoading(false);


        }

    };









    const downloadPDF = async(id)=>{


        try{


            const file =
                await meetingService.downloadReport(id);




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



            link.href = url;



            link.download =
                "MeetingReport.pdf";



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









    useEffect(()=>{


        loadHistory();


    },[]);









    return (

        <div className="dashboard-page">


            <Navbar />



            <div className="dashboard-container">





                <GlassCard>


                    <h1>
                        Meeting History
                    </h1>





                    <div className="search-box">


                        <input


                            type="text"


                            placeholder="Search meetings"


                            value={keyword}


                            onChange={(e)=>

                                setKeyword(
                                    e.target.value
                                )

                            }


                        />





                        <Button
                            onClick={search}
                        >

                            Search

                        </Button>



                    </div>



                </GlassCard>









                {

                    loading ?


                    (

                        <Loader
                            text="Loading meetings..."
                        />

                    )



                    :



                    meetings.length === 0 ?



                    (

                        <GlassCard>

                            <h2>
                                No meetings found
                            </h2>

                        </GlassCard>

                    )



                    :



                    meetings.map(
                        
                        (meeting,index)=>(


                        <GlassCard

                            key={
                                `${meeting.id}-${index}`
                            }

                        >



                            <h2>


                                {

                                    meeting.title ||

                                    meeting.meetingTitle ||

                                    meeting.fileName ||

                                    "Meeting"

                                }


                            </h2>







                            <p>


                                <b>
                                    Type:
                                </b>


                                {" "}


                                {

                                    meeting.type ||

                                    "MEETING"

                                }


                            </p>







                            <p>


                                {

                                    meeting.message ||

                                    meeting.notes ||

                                    "No description"

                                }


                            </p>







                            <p>


                                <b>
                                    Date:
                                </b>


                                {" "}


                                {

                                    meeting.createdAt ||

                                    meeting.uploadedAt ||

                                    "N/A"

                                }



                            </p>







                            <Button


                                onClick={()=>


                                    downloadPDF(
                                        meeting.id
                                    )


                                }


                            >

                                Download PDF


                            </Button>







                        </GlassCard>


                    ))


                }





            </div>





        </div>

    );

}



export default MeetingHistory;