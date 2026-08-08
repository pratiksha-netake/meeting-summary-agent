import { useEffect, useState } from "react";

import { useParams } from "react-router-dom";

import meetingService from "../services/meetingService";

import Navbar from "../components/Navbar";

import GlassCard from "../components/GlassCard";


function ViewMeeting() {

    const { id } = useParams();

    const [meeting, setMeeting] = useState(null);

    const [loading, setLoading] = useState(true);

    const [error, setError] = useState("");


    useEffect(() => {

        const loadMeeting = async () => {

            try {

                setLoading(true);

                setError("");

                const data =
                    await meetingService.viewMeeting(id);

                console.log("Meeting data:", data);

                setMeeting(data);

            } catch (error) {

                console.error(
                    "Failed to load meeting:",
                    error
                );

                setError(
                    "Unable to load meeting"
                );

            } finally {

                setLoading(false);
            }
        };


        if (id) {

            loadMeeting();

        }

    }, [id]);


    return (

        <div className="dashboard-page">

            <Navbar />

            <div className="dashboard-container">

                <GlassCard>

                    {
                        loading &&

                        <p>
                            Loading meeting...
                        </p>
                    }


                    {
                        error &&

                        <p className="message">
                            {error}
                        </p>
                    }


                    {
                        !loading &&
                        !error &&
                        meeting &&

                        <>

                            <h1>
                                {meeting.title}
                            </h1>


                            <p>
                                {meeting.notes}
                            </p>


                            <p>
                                {meeting.content}
                            </p>


                            <p>
                                Type: {meeting.type}
                            </p>


                            <p>
                                Created:
                                {" "}
                                {meeting.createdAt}
                            </p>

                        </>
                    }

                </GlassCard>

            </div>

        </div>
    );
}


export default ViewMeeting;