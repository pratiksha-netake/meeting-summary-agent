import { Link } from "react-router-dom";

import Navbar from "../components/Navbar";
import GlassCard from "../components/GlassCard";

import "../styles/dashboard.css";



function Dashboard(){

return (

    <div className="dashboard-page">

        <Navbar />
        <div className="dashboard-container">
            <h1>

                Meeting Summary Agent
            </h1>
            <p className="dashboard-subtitle">
                Manage your meetings, summaries and action items
            </p>
            <div className="dashboard-grid">
                <GlassCard>
                    <h2>

                        Upload Transcript
                    </h2>
                    <p>
                        Upload meeting transcript files (TXT, DOCX, PDF)
                    </p>
                    <Link to="/upload">
                        Upload Transcript
                    </Link>
                </GlassCard>
              
                <GlassCard>
                    <h2>
                        Manual Meeting Notes
                    </h2>
                    <p>
                        Add meeting details manually and create notes
                    </p>
                    <Link to="/meeting">
                        Add Meeting Notes
                    </Link>

                </GlassCard>

              

                <GlassCard>

                    <h2>
                        Meeting History
                    </h2>
                    <p>
                        View previous meetings and saved summaries
                    </p>

                    <Link to="/history">
                        View History
                    </Link>
                </GlassCard>
              
                <GlassCard>
                    <h2>
                        AI Summary
                    </h2>
                    <p>
                        Generate and download meeting summaries
                    </p>
                    <Link to="/summary">
                        Open Summary
                    </Link>
                </GlassCard>

          

                <GlassCard>

                    <h2>
                        Action Items
                    </h2>

                    <p>
                        Assign tasks and update task status
                    </p>
                    <Link to="/actions">
                        Manage Actions
                    </Link>
                </GlassCard>
            </div>
        </div>
    </div>
);
}
export default Dashboard;