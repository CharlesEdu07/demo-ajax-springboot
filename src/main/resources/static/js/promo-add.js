$("#saleLink").on('change', function () {
    var url = $(this).val();

    if (url.length > 7) {
        $.ajax({
            method: "POST",
            url: "meta/info?url=" + url,
            cache: false,
            success: function (data) {
                console.log(data);

                $("#title").val(data.title);
                $("#site").text(data.site.replace("@", ""));
                $("#imageLink").attr("src", data.image);
            },
            statusCode: {
                404: function () {
                    $("#alert").addClass("alert alert-danger").text("Nenhuma informação pode ser recuperada da URL informada.");
                }
            },
            error: function (data) {
                $("#alert").addClass("alert alert-danger").text("Algo deu errado. Tente novamente mais tarde.");
            }
        });
    }
});
