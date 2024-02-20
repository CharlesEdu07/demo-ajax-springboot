var pageNumber = 0;

$(document).ready(function () {
    $("#loader-img").hide();
    $("#end-btn").hide();
})

// Infinite scroll effect
$(window).scroll(function () {
    var scrollTop = $(this).scrollTop();

    var content = $(document).height() - $(window).height();

    // console.log('scrollTop: ', scrollTop);
    // console.log('content: ', content);

    if (scrollTop >= content) {
        pageNumber++;

        setTimeout(function () {
            loadByScrollBar(pageNumber);
        }, 200);
    }
});

function loadByScrollBar(pageNumber) {
    $.ajax({
        method: "GET",
        url: "/sale/list/ajax",
        data: {
            page: pageNumber
        },
        beforeSend: function () {
            $("#loader-img").show();
        },
        success: function (response) {
            // console.log("response: ", response);

            if (response.length > 150) {
                $(".row").fadeIn(250, function () {
                    $(this).append(response);
                });
            } else {
                $("#end-btn").show();
                $("#loader-img").removeClass("loader");
            }
        },
        error: function (xhr) {
            alert("Ops! Ocorreu um erro: " + xhr.status + " - " + xhr.statusText);
        },
        complete: function () {
            $("#loader-img").hide();
        }
    })
}

//Adicionar likes
$(document).on("click", "button[id*='likes-btn-']", function () {
    var saleId = $(this).attr("id").split("-")[2];

    console.log("saleId: ", saleId);

    console.log("/sale/like/" + saleId);

    $.ajax({
        method: "POST",
        url: "/sale/like/" + saleId,
        success: function (response) {
            $("#likes-count-" + saleId).text(response);
        },
        error: function (xhr) {
            alert("Ops! Ocorreu um erro: " + xhr.status + " - " + xhr.statusText);
        }
    });
});