import { useState } from "react";

import Navbar from "../components/Navbar";
import GlassCard from "../components/GlassCard";
import Button from "../components/Button";
import Loader from "../components/Loader";

import actionItemService from "../services/actionItemService";

import "../styles/dashboard.css";

function ActionItems(){

    const [formData,setFormData] = useState({
        task:"",
        assignedTo:"",
        meetingId:""
    });
    const [actionItem,setActionItem] = useState(null);
    const [status,setStatus] = useState("");
    const [loading,setLoading] = useState(false);
    const handleChange=(e)=>{
        setFormData({
            ...formData,
            [e.target.name]:e.target.value
        });

    };

    const assignItem = async()=>{
        try{
            setLoading(true);

            const payload = {

                task:formData.task,
                assignedTo:formData.assignedTo,
                meetingId:Number(formData.meetingId)
            };
            const response =
                await actionItemService.assignActionItem(
                    payload
                );
            setActionItem(response);

        }
        catch(error){
            console.log(
                error.response?.data || error
            );
        }
        finally{
            setLoading(false);
        }
    };

    const updateStatus = async()=>{
        if(!actionItem || !status){
            return;
        }

        try{
            setLoading(true);
            const response =
                await actionItemService.updateActionItemStatus(
                    actionItem.id,
                    status
                );

            setActionItem(response);
        }
        catch(error){
            console.log(
                error.response?.data || error
            );
        }

        finally{
            setLoading(false);
        }
    };
    return (

        <div className="dashboard-page">

            <Navbar />
            <div className="dashboard-container">

                <GlassCard>
                    <h1>

                        Assign Action Item
                    </h1>
                    <input

                        name="task"
                        placeholder="Task"
                        value={formData.task}
                        onChange={handleChange}
                    />

                    <input

                        name="assignedTo"
                        placeholder="Assigned To"
                        value={formData.assignedTo}
                        onChange={handleChange}
                    />
                    <input
                        name="meetingId"
                        type="number"
                        placeholder="Meeting ID"
                        value={formData.meetingId}
                        onChange={handleChange}

                    />

                    {
                    loading ?
                    <Loader text="Saving..." />
                    :

                    <Button onClick={assignItem}>
                        Assign
                    </Button>
                    }
                </GlassCard>
                {
                actionItem &&
                <GlassCard>
                    <h2>
                        Action Item Details
                    </h2>
                    <p>
                        <b>
                            Task:
                        </b>
                        {" "}
                        {actionItem.task}
                    </p>
                    <p>
                        <b>
                            Assigned To:
                        </b>
                        {" "}
                        {actionItem.assignedTo}
                    </p>
                    <p>
                        <b>
                            Status:
                        </b>
                        {" "}
                        {actionItem.status}
                    </p>
                    <select
                        value={status}
                        onChange={(e)=>
                            setStatus(e.target.value)
                        }
                    >
                        <option value="">
                            Select Status
                        </option>
                        <option value="PENDING">
                            Pending
                        </option>
                        <option value="IN_PROGRESS">
                            In Progress
                        </option>
                        <option value="COMPLETED">
                            Completed
                        </option>
                    </select>
                    <Button onClick={updateStatus}>
                        Update Status
                    </Button>
                </GlassCard>
                }
            </div>
        </div>
    );
}
export default ActionItems;