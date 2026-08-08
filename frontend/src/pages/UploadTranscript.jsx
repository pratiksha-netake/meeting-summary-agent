import { useState } from "react";
import { useNavigate } from "react-router-dom";

import Navbar from "../components/Navbar";
import GlassCard from "../components/GlassCard";
import Button from "../components/Button";
import Loader from "../components/Loader";

import meetingService from "../services/meetingService";

import "../styles/dashboard.css";

function UploadTranscript() {

    const navigate = useNavigate();

    const [file, setFile] = useState(null);

    const [loading, setLoading] = useState(false);

    const [message, setMessage] = useState("");

    const [error, setError] = useState("");

    const [uploadedMeeting, setUploadedMeeting] =
        useState(null);


    const handleFileChange = (event) => {
        const selectedFile =
            event.target.files[0];
        setMessage("");
        setError("");
        setUploadedMeeting(null);

        if (!selectedFile) {
            setFile(null);
            return;
        }

        const fileName =
            selectedFile.name.toLowerCase();
        const allowedExtensions = [
            ".txt",
            ".pdf",
            ".doc",
            ".docx"
        ];
        const isValid =
            allowedExtensions.some(
                (extension) =>
                    fileName.endsWith(extension)
            );
        if (!isValid) {
            setFile(null);
            setError(
                "Only TXT, PDF, DOC and DOCX files are allowed."
            );
            return;
        }
        setFile(selectedFile);
    };


    const uploadFile = async () => {
        setMessage("");
        setError("");
        if (!file) {
            setError(
                "Please select a file first."
            );
            return;
        }

        try {
            setLoading(true);
            const response =
                await meetingService.uploadTranscript(
                    file
                );
            console.log(
                "Upload response:",
                response
            );


        
            setUploadedMeeting(response);
            setMessage(
                "Transcript uploaded successfully."
            );
            setFile(null);
            const fileInput =
                document.getElementById(
                    "transcriptFile"
                );

            if (fileInput) {
                fileInput.value = "";
            }
        }
        catch (error) {
            console.error(
                "Upload error:",
                error
            );

            console.error(
                "Backend error:",
                error.response?.data
            );


            if (
                typeof error.response?.data ===
                "string"
            ) {
                setError(
                    error.response.data
                );
            }
            else if (
                error.response?.data?.message
            ) {
                setError(
                    error.response.data.message
                );
            }
            else {
                setError(
                    "Unable to upload transcript. Please try again."
                );
            }
        }
        finally {
            setLoading(false);

        }
    };


    const viewMeeting = () => {
        if (!uploadedMeeting?.id) {
            setError(
                "Meeting ID not found."
            );
            return;
        }


        navigate(
            `/meeting/${uploadedMeeting.id}`
        );
    };



    return (
        <div className="dashboard-page">
            <Navbar />
            <div
                className="dashboard-container"
                style={{
                    display: "flex",
                    justifyContent: "center"
                }}
            >
                <GlassCard>
                    <div
                        style={{
                            width: "100%",
                            maxWidth: "600px",
                            margin: "0 auto",
                            textAlign: "center"
                        }}
                    >
                        <h1>
                            Upload Meeting Transcript
                        </h1>
                        <p>
                            Upload your meeting transcript
                            in TXT, PDF, DOC or DOCX format.
                        </p>
                        <div
                            style={{
                                marginTop: "25px",
                                display: "flex",
                                justifyContent: "center"
                            }}
                        >
                            <input
                                id="transcriptFile"
                                type="file"
                                accept=".txt,.pdf,.doc,.docx"
                                onChange={handleFileChange}
                            />
                        </div>
                        {file && (
                            <p
                                style={{
                                    marginTop: "15px"
                                }}
                            >
                                Selected file:
                                {" "}
                                <strong>
                                    {file.name}
                                </strong>
                            </p>
                        )}
                        {error && (
                            <p
                                style={{
                                    color: "red",
                                    marginTop: "15px"
                                }}
                            >
                                {error}
                            </p>
                        )}
                        {message && (
                            <p
                                style={{
                                    color: "green",
                                    marginTop: "15px"
                                }}
                            >
                                {message}
                            </p>
                        )}
                        <div
                            style={{
                                marginTop: "25px",
                                display: "flex",
                                justifyContent: "center",
                                alignItems: "center"
                            }}
                        >
                            {loading ? (
                                <Loader
                                    text="Uploading transcript..."
                                />
                            ) : (
                                <Button
                                    onClick={uploadFile}
                                    disabled={!file}
                                >
                                    Upload Transcript
                                </Button>
                            )}
                        </div>
                        {uploadedMeeting && (
                            <div
                                style={{
                                    marginTop: "20px",
                                    display: "flex",
                                    justifyContent: "center"
                                }}
                            >
                                <Button
                                    onClick={viewMeeting}
                                >
                                    View Meeting
                                </Button>
                            </div>
                        )}
                    </div>
                </GlassCard>
            </div>
        </div>
    );
}
export default UploadTranscript;