import { useState } from "react";
import CalendarView from "./components/CalendarView";
import {CaregiverForm} from "./components/CaregiverForm";
import { Tabs, Tab } from "@/components/tabs";

export default function App() {
    const [activeTab, setActiveTab] = useState("calendar");

    return (
        <div className="min-h-screen bg-gray-100 p-4">
            <Tabs activeTab={activeTab} onChange={setActiveTab}>
                <Tab id="calendar" label="Calendario">
                    <CalendarView />
                </Tab>
                <Tab id="caregivers" label="Añadir Cuidador">
                    <CaregiverForm />
                </Tab>
            </Tabs>
        </div>
    );
}
