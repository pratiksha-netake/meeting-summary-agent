import { BrowserRouter, Routes, Route } from "react-router-dom";

import { AuthProvider } from "./context/AuthContext";
import ProtectedRoute from "./components/ProtectedRoute";

import Login from "./pages/Login";
import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";
import Meeting from "./pages/Meeting";
import UploadTranscript from "./pages/UploadTranscript";
import MeetingHistory from "./pages/MeetingHistory";
import ViewMeeting from "./pages/ViewMeeting";
import Summary from "./pages/Summary";
import ActionItems from "./pages/ActionItems";
import DashboardStatistics from "./pages/DashboardStatistics";


function App() {

    return (

        <AuthProvider>

            <BrowserRouter>

                <Routes>

                    {/* =========================
                        PUBLIC ROUTES
                    ========================= */}

                    <Route
                        path="/login"
                        element={<Login />}
                    />

                    <Route
                        path="/register"
                        element={<Register />}
                    />


                    {/* =========================
                        PROTECTED ROUTES
                    ========================= */}

                    <Route
                        path="/"
                        element={
                            <ProtectedRoute>
                                <Dashboard />
                            </ProtectedRoute>
                        }
                    />


                    <Route
                        path="/dashboard"
                        element={
                            <ProtectedRoute>
                                <Dashboard />
                            </ProtectedRoute>
                        }
                    />


                    {/* Manual Meeting */}

                    <Route
                        path="/meeting"
                        element={
                            <ProtectedRoute>
                                <Meeting />
                            </ProtectedRoute>
                        }
                    />


                    {/* Upload Transcript */}

                    <Route
                        path="/upload"
                        element={
                            <ProtectedRoute>
                                <UploadTranscript />
                            </ProtectedRoute>
                        }
                    />


                    {/* Meeting History */}

                    <Route
                        path="/history"
                        element={
                            <ProtectedRoute>
                                <MeetingHistory />
                            </ProtectedRoute>
                        }
                    />


                    {/* View Single Meeting */}

                    <Route
                        path="/meeting/:id"
                        element={
                            <ProtectedRoute>
                                <ViewMeeting />
                            </ProtectedRoute>
                        }
                    />


                    {/* Summary */}

                    <Route
                        path="/summary"
                        element={
                            <ProtectedRoute>
                                <Summary />
                            </ProtectedRoute>
                        }
                    />


                    {/* Action Items */}

                    <Route
                        path="/actions"
                        element={
                            <ProtectedRoute>
                                <ActionItems />
                            </ProtectedRoute>
                        }
                    />


                    {/* Statistics */}

                    <Route
                        path="/statistics"
                        element={
                            <ProtectedRoute>
                                <DashboardStatistics />
                            </ProtectedRoute>
                        }
                    />

                </Routes>

            </BrowserRouter>

        </AuthProvider>

    );
}


export default App;