/*
    Document   : app.js
    Created on : Dec 22, 2010, 10:05:33 PM
    Author     : danhit
    Description: Javascript.
*/

/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function getListItems(listID) {
	var listOf = '';
	for(var c = 0; c < document.getElementById(listID).options.length; c++) {
		listOf = listOf + document.getElementById(listID).options[c].value;
		if (c < (document.getElementById(listID).options.length - 1))
			listOf = listOf + "-";
	}
	return listOf;
}

function submitForm(frm, screenid, processid) {
    frm.screenid.value = screenid;
    frm.processid.value = processid;

    showLoadingPage();

    if (screenid == 'WELCOME') {
        frm.submit();
    } else if (screenid == 'SEARCH_ONTOLOGY') {
        var query = '';
        query = getListItems('listID03');
        if (query == '') {
            alert ('Query is empty!');
        } else if((!frm.yahoo.checked)&&(!frm.google.checked)) {
            alert ("You have not selected search engine!");
        } else {
            frm.query_string.value = query;
            frm.submit();
        }
    } else if (screenid == 'DISPLAY_LINKS') {
        frm.submit();
    } else if (screenid == 'IT_DOCUMENT') {
        frm.submit();
    } else if (screenid == 'UPDATE_ONTOLOGY') {
        frm.submit();
    } else if (screenid == 'RESULT') {
        frm.submit();
    }
}

function submit(frm) {
    showLoadingPage();
    frm.submit()
}

function hideLoadingPage() {
    if (document.getElementById) { // DOM3 = IE5, NS6
         document.getElementById('hidepage').style.visibility = 'hidden';
    } else {
        if (document.layers) { // Netscape 4
             document.hidepage.visibility = 'hidden';
        } else { // IE 4
            document.all.hidepage.style.visibility = 'hidden';
        }
    }
}

function showLoadingPage() {
    if (document.getElementById) { // DOM3 = IE5, NS6
         document.getElementById('hidepage').style.visibility = 'visible';
    } else {
        if (document.layers) { // Netscape 4
             document.hidepage.visibility = 'show';
        } else { // IE 4
            document.all.hidepage.style.visibility = 'visible';
        }
    }
}

function getIndividuals(frm, process, content) {
    frm.process.value = process;
    frm.content.value = content;
    frm.submit();
}

function test() {
    alert("success");
}

function checkString(listID, content) {
    var listOf = '';
    for(var c = 0; c < document.getElementById(listID).options.length; c++) {
        listOf = document.getElementById(listID).options[c].value;
        if (content.value == listOf.value) {
            return true;
        }
    }
    return false;
}

function chose(textID, classlistID, listID) {
    var content = document.getElementById(textID).value;
    if (content == "") {
        alert("Bạn cần nhập tên lớp được chọn");
    } else {
        var isExist = checkString(classlistID, content);
        if (isExist == true) {
            addConcept(content, listID);
        } else {
            alert("Tên lớp này không tồn tại");
        }
    }

}