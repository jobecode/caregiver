// src/components/Tabs.jsx
import { useState } from "react";

export function Tabs({ children }) {
    const [activeIndex, setActiveIndex] = useState(0);

    return (
        <div>
            <div className="flex space-x-4 border-b">
                {children.map((child, index) => (
                    <button
                        key={index}
                        className={`p-2 ${
                            activeIndex === index ? "border-b-2 border-blue-500 font-bold" : ""
                        }`}
                        onClick={() => setActiveIndex(index)}
                    >
                        {child.props.label}
                    </button>
                ))}
            </div>
            <div className="p-4">{children[activeIndex]}</div>
        </div>
    );
}

export function Tab({ children }) {
    return <>{children}</>;
}
