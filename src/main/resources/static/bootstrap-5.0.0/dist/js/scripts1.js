/*!
* Start Bootstrap - Simple Sidebar v5.2.0 (https://startbootstrap.com/template/simple-sidebar)
* Copyright 2013-2021 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-simple-sidebar/blob/master/LICENSE)
*/
// Toggles the sidebar

$("#menu-toggle").click(function (e) {
    e.preventDefault();
    $("#wrapper").toggleClass("toggled");
});
function createPDF() {
    var dt = new Date();
    var sTable = document.getElementById('printme').innerHTML;
    var style = "<style>";
    style = style + "table {width: 100%;font: 17px Calibri;}";
    style = style + "table, th, td {border: solid 1px #DDD; border-collapse: collapse;";
    style = style + "padding: 2px 3px;text-align: center;}";
    style = style + "</style>";

    // CREATE A WINDOW OBJECT.
    var win = window.open('', '', 'height=700,width=700');

    win.document.write('<html><head>');
    win.document.write('<title></title>'); // <title> FOR PDF HEADER.
    win.document.write(style); // ADD STYLE INSIDE THE HEAD TAG.
    win.document.write('</head>');
    win.document.write('<body>');
    win.document.write("<div style='line-height: 0.3;'><h4 style='text-align: center;'>Cộng hòa xã hội chủ nghĩa Việt Nam</h4><h4 style='text-align: center;'>Độc lập - Tự do - Hạnh phúc</h4></div>");
    win.document.write("<p style='text-align: right; padding-right: 50px;''>Đà Nẵng, ngày " + dt.getDate().toLocaleString() + " tháng " + (dt.getMonth() + 1).toLocaleString() + " năm 2021</p><h1 style='text-align: center;''>Bảng báo cáo</h1>")
    win.document.write(sTable); // THE TABLE CONTENTS INSIDE THE BODY TAG.
    win.document.write("<p style='text-align: right; padding-right: 150px;'>Chữ ký</p>");
    win.document.write('</body></html>');
    win.document.close(); 
    win.print();
}