/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var AJAX;

function showIndividuals(content, listID) {
    addConcept("Xin vui lòng chờ ...", listID);

    //initial AJAX
    try {
        var _IE = false;
        var x = new DOMParser();
    } catch(e) {
        var _IE = true;
    };
    if(_IE) {
        AJAX = new ActiveObject("Microsoft.XMLHTTP");
    } else {
        AJAX = new XMLHttpRequest();
    }
    
    try {
        AJAX.onreadystatechange = function() {
            if(AJAX.readyState == 4) {
                if(AJAX.status == 200) {
                    document.getElementById("individual_ID").innerHTML = AJAX.responseText;
                } else {
                    alert("AJAX.status = 404");
                }
            }
        }
        AJAX.open("GET", "http://localhost:8080/EnrichOntology/OntologyAction.do?content=" + content + "&&processid=ONTOLOGY_01", true);
        AJAX.send(null);
    } catch (err){
        alert(err);
    }
}

function test() {
    alert("danhit");
}

function showValues(individual_name, listID) {
    addConcept("Xin vui lòng chờ ...", listID);

    //initial AJAX
    try {
        var _IE = false;
        var x = new DOMParser();
    } catch(e) {
        var _IE = true;
    };
    if(_IE) {
        AJAX = new ActiveObject("Microsoft.XMLHTTP");
    } else {
        AJAX = new XMLHttpRequest();
    }

    try {
        AJAX.onreadystatechange = function() {
            if(AJAX.readyState == 4) {
                if(AJAX.status == 200) {
                    document.getElementById("values_ID").innerHTML = AJAX.responseText;
                } else {
                    alert("AJAX.status = 404");
                }
            }
        }
        AJAX.open("GET", "http://localhost:8080/EnrichOntology/OntologyAction.do?individual_name=" + individual_name + "&&processid=ONTOLOGY_02", true);
        AJAX.send(null);
    } catch (err){
        alert(err);
    }
}

