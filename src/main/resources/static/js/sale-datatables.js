$(document).ready(function () {
    moment.locale("pt-br");
    $("#table-server").DataTable({
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
                    class: "btn btn-primary"
                },
            },
            {
                text: "Excluir",
                attr: {
                    id: "deleteButton",
                    class: "btn btn-danger"
                },
            }
        ]
    });

    $("#table-server tbody").on("click", "tr", function () {
        if ($(this).hasClass("selected")) {
            $(this).removeClass("selected");
        } else {
            $("tr.selected").removeClass("selected");

            $(this).addClass("selected");
        }
    });

    $("#editButton").click(function () {
        alert("Editar");
    });

    $("#deleteButton").click(function () {
        alert("Excluir");
    });
});