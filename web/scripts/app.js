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

    if (screenid == 'WELCOME') {
        frm.submit();
    } else if (screenid == 'SEARCH_ONTOLOGY') {
        var query = '';
        query = getListItems('listID');
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
    frm.submit()
}