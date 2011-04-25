/*
    Document   : tree.js
    Created on : Dec 22, 2010, 10:05:33 PM
    Author     : danhit
    Description: Javascript.
*/

/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function clickNode(frm, li_id, screenid, processid) {
    frm.li_id.value = li_id;
    submitForm(frm, screenid, processid);
}

function changeTree(liID) {
    var li = document.getElementById(liID);
    if (li.className == 'liClosed')
        document.getElementById(liID).className = 'liOpen';
    else
        document.getElementById(liID).className = 'liClosed';
}