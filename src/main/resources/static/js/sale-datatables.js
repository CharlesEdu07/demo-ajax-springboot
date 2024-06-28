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
        ]
    });
});