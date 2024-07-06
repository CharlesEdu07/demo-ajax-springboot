$("#form-add-sale").submit(function (event) {
    event.preventDefault();

    var sale = {};

    sale.title = $("#title").val();
    sale.saleLink = $("#saleLink").val();
    sale.site = $("#site").text();
    sale.description = $("#description").val();
    sale.imageLink = $("#imageLink").attr("src");
    sale.price = $("#price").val();
    sale.category = $("#category").val();

    console.log(sale);

    $.ajax({
        method: "POST",
        url: "/sale/save",
        data: sale,
        beforeSend: function () {
            // Remove all error messages
            $("span").closest(".error-span").remove();

            // Remove all is-invalid classes
            $("#category").removeClass("is-invalid");
            $("#price").removeClass("is-invalid");
            $("#saleLink").removeClass("is-invalid");
            $("#title").removeClass("is-invalid");

            // Trigger the loader
            $("#form-add-sale").hide();
            $("#loader-form").addClass("loader").show();
        },
        success: function (data) {
            $("#form-add-sale").each(function () {
                this.reset();
            });
            $("#imageLink").attr("src", "/images/sale-dark.jpg");
            $("#site").text("");
            $("#alert").removeClass("alert alert-danger").addClass("alert alert-success").text("Promoção cadastrada com sucesso.");
        },
        statusCode: {
            422: function (xhr) {
                console.log('> error: ', xhr.responseText);

                var errors = $.parseJSON(xhr.responseText);

                $.each(errors, function (key, value) {
                    $("#" + key).addClass("is-invalid");
                    
                    $("#error-" + key).addClass("invalid-feedback").append("<span class='error-span'>" + value + "</span>");
                })
            }
        },
        error: function (xhr) {
            console.log("> error: ", xhr.responseText)
            $("#alert").addClass("alert alert-danger").text("Não foi possível salvar esta promoção.");
        },
        complete: function () {
            $("#loader-form").fadeOut(800, function () {
                $("#form-add-sale").fadeIn(250);
                $("#loader-form").removeClass("loader");
            })
        }
    })
})

$("#saleLink").on("change", function () {
    var url = $(this).val();

    if (url.length > 7) {
        $.ajax({
            method: "POST",
            url: "/meta/info?url=" + url,
            cache: false,
            beforeSend: function () {
                $("#alert").removeClass("alert alert-danger alert-success").text("");
                $("#title").val("");
                $("#site").text("");
                $("#imageLink").attr("src", "");
                $("#loader-img").addClass("loader");
            },
            success: function (data) {
                console.log(data);

                $("#title").val(data.title);
                $("#site").text(data.site.replace("@", ""));
                $("#imageLink").attr("src", data.image);
            },
            statusCode: {
                404: function () {
                    $("#alert").addClass("alert alert-danger").text("Nenhuma informação pode ser recuperada da URL informada.");
                    $("#imageLink").attr("src", "/images/sale-dark.jpg");
                }
            },
            error: function (data) {
                $("#alert").addClass("alert alert-danger").text("Algo deu errado. Tente novamente mais tarde.");
                $("#imageLink").attr("src", "/images/sale-dark.jpg");
            },
            complete: function () {
                $("#loader-img").removeClass("loader");
            }
        });
    }
});
