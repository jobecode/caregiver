import { useState } from 'react';
import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import { Modal } from './Modal';
import './CalendarView.css';


const CalendarView = () => {
    const [selectedDate, setSelectedDate] = useState(null);
    const [comments, setComments] = useState({});
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [events, setEvents] = useState([]);

    const handleDateClick = (info) => {
        setSelectedDate(info.dateStr);
        setIsModalOpen(true);

        setEvents([
            {
                title: '📝',
                start: info.dateStr,
                display: 'background',
                backgroundColor: '#FFD700', // Amarillo dorado
            },
        ])
    };

    const handleCommentChange = (e) => {
        setComments({ ...comments, [selectedDate]: e.target.value });
    };

    const saveObservation = (text) => {
        setObservations((prev) => ({
            ...prev,
            [selectedDate]: text,
        }));
        setSelectedDate(null); // Close the modal after saving
    };

    const renderEventContent = (eventInfo) => {
        const isSelected = eventInfo.dateStr === selectedDate;
        return (
            <div className={isSelected ? 'selected-day' : ''}>
                {eventInfo.dayNumberText}
            </div>
        );
    };

    return (
        <div className="calendar-container">
            <h1 className="calendar-title">Calendario de Cuidadores</h1>

            <FullCalendar
                plugins={[dayGridPlugin, interactionPlugin]}
                initialView="dayGridMonth"
                dateClick={handleDateClick}
                selectable={true}
                locale="es"
                dayCellContent={renderEventContent}
                events={events}
                firstDay={1}
            />

            {selectedDate && (
                <div className="comment-modal">
                    <h2>Observaciones para {selectedDate}</h2>
                    <textarea
                        value={comments[selectedDate] || ''}
                        onChange={handleCommentChange}
                        placeholder=""
                    />
                </div>
            )}
        </div>


    );
};


export default CalendarView;