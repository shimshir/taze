import React from "react";

export const LOREM_IPSUM_PARAGRAPHS = () => {
    return (
        <div>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec semper augue vel neque porttitor
                suscipit. Aliquam accumsan dapibus metus vel dictum. Proin auctor quis ligula sit amet mattis. Duis
                porta aliquet nibh sit amet dictum. Nunc a finibus eros. Nulla id augue eros. Donec vel dui a mauris
                rutrum eleifend sed in nisi.</p>

            <p>Suspendisse vulputate massa elit, rutrum condimentum felis fringilla sit amet. Vivamus diam arcu,
                ornare imperdiet condimentum a, elementum ac ante. Mauris varius molestie purus, sed varius
                risus volutpat eget. Duis sagittis erat ex, a pulvinar sapien dapibus id. Aenean sit amet
                consequat dolor. Pellentesque consequat, risus et vulputate hendrerit, velit sapien malesuada
                metus, nec efficitur neque ex eget massa. Sed blandit mi et enim molestie laoreet. Etiam luctus,
                felis quis egestas convallis, nisl est varius nunc, a rhoncus dolor dolor vitae justo.</p>

            <p>Fusce mauris risus, molestie ac viverra ut, placerat non libero. Mauris ex quam, tempor a augue
                vitae, congue malesuada nulla. Suspendisse eu tempor odio. Duis malesuada magna ut dapibus
                facilisis. Curabitur purus sapien, auctor vitae rhoncus sit amet, ornare nec orci. Morbi ut
                augue vel urna tempor tempor ac ac lectus. Ut vel egestas sem. Aenean ante magna, condimentum id
                vehicula id, volutpat vitae urna. Curabitur dictum iaculis hendrerit. Quisque dapibus quis odio
                ut posuere. Nullam sed congue lorem. Praesent eget nisl non libero facilisis bibendum. Praesent
                nec orci eros. Aliquam a purus vel nisl ultrices dignissim vitae sit amet diam.</p>

            <p> In mattis lectus at erat sollicitudin, ac pharetra est tempus. Vestibulum nec lorem nec nulla
                facilisis pulvinar. Aliquam scelerisque, metus pulvinar placerat tincidunt, risus nibh tincidunt
                risus, a posuere sapien urna vitae nunc. Suspendisse quis tortor justo. Nulla pulvinar tempor
                interdum. Nunc nec lectus ex. Phasellus auctor elit id pulvinar hendrerit. Duis ut erat varius,
                tempor libero eget, aliquet augue. Aliquam semper, turpis a pharetra interdum, tortor sem
                commodo nibh, quis mattis mi ipsum non urna. Vivamus dapibus sed mi in lacinia. Quisque interdum
                sem volutpat metus faucibus porttitor. Nullam ante ante, sollicitudin auctor elit ac,
                sollicitudin consectetur purus.</p>

            <p>Vestibulum eu nulla vulputate purus aliquet aliquet. Donec tempor dignissim lacus. Nam ultricies
                mi eget dolor sodales viverra. Aenean rhoncus a nisl id feugiat. Donec vestibulum eros at quam
                fringilla maximus. Nunc efficitur eleifend justo eu imperdiet. Proin mollis, tortor eu interdum
                imperdiet, lorem elit venenatis eros, quis varius arcu magna ac eros. Aenean nulla diam, rutrum
                ut iaculis id, facilisis in lectus. Quisque lobortis non diam eget sollicitudin. Vivamus
                facilisis ante id metus maximus, vitae tristique ante ornare. Etiam nec laoreet nisi. Cras
                efficitur ex vel posuere aliquam.</p>
        </div>
    );
};

export const DELIVERY_OPTIONS = [
    {value: 'COLLECT', text: 'Preuzet cu ja'},
    {value: 'DELIVERY', text: 'Dostavite mi na adresu'}
];

const productionBackendHost = window.location.origin;
const developmentBackendHost = "http://localhost:18081";

const currentBackendHost = productionBackendHost;

export const API_REST_PATH = `${currentBackendHost}/api/rest`;
export const API_ADMIN_PATH = `${currentBackendHost}/api/admin`;
