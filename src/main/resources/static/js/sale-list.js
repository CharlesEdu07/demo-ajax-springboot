// Infinite scroll effect
$(window).scroll(function () {
    var scrollTop = $(this).scrollTop();

    var content = $(document).height() - $(window).height();

    console.log('scrollTop: ', scrollTop);
    console.log('content: ', content);

    if (scrollTop >= content) {
        console.log('Carregar mais promoções');
    }
});