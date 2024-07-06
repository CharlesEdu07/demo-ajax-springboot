$(document).ready(function () {
    moment.locale("pt-br");
    var table = $("#table-server").DataTable({
        processing: true,
        serverSide: true,
        responsive: true,
        lengthMenu: [10, 15, 20, 25],
        ajax: {
            url: "/sale/datatables/server",
            data: "data",
        },
        columns: [
            { data: "id" },
            { data: "title" },
            { data: "site" },
            { data: "saleLink" },
            { data: "description" },
            { data: "imageLink" },
            { data: "price", render: $.fn.dataTable.render.number(".", ",", 2, "R$") },
            { data: "likes" },
            { data: "registerDate", render: function (data) { return moment(data).format("LLL"); } },
            { data: "category.title" }
        ],
        dom: 'Bfrtip',
        buttons: [
            {
                text: "Editar",
                attr: {
                    id: "updateButton",
                    type: "button",
                },
                enabled: false
            },
            {
                text: "Excluir",
                attr: {
                    id: "deleteButton",
                    type: "button",
                },
                enabled: false
            }
        ]
    });

    $("#table-server thead").on("click", "tr", function () {
        table.buttons().disable();
    });

    $("#table-server tbody").on("click", "tr", function () {
        if ($(this).hasClass("selected")) {
            $(this).removeClass("selected");

            table.buttons().disable();
        } else {
            $("tr.selected").removeClass("selected");

            $(this).addClass("selected");

            table.buttons().enable();
        }
    });

    $("#updateButton").click(function () {
        if (isSelectedRow()) {
            var id = getSaleId();

            $.ajax({
                method: "GET",
                url: "/sale/update/" + id,
                beforeSend: function () {
                    $("#modal-form").modal("show");
                },
                success: function (data) {
                    $("#updt_id").val(data.id);
                    $("#updt_site").text(data.site);
                    $("#updt_title").val(data.title);
                    $("#updt_description").val(data.description);
                    $("#updt_price").val(data.price.toLocaleString("pt-BR", {
                        minimumFractionDigits: 2,
                        maximumFractionDigits: 2
                    }));
                    $("#updt_category").val(data.category.id);
                    $("#updt_imageLink").val(data.imageLink);
                    $("#updt_image").attr("src", data.imageLink);
                },
                error: function (xhr) {
                    alert("Ops! Ocorreu um erro: " + xhr.status + " - " + xhr.statusText);
                }
            });
        }
    });

    $("#btn-update-modal").on("click", function () {
        var sale = {};

        debugger;

        sale.id = $("#updt_id").val();
        sale.title = $("#updt_title").val();
        sale.description = $("#updt_description").val();
        sale.imageLink = $("#updt_imageLink").val();
        sale.price = parseFloat($("#updt_price").val().replace(",", "."));
        sale.category = $("#updt_category").val();

        $.ajax({
            method: "PUT",
            url: "/sale/update",
            data: sale,
            success: function () {
                $("#modal-form").modal("hide");

                table.ajax.reload();
            },
            statusCode: {
                422: function (xhr) {
                    console.log('> error: ', xhr.responseText);

                    var errors = $.parseJSON(xhr.responseText);

                    $.each(errors, function (key, value) {
                        $("#updt_" + key).addClass("is-invalid");

                        $("#error-" + key).addClass("invalid-feedback").append("<span class='error-span'>" + value + "</span>");
                    })
                }
            },
        });
    });

    $("#updt_imageLink").on("change", function () {
        var link = $(this).val();

        $("#updt_image").attr("src", link);
    });

    $("#deleteButton").click(function () {
        if (isSelectedRow()) {
            $("#modal-delete").modal("show");
        }
    });

    $("#btn-del-modal").on("click", function () {
        var id = getSaleId();

        $.ajax({
            method: "DELETE",
            url: "/sale/delete/" + id,
            success: function () {
                $("#modal-delete").modal("hide");

                table.ajax.reload();
            },
            error: function (xhr) {
                alert("Ops! Ocorreu um erro: " + xhr.status + " - " + xhr.statusText);
            }
        })
    });

    function getSaleId() {
        return table.row(table.$("tr.selected")).data().id;
    }

    function isSelectedRow() {
        var table_row = table.row(table.$("tr.selected"));

        return table_row.data() !== undefined;
    }
});