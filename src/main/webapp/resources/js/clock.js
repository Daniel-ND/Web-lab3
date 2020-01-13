function clock() {
    var date = new Date();
    var hour=date.getHours();
    var minute =date.getMinutes();
    var sec=date.getSeconds();
    document.getElementById('date').innerHTML = formatDate(date);
    document.getElementById('clock').innerHTML = (hour<10?'0':'')+hour+':'+(minute<10?'0':'')+minute+':'+(sec<10?'0':'')+sec;
    window.setTimeout('clock()',12000);
}

function formatDate(date) {
    var dd = date.getDate();
    if (date.getDate()< 10) dd = '0' + dd;

    var mm = date.getMonth() + 1;
    if (mm < 10) mm = '0' + mm;

    var yy = date.getFullYear() % 100;
    if (yy < 10) yy = '0' + yy;

    return dd + '.' + mm + '.' + yy;
}

window.onload = function () {
    clock();
};