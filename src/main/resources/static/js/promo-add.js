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
        });
    }
});
