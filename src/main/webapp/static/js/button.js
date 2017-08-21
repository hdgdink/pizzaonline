var el = document.getElementById('myTable');

function addRowBottom() {
    var cols_cnt = el.rows[el.rows.length-1].cells.length;
    var row = el.insertRow(-1);

    for (var i=0; i < cols_cnt; i++) {
        var NewCell = row.insertCell(-1);
        NewCell.innerHTML = 'Text on bottom ' + (i+1).toString();
    }
}



