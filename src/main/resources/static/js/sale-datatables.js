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
                    id: "editButton",
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

    $("#editButton").click(function () {
        var table_row = table.row(table.$("tr.selected"))

        if (table_row.data() !== undefined) {
            var id = table.row(table.$("tr.selected")).data().id;

            alert("Editar" + id);
        }
    });

    $("#deleteButton").click(function () {
        alert("Excluir");
    });
});