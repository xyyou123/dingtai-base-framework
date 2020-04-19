javascript:(function () {
        window.scrollY = 1000000;
        alert(window.scrollY);
        var evt = window.document.createEvent('UIEvents');
        evt.initUIEvent('scroll', true, false, window, 0);
        window.dispatchEvent(evt);
})()