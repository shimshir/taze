import React from 'react';
import TopNavbar from './nav/topNavbar.js';

const App = ({children}) => {
    return (
        <div className="react-root">
            <TopNavbar/>
            <div className="container">
                {children}
            </div>
        </div>
    )
};

export default App;