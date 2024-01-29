$("#saleLink").on('change', function () {
    var url = $(this).val();

    if (url.length > 7) {
        $.ajax({
            method: "POST",
            url: "meta/info?url=" + url,
            cache: false,
            beforeSend: function () {
                $("#alert").removeClass("alert alert-danger").text("");
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
                    $("#imageLink").attr("src", "/images/promo-dark.jpg");
                }
            },
            error: function (data) {
                $("#alert").addClass("alert alert-danger").text("Algo deu errado. Tente novamente mais tarde.");
                $("#imageLink").attr("src", "/images/promo-dark.jpg");
            },
            complete: function () {
                $("#loader-img").removeClass("loader");
            }
        });
    }
});
