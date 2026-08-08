import { useEffect, useState } from "react";

import Navbar from "../components/Navbar";
import GlassCard from "../components/GlassCard";
import Loader from "../components/Loader";

import dashboardStatisticsService
    from "../services/dashboardStatisticsService";

import "../styles/dashboard.css";


function DashboardStatistics() {

    const [stats, setStats] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    useEffect(() => {
        loadStatistics();
    }, []);

    const loadStatistics = async () => {
        try {
            setLoading(true);
            setError("");
            const data =
                await dashboardStatisticsService
                    .getStatistics();
            setStats(data);
        }
        catch (error) {
            console.error(
                "Dashboard Statistics Error:",
                error
            );
            setError(
                error.response?.data ||
                "Unable to load dashboard statistics."
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
                <h1>
                    Meeting Statistics
                </h1>              
                {error && (
                    <GlassCard>
                        <p>
                            {error}
                        </p>
                    </GlassCard>

                )}
                {loading && (

                    <Loader
                        text="Loading statistics..."
                    />

                )}
                {!loading && stats && (

                    <>

                        <GlassCard>
                            <h2>
                                Total Meetings
                            </h2>
                            <h1>
                                {stats.totalMeetings}
                            </h1>
                        </GlassCard>

                        <GlassCard>
                            <h2>
                                Summaries Generated
                            </h2>
                            <h1>
                                {stats.totalSummaries}
                            </h1>
                        </GlassCard>
                        <GlassCard>
                            <h2>
                                Action Items
                            </h2>
                            <h1>
                                {stats.totalActionItems}
                            </h1>
                        </GlassCard>
                        <GlassCard>
                            <h2>
                                Pending Actions
                            </h2>
                            <h1>
                                {stats.pendingActions}
                            </h1>
                        </GlassCard>


                        <GlassCard>
                            <h2>
                                Completed Actions
                            </h2>
                            <h1>
                                {stats.completedActions}
                            </h1>
                        </GlassCard>

                        <GlassCard>
                            <h2>
                                Meetings Last 7 Days
                            </h2>

                            <h1>
                                {stats.recentMeetings}
                            </h1>
                        </GlassCard>
                    </>
                )}
            </div>
        </div>
    );
}
export default DashboardStatistics;