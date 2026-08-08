import { useEffect, useState } from "react";

import { useNavigate } from "react-router-dom";

import Navbar from "../components/Navbar";
import GlassCard from "../components/GlassCard";
import Button from "../components/Button";
import Loader from "../components/Loader";

import meetingService from "../services/meetingService";
import summaryService from "../services/summaryService";

import "../styles/dashboard.css";

function MeetingHistory() {

    // =====================================================
    // MEETING HISTORY
    // =====================================================

    const [meetings, setMeetings] = useState([]);

    const [keyword, setKeyword] = useState("");

    const [loading, setLoading] = useState(false);


    // =====================================================
    // SUMMARY HISTORY
    // =====================================================

    const [summaryHistory, setSummaryHistory] =
        useState([]);

    const [summaryLoading, setSummaryLoading] =
        useState(false);


    const navigate = useNavigate();


    // =====================================================
    // LOAD NORMAL MEETING HISTORY
    // =====================================================

    const loadHistory = async () => {

        try {

            setLoading(true);

            const data =
                await meetingService.getHistory();

            setMeetings(
                data || []
            );

        }
        catch (error) {

            console.log(
                "History Error:",
                error
            );

        }
        finally {

            setLoading(false);

        }

    };


    // =====================================================
    // LOAD SUMMARY HISTORY
    // =====================================================

    const loadSummaryHistory = async () => {

        try {

            setSummaryLoading(true);

            const data =
                await summaryService
                    .getSummaryHistory();

            setSummaryHistory(
                Array.isArray(data)
                    ? data
                    : []
            );

        }
        catch (error) {

            console.log(
                "Summary History Error:",
                error
            );

        }
        finally {

            setSummaryLoading(false);

        }

    };


    // =====================================================
    // SEARCH NORMAL MEETINGS
    // =====================================================

    const search = async () => {

        try {

            setLoading(true);

            const data =
                await meetingService
                    .searchMeetings(
                        keyword
                    );

            setMeetings(
                data || []
            );

        }
        catch (error) {

            console.log(
                "Search Error:",
                error
            );

        }
        finally {

            setLoading(false);

        }

    };


    // =====================================================
    // VIEW MEETING
    // =====================================================

    const handleViewMeeting = (id) => {

        navigate(
            `/meeting/${id}`
        );

    };


    // =====================================================
    // DOWNLOAD MEETING PDF
    // =====================================================

    const downloadPDF = async (id) => {

        try {

            const file =
                await meetingService
                    .downloadReport(id);


            const url =
                window.URL.createObjectURL(

                    new Blob(
                        [file],
                        {
                            type:
                                "application/pdf"
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

            window.URL.revokeObjectURL(
                url
            );

        }
        catch (error) {

            console.log(
                "PDF Download Error:",
                error
            );

        }

    };


    // =====================================================
    // VIEW SUMMARY
    // =====================================================

    const handleViewSummary = (meetingId) => {

        navigate(
            `/summary?meetingId=${meetingId}`
        );

    };


    // =====================================================
    // INITIAL LOAD
    // =====================================================

    useEffect(() => {

        loadHistory();

        loadSummaryHistory();

    }, []);


    // =====================================================
    // RENDER
    // =====================================================

    return (

        <div className="dashboard-page">

            <Navbar />


            <div className="dashboard-container">


                {/* =================================================
                    NORMAL MEETING HISTORY
                ================================================= */}

                <GlassCard>

                    <h1>
                        Meeting History
                    </h1>


                    <div className="search-box">

                        <input
                            type="text"
                            placeholder="Search meetings"
                            value={keyword}
                            onChange={(e) =>
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


                {/* =================================================
                    NORMAL MEETING LOADING
                ================================================= */}

                {loading && (

                    <Loader
                        text="Loading meetings..."
                    />

                )}


                {/* =================================================
                    NORMAL MEETING EMPTY
                ================================================= */}

                {!loading &&
                    meetings.length === 0 && (

                    <GlassCard>

                        <h2>
                            No meetings found
                        </h2>

                    </GlassCard>

                )}


                {/* =================================================
                    NORMAL MEETING LIST
                ================================================= */}

                {!loading &&
                    meetings.length > 0 &&

                    meetings.map(
                        (meeting, index) => (

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

                                <b>
                                    Created At:
                                </b>

                                {" "}

                                {
                                    meeting.createdAt
                                        ? new Date(
                                            meeting.createdAt
                                        ).toLocaleString()
                                        : "N/A"
                                }

                            </p>


                            <div
                                style={{
                                    display:
                                        "flex",
                                    gap:
                                        "10px",
                                    marginTop:
                                        "15px"
                                }}
                            >

                                <Button
                                    onClick={() =>
                                        handleViewMeeting(
                                            meeting.id
                                        )
                                    }
                                >
                                    View Meeting
                                </Button>


                                <Button
                                    onClick={() =>
                                        downloadPDF(
                                            meeting.id
                                        )
                                    }
                                >
                                    Download PDF
                                </Button>

                            </div>

                        </GlassCard>

                    ))

                }


                {/* =================================================
                    SUMMARY HISTORY
                ================================================= */}

                <GlassCard>

                    <h1>
                        Summary History
                    </h1>

                    <p>
                        Previously generated meeting summaries
                    </p>

                </GlassCard>


                {/* =================================================
                    SUMMARY LOADING
                ================================================= */}

                {summaryLoading && (

                    <Loader
                        text="Loading summary history..."
                    />

                )}


                {/* =================================================
                    SUMMARY EMPTY
                ================================================= */}

                {!summaryLoading &&
                    summaryHistory.length === 0 && (

                    <GlassCard>

                        <h2>
                            No summaries generated yet
                        </h2>

                    </GlassCard>

                )}


                {/* =================================================
                    SUMMARY HISTORY LIST
                ================================================= */}

                {!summaryLoading &&
                    summaryHistory.length > 0 &&

                    summaryHistory.map(
                        (summary, index) => (

                        <GlassCard
                            key={
                                `${summary.id}-${index}`
                            }
                        >

                            {/* TITLE */}

                            <h2>

                                {
                                    summary.title ||
                                    "Meeting"
                                }

                            </h2>


                            {/* TYPE */}

                            <p>

                                <b>
                                    Type:
                                </b>

                                {" "}

                                {
                                    summary.type ||
                                    "SUMMARY"
                                }

                            </p>


                            {/* CREATED DATE */}

                            <p>

                                <b>
                                    Created At:
                                </b>

                                {" "}

                                {
                                    summary.createdAt
                                        ? new Date(
                                            summary.createdAt
                                        ).toLocaleString()
                                        : "N/A"
                                }

                            </p>


                            {/* ACTION */}

                            <div
                                style={{
                                    marginTop:
                                        "15px"
                                }}
                            >

                                <Button
                                    onClick={() =>
                                        handleViewSummary(
                                            summary.id
                                        )
                                    }
                                >
                                    View Summary
                                </Button>

                            </div>

                        </GlassCard>

                    ))

                }


            </div>

        </div>

    );
}

export default MeetingHistory;