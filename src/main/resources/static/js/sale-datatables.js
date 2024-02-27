$(document).ready(function () {
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
            { data: "price" },
            { data: "likes" },
            { data: "registerDate" },
            { data: "category" }
        ]
    });
});