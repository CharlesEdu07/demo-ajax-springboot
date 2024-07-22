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

var totalSales = new Number(0);

function init() {
    const evtSource = new EventSource("/sale/notification");

    evtSource.onopen = (event) => {
        console.log("The connection has been estabilished.");
    };

    evtSource.onmessage = (event) => {
        const count = event.data;

        if (count > 0) {
            showButton(count);
        }
    };
}

function showButton(count) {
    totalSales = totalSales + new Number(count);

    $("#btn-alert").show(function () {
        $(this).attr("style", "display: block;").text("Veja " + totalSales + " novas(s) oferta(s)!");
    })
}

$("#btn-alert").click(function () {
    $.ajax({
        method: "GET",
        url: "/sale/list/ajax",
        data: {
            page: 0,
            site: ''
        },
        beforeSend: function () {
            pageNumber = 0;
            totalSales = 0;

            $("#end-btn").hide();
            $("#loader-img").addClass("loader");
            $("#btn-alert").attr("style", "display: none");

            $(".row").fadeOut(400, function () {
                $(this).empty();
            });
        },
        success: function (response, status, xhr) {
            $("#loader-img").hide();

            $(".row").fadeIn(250, function () {
                $(this).append(response);
            });
        },
        error: function (error) {
            console.log("error: ", error)
        }
    });
});