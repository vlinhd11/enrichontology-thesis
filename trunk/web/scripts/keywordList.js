/*
    Document   : keywordList.js
    Created on : Dec 22, 2010, 10:05:33 PM
    Author     : danhit
    Description: Javascript.
*/

/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function isSelected(term, listID){
  var list = document.getElementById(listID);
  var numberOfItems = list.options.length;
  for (var i = 0; i < numberOfItems; i++) {
	if (list.options[i].text == term)
		return true;
  }
  return false;
}

function addConcept(concept, listID) {
    if (document.getElementById(listID).options.length == 4) {
        alert("You can choose 4 keywords one time");
    } else {
        var newItem = document.createElement('option');
	newItem.innerHTML = concept;
	newItem.value = concept;
	if (isSelected(concept, listID) == true) {
		alert("This concept already exists in the query!");
	} else {
		document.getElementById(listID).appendChild(newItem);
	}
    }
}

function removeConcept(listID) {
	var list = document.getElementById('listID');
	var numberOfItems = list.options.length;
	for(var i = numberOfItems - 1; i >= 0; i--) {
		if ((list.options[i] != null) && (list.options[i].selected == true)) {
			list.options[i] = null;
		}
	}
}

function removeAll(listID) {
	var list = document.getElementById('listID');
	var numberOfItems = list.options.length;
	for(var i = numberOfItems - 1; i >= 0; i--) {
		if ((list.options[i] != null)) {
			list.options[i] = null;
		}
	}
}