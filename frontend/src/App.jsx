import { BrowserRouter, Routes, Route } from "react-router-dom";

import { AuthProvider } from "./context/AuthContext";

import ProtectedRoute from "./components/ProtectedRoute";
 import MeetingHistory from "./pages/MeetingHistory";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";
import Meeting from "./pages/Meeting";
import UploadTranscript from "./pages/UploadTranscript";

import Summary from "./pages/Summary";

import ActionItems from "./pages/ActionItems";

import DashboardStatistics 
from "./pages/DashboardStatistics";



function App() {


    return (


        <AuthProvider>


            <BrowserRouter>


                <Routes>



                    {/* Public Routes */}



                    <Route

                        path="/login"

                        element={<Login />}

                    />


                    <Route

    path="/summary"

    element={

        <ProtectedRoute>

            <Summary />

        </ProtectedRoute>

    }

/>



                    <Route

                        path="/register"

                        element={<Register />}

                    />


                    <Route

path="/statistics"

element={

<ProtectedRoute>

<DashboardStatistics />

</ProtectedRoute>

}

/>





                    {/* Protected Routes */}





                    <Route

                        path="/"

                        element={

                            <ProtectedRoute>

                                <Dashboard />

                            </ProtectedRoute>

                        }

                    />


                    <Route

    path="/actions"

    element={

        <ProtectedRoute>

            <ActionItems />

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





                    <Route

                        path="/upload"

                        element={

                            <ProtectedRoute>

                                <UploadTranscript />

                            </ProtectedRoute>

                        }

                    />

                    <Route

    path="/history"

    element={

        <ProtectedRoute>

            <MeetingHistory />

        </ProtectedRoute>

    }

/>



                    <Route

                        path="/meeting"

                        element={

                            <ProtectedRoute>

                                <Meeting />

                            </ProtectedRoute>

                        }

                    />





                </Routes>



            </BrowserRouter>



        </AuthProvider>


    );

}



export default App;