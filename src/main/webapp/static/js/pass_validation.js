function checkForm(frm) {

    if ((document.getElementById("pass1").value) != (document.getElementById("pass2").value))  {
        document.getElementById("msg").style.display = "inline";
    }

    else {
        document.getElementById("msg").style.display = "none";
    }
}