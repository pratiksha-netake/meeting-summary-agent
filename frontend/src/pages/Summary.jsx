import { useEffect, useRef, useState } from "react";

import Navbar from "../components/Navbar";
import GlassCard from "../components/GlassCard";
import Button from "../components/Button";
import Loader from "../components/Loader";

import meetingService from "../services/meetingService";
import summaryService from "../services/summaryService";

import "../styles/dashboard.css";

function Summary() {

    // =====================================================
    // SEARCH MODE
    // =====================================================

    const [searchOpen, setSearchOpen] = useState(false);

    const [searchMode, setSearchMode] = useState(null);
    // null
    // "upload"
    // "search"


    const [searchText, setSearchText] = useState("");


    // =====================================================
    // MEETINGS
    // =====================================================

    const [meetings, setMeetings] = useState([]);

    const [selectedMeeting, setSelectedMeeting] =
        useState(null);


    // =====================================================
    // SUMMARY
    // =====================================================

    const [summary, setSummary] = useState(null);

    const [loading, setLoading] = useState(false);


    // =====================================================
    // UPLOAD
    // =====================================================

    const fileInputRef = useRef(null);

    const [uploading, setUploading] = useState(false);


    // =====================================================
    // ERROR
    // =====================================================

    const [error, setError] = useState("");


    // =====================================================
    // LOAD MEETINGS
    // =====================================================

    useEffect(() => {

        loadMeetings();

    }, []);


    const loadMeetings = async () => {

        try {

            setError("");

            const data =
                await meetingService.getHistory();


            setMeetings(
                Array.isArray(data)
                    ? data
                    : []
            );

        }
        catch (error) {

            console.error(
                "Meeting history error:",
                error
            );

            setError(
                "Unable to load meeting history."
            );

        }

    };


    // =====================================================
    // FILTER EXISTING MEETINGS
    // =====================================================

    const filteredMeetings =
        meetings.filter((meeting) => {

            const title =
                meeting.title || "";

            return title
                .toLowerCase()
                .includes(
                    searchText.toLowerCase()
                );

        });


    // =====================================================
    // SEARCH INPUT CLICK
    // =====================================================

    const handleSearchFocus = () => {

        setSearchOpen(true);

    };


    // =====================================================
    // SELECT SEARCH MODE
    // =====================================================

    const selectSearchMode = (mode) => {

        setSearchMode(mode);

        setSearchOpen(false);

        setSelectedMeeting(null);

        setSummary(null);

        setError("");


        // =================================================
        // UPLOAD MODE
        // =================================================

        if (mode === "upload") {

            setSearchText("Upload File");


            // Open file picker directly

            setTimeout(() => {

                if (fileInputRef.current) {

                    fileInputRef.current.click();

                }

            }, 100);

        }


        // =================================================
        // SEARCH MODE
        // =================================================

        if (mode === "search") {

            setSearchText("Search Meeting");

            setTimeout(() => {

                setSearchOpen(true);

            }, 100);

        }

    };


    // =====================================================
    // SEARCH CHANGE
    // =====================================================

    const handleSearchChange = (event) => {

        const value =
            event.target.value;


        // If user is searching existing meetings

        if (searchMode === "search") {

            setSearchText(value);

            setSelectedMeeting(null);

            setSummary(null);

            setError("");

        }

    };


    // =====================================================
    // SELECT EXISTING MEETING
    // =====================================================

    const selectMeeting = (meeting) => {

        setSelectedMeeting(meeting);

        setSearchText(
            meeting.title || ""
        );

        setSearchOpen(false);

        setSummary(null);

        setError("");

    };


    // =====================================================
    // FILE UPLOAD
    // =====================================================

    const handleFileUpload = async (event) => {

        const file =
            event.target.files?.[0];


        if (!file) {

            return;

        }


        try {

            setUploading(true);

            setError("");

            setSummary(null);


            const uploadedMeeting =
                await meetingService.uploadTranscript(
                    file
                );


            // Backend returns created Meeting

            setSelectedMeeting(
                uploadedMeeting
            );


            setSearchText(
                uploadedMeeting.title ||
                file.name
            );


            // Refresh history

            await loadMeetings();


        }
        catch (error) {

            console.error(
                "Upload error:",
                error
            );


            const backendMessage =
                error.response?.data;


            setError(
                typeof backendMessage === "string"
                    ? backendMessage
                    : "Unable to upload file."
            );

        }
        finally {

            setUploading(false);


            // Reset input so same file
            // can be selected again

            if (fileInputRef.current) {

                fileInputRef.current.value = "";

            }

        }

    };


    // =====================================================
    // GENERATE SUMMARY
    // =====================================================

    const generate = async () => {

        if (!selectedMeeting) {

            setError(
                "Please select a meeting first."
            );

            return;

        }


        try {

            setLoading(true);

            setError("");

            setSummary(null);


            const response =
                await summaryService.generateSummary(
                    selectedMeeting.id
                );


            setSummary(response);

        }
        catch (error) {

            console.error(
                "Generate Summary Error:",
                error
            );


            const message =
                error.response?.data;


            setError(
                typeof message === "string"
                    ? message
                    : "Unable to generate summary."
            );

        }
        finally {

            setLoading(false);

        }

    };


    // =====================================================
    // DOWNLOAD PDF
    // =====================================================

    const downloadPDF = async () => {

        if (!selectedMeeting) {

            setError(
                "Please select a meeting first."
            );

            return;

        }


        try {

            setError("");


            const file =
                await summaryService.downloadSummaryPDF(
                    selectedMeeting.id
                );


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
                `${selectedMeeting.title || "Meeting"}-Summary.pdf`;


            document.body.appendChild(link);


            link.click();


            document.body.removeChild(link);


            window.URL.revokeObjectURL(url);

        }
        catch (error) {

            console.error(
                "PDF Download Error:",
                error
            );


            setError(
                "Unable to download PDF."
            );

        }

    };


    // =====================================================
    // FORMAT DATE
    // =====================================================

    const formatDate = (date) => {

        if (!date) {

            return "";

        }


        try {

            return new Date(date)
                .toLocaleString();

        }
        catch {

            return "";

        }

    };


    // =====================================================
    // RENDER
    // =====================================================

    return (

        <div className="dashboard-page">

            <Navbar />


            <div className="dashboard-container">


                {/* =================================================
                    SEARCH / GENERATE SECTION
                ================================================= */}

                <GlassCard>

                    <h1>
                        Generate Meeting Summary
                    </h1>


                    {/* Hidden file input */}

                    <input
                        ref={fileInputRef}
                        type="file"
                        accept=".txt,.pdf,.doc,.docx"
                        onChange={handleFileUpload}
                        style={{
                            display: "none"
                        }}
                    />


                    {/* =================================================
                        SEARCH INPUT
                    ================================================= */}

                    <div
                        style={{
                            position: "relative"
                        }}
                    >

                        <input
                            type="text"
                            placeholder="Search Meeting"
                            value={searchText}
                            onFocus={handleSearchFocus}
                            onChange={handleSearchChange}
                            readOnly={
                                searchMode !== "search"
                            }
                            style={{
                                width: "100%",
                                boxSizing: "border-box"
                            }}
                        />


                        {/* =================================================
                            FIRST MENU
                        ================================================= */}

                        {searchOpen &&
                            searchMode === null && (

                            <div
                                style={{
                                    marginTop: "12px",
                                    display: "flex",
                                    flexDirection: "column",
                                    gap: "10px"
                                }}
                            >

                                {/* Upload File */}

                                <div
                                    onMouseDown={() =>
                                        selectSearchMode(
                                            "upload"
                                        )
                                    }
                                    style={{
                                        cursor: "pointer",
                                        padding: "16px 18px",
                                        borderRadius: "10px",
                                        border:
                                            "1px solid rgba(255,255,255,0.15)"
                                    }}
                                >

                                    <strong>
                                        Upload File
                                    </strong>

                                    <div>
                                        Upload a new meeting transcript
                                    </div>

                                </div>


                                {/* Search Meeting */}

                                <div
                                    onMouseDown={() =>
                                        selectSearchMode(
                                            "search"
                                        )
                                    }
                                    style={{
                                        cursor: "pointer",
                                        padding: "16px 18px",
                                        borderRadius: "10px",
                                        border:
                                            "1px solid rgba(255,255,255,0.15)"
                                    }}
                                >

                                    <strong>
                                        Search Meeting
                                    </strong>

                                    <div>
                                        Search existing meetings
                                    </div>

                                </div>

                            </div>

                        )}


                    </div>


                    {/* =================================================
                        SEARCH MODE
                    ================================================= */}

                    {searchMode === "search" &&
                        searchOpen && (

                        <div
                            style={{
                                marginTop: "20px"
                            }}
                        >

                            <h3>
                                Meeting History
                            </h3>


                            {filteredMeetings.length === 0 ? (

                                <p>
                                    No meetings found.
                                </p>

                            ) : (

                                <div
                                    style={{
                                        display: "grid",
                                        gridTemplateColumns:
                                            "repeat(auto-fill, minmax(280px, 1fr))",
                                        gap: "16px"
                                    }}
                                >

                                    {filteredMeetings.map(
                                        (meeting) => (

                                        <div
                                            key={
                                                meeting.id
                                            }
                                            onMouseDown={() =>
                                                selectMeeting(
                                                    meeting
                                                )
                                            }
                                            style={{
                                                cursor:
                                                    "pointer",
                                                padding:
                                                    "18px",
                                                borderRadius:
                                                    "12px",
                                                border:
                                                    "1px solid rgba(255,255,255,0.15)"
                                            }}
                                        >

                                            <h3
                                                style={{
                                                    marginTop: 0
                                                }}
                                            >
                                                {
                                                    meeting.title
                                                }
                                            </h3>


                                            <p>
                                                {
                                                    meeting.type
                                                }
                                            </p>


                                            <small>
                                                {
                                                    formatDate(
                                                        meeting.createdAt
                                                    )
                                                }
                                            </small>

                                        </div>

                                    ))}

                                </div>

                            )}

                        </div>

                    )}


                    {/* =================================================
                        UPLOADING
                    ================================================= */}

                    {uploading && (

                        <div
                            style={{
                                marginTop: "20px"
                            }}
                        >

                            <Loader
                                text="Uploading meeting..."
                            />

                        </div>

                    )}


                    {/* =================================================
                        SELECTED MEETING
                    ================================================= */}

                    {selectedMeeting && (

                        <div
                            style={{
                                marginTop: "20px",
                                padding: "15px",
                                borderRadius: "10px",
                                border:
                                    "1px solid rgba(255,255,255,0.15)"
                            }}
                        >

                            <strong>
                                Selected Meeting:
                            </strong>

                            <div>
                                {
                                    selectedMeeting.title
                                }
                            </div>

                        </div>

                    )}


                    {/* =================================================
                        ERROR
                    ================================================= */}

                    {error && (

                        <p
                            style={{
                                marginTop: "15px"
                            }}
                        >
                            {error}
                        </p>

                    )}


                    {/* =================================================
                        GENERATE SUMMARY BUTTON
                    ================================================= */}

                    <div
                        style={{
                            marginTop: "20px"
                        }}
                    >

                        {loading ? (

                            <Loader
                                text="Generating summary..."
                            />

                        ) : (

                            <Button
                                onClick={generate}
                                disabled={
                                    !selectedMeeting ||
                                    uploading
                                }
                            >
                                Generate Summary
                            </Button>

                        )}

                    </div>


                </GlassCard>


                {/* =================================================
                    SUMMARY RESULT
                ================================================= */}

                {summary && selectedMeeting && (

                    <GlassCard>

                        <h2>
                            {
                                selectedMeeting.title
                            }
                        </h2>


                        {/* SUMMARY */}

                        <h3>
                            Summary
                        </h3>

                        <p>
                            {
                                summary.summary
                            }
                        </p>


                        {/* DISCUSSION */}

                        <h3>
                            Discussion Points
                        </h3>

                        <p
                            style={{
                                whiteSpace:
                                    "pre-line"
                            }}
                        >
                            {
                                summary.discussionPoints
                            }
                        </p>


                        {/* DECISIONS */}

                        <h3>
                            Decisions
                        </h3>

                        <p
                            style={{
                                whiteSpace:
                                    "pre-line"
                            }}
                        >
                            {
                                summary.decisions
                            }
                        </p>


                        {/* ACTION ITEMS */}

                        <h3>
                            Action Items
                        </h3>

                        <p
                            style={{
                                whiteSpace:
                                    "pre-line"
                            }}
                        >
                            {
                                summary.actionItems
                            }
                        </p>


                        {/* DOWNLOAD */}

                        <div
                            style={{
                                marginTop: "20px"
                            }}
                        >

                            <Button
                                onClick={
                                    downloadPDF
                                }
                            >
                                Download PDF
                            </Button>

                        </div>

                    </GlassCard>

                )}

            </div>

        </div>

    );

}

export default Summary;