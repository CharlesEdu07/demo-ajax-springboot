var pageNumber = 0;

$(document).ready(function () {
    $("#loader-img").hide();
    $("#end-btn").hide();
})

// Infinite scroll effect
$(window).scroll(function () {
    var scrollTop = $(this).scrollTop();
    var content = $(document).height() - $(window).height();

    if (scrollTop >= content) {
        pageNumber++;

        setTimeout(function () {
            loadByScrollBar(pageNumber);
        }, 200);
    }
});

function loadByScrollBar(pageNumber) {
    var site = $("#autocomplete-input").val();

    $.ajax({
        method: "GET",
        url: "/sale/list/ajax",
        data: {
            page: pageNumber,
            site: site,
        },
        beforeSend: function () {
            $("#loader-img").show();
        },
        success: function (response) {
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

// Autocomplete
$("#autocomplete-input").autocomplete({
    source: function (request, response) {
        $.ajax({
            method: "GET",
            url: "/sale/site",
            data: {
                term: request.term,
            },
            success: function (result) {
                response(result);
            }
        });
    }
});

$("#autocomplete-submit").on("click", function () {
    var site = $("#autocomplete-input").val();

    $.ajax({
        method: "GET",
        url: "/sale/site/list",
        data: {
            site: site,
        },
        beforeSend: function () {
            pageNumber = 0;

            $("#end-btn").hide();
            $(".row").fadeOut(400, function () {
                $(this).empty();
            });
        },
        success: function (response) {
            $(".row").fadeIn(250, function () {
                $(this).append(response);
            });
        },
        error: function (xhr) {
            alert("Ops! Ocorreu um erro: " + xhr.status + " - " + xhr.statusText);
        }
    });
});

// Adicionar likes
$(document).on("click", "button[id*='likes-btn-']", function () {
    var saleId = $(this).attr("id").split("-")[2];

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

// SSE
window.onload = init();

function init() {
    const evtSource = new EventSource("/sale/notification");

    evtSource.onopen = (event) => {
        console.log("The connection has been estabilished.");
    }

    evtSource.onmessage = (event) => {
        console.log("New message", event.data);
    }
}