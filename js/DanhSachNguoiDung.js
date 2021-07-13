var table;
function initTableData() {
    var data = [
        {
            "ID": "NghiaPhma11",
            "TaiKhoan": "Nghia1",
            "MatKhau": "nghiaphma11@gmail.com",
        },
        {
            "ID": "NghiaPhma11",
            "TaiKhoan": "Nghia2",
            "MatKhau": "nghiaphma11@gmail.com",
        },
        {
            "ID": "NghiaPhma11",
            "TaiKhoan": "Nghia3",
            "MatKhau": "nghiaphma11@gmail.com",
        }
    ];
    // table = $('#tb-NghienCuuSinh').DataTable({
    //     "proccessing": true,
    //     data, 
    //     columns: [
    //         {data: 'FirstName'},
    //         {data: 'Name'},
    //         {data: 'Email'}
    //     ]

    // });
}
$(document).ready(function () {
    initTableData()
    $("#list-NghienCuuSinh").on({
        mouseenter: function () {
            $(this).css("background-color", "lightgray");
        },
        mouseleave: function () {
            $(this).css("background-color", "lightblue");
        },
    });
});
const searchFocus = document.getElementById('search-focus');
const keys = [
  { keyCode: 'AltLeft', isTriggered: false },
  { keyCode: 'ControlLeft', isTriggered: false },
];

window.addEventListener('keydown', (e) => {
  keys.forEach((obj) => {
    if (obj.keyCode === e.code) {
      obj.isTriggered = true;
    }
  });

  const shortcutTriggered = keys.filter((obj) => obj.isTriggered).length === keys.length;

  if (shortcutTriggered) {
    searchFocus.focus();
  }
});

window.addEventListener('keyup', (e) => {
  keys.forEach((obj) => {
    if (obj.keyCode === e.code) {
      obj.isTriggered = false;
    }
  });
});