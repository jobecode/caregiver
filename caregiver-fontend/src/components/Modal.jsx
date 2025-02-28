import React, { useState } from 'react';

export const Modal = ({ date, initialText, onSave, onClose }) => {
    const [text, setText] = useState(initialText);

    const handleSave = () => {
        onSave(text);
    };

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <h2>Observaciones para {date}</h2>
                <textarea
                    value={text}
                    onChange={(e) => setText(e.target.value)}
                />
                <button onClick={handleSave}>Guardar</button>
                <button onClick={onClose}>Cerrar</button>
            </div>
        </div>
    );
};

