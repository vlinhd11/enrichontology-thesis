package com.tkorg.search;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.tkorg.entities.MyStack;
import com.tkorg.entities.OWLModel;

import com.tkorg.util.Global;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.RDFSClass;
import edu.stanford.smi.protegex.owl.model.impl.DefaultRDFSNamedClass;
import java.util.HashSet;
import java.util.Set;

public class ClassActions {
        private static String temp = "";

	public static void viewClasses() {

		MyStack myStack = new MyStack();
		myStack.init();
		OWLNamedClass owlThingClass = OWLModel.owlModel.getOWLThingClass();
		Global.classesArrayList.add(owlThingClass);

		//Add all classes into classesArrayList.
		addClassesIntoArrayListByClass(owlThingClass);

		for (int i = 0; i < Global.classesArrayList.size(); i++) {
			Global.liClassNameList.add("liClosed");
		}

                // remove duplicate class in list
                Set setClasses = new HashSet(Global.classesArrayList);
                Global.classesArrayList = new ArrayList<OWLNamedClass>(setClasses);
		// Convert all classes into Tree.                                
		convertClassesIntoTree();
	}

	public static void addClassesIntoArrayListByClass(OWLNamedClass parentClass) {
		Collection classes = parentClass.getSubclasses(false);
		try {
			for (Iterator it = classes.iterator(); it.hasNext();) {
				DefaultRDFSNamedClass cls = (DefaultRDFSNamedClass) it.next();
				if (!cls.isSystem()) {
					Global.classesArrayList.add((OWLNamedClass) cls);
					if (cls.getSubclassCount() != 0) {
						addClassesIntoArrayListByClass((OWLNamedClass) cls);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error addClassesIntoArrayListByClass");
		}
	}

	public static void convertClassesIntoTree() {
		Global.strResult = "<ul class=\"mktree\" id=\"tree1\" >\n";
		for (int i = 0; i < Global.classesArrayList.size(); i++) {
			if (Global.classesArrayList.get(i).getBrowserText().equals("owl:Thing")) {
                            Global.liClassNameList.set(i, "liOpen");
				Global.strResult += "<li id=\"" + Global.classesArrayList.get(i).getBrowserText() +"\" " +
                                        "class=\"" + Global.liClassNameList.get(i) + "\" >\n";
				if (Global.classesArrayList.get(i).getSubclassCount() != 0) {
					Global.strResult += "	<a href='#"+Global.classesArrayList.get(i).getBrowserText() + "_"+i+ "'"+" style=\"text-decoration:none\" class=\"bullet\" " +
                                                    "onclick=\"changeTree('" + Global.classesArrayList.get(i).getBrowserText() + "')\" ></a>" +
                                                "<IMG SRC=\"./css/accept.png\" BORDER=0 align=\"bottom\">\n";
				}
				else {
					Global.strResult += "	<IMG SRC=\"./css/bullet.gif\" BORDER=0 align=\"bottom\">\n";
				}
				Global.strResult += "<a href='#"+Global.classesArrayList.get(i).getBrowserText() + "_"+i+ "'"+" name='"+Global.classesArrayList.get(i).getBrowserText() + "_"+i+"'"+ "style=\"text-decoration:none\" onclick=\"showIndividuals('" + Global.classesArrayList.get(i).getBrowserText() + "','listID')\" >"
						+ Global.classesArrayList.get(i).getBrowserText()
						+ "</a>\n";
				addNodesByRank(Global.classesArrayList.get(i));
                                Global.strResult += "\n</li>\n";
				break;
			}
		}
	}

	private static void addNodesByRank(OWLNamedClass parentClass) {
            Global.strResult += "<ul>\n";
		for (int i = 0; i < Global.classesArrayList.size(); i++) {
			if (Global.classesArrayList.get(i).isSubclassOf((RDFSClass) parentClass)) {
                            temp = Global.classesArrayList.get(i).getBrowserText().replace("_", " ");
				Global.strResult += "<li id=\"" + temp + "_" + parentClass.getBrowserText() + "\" " +
                                        "class=\"" + Global.liClassNameList.get(i) + "\" >";
				if (Global.classesArrayList.get(i).getSubclassCount() != 0)
					Global.strResult += "	<a href='#"+temp + "_sub_"+i+ "'"+" style=\"text-decoration:none\" class=\"bullet\" onclick=\"changeTree('"
							+ temp + "_" + parentClass.getBrowserText()
							+ "')\" ></a>" +
                                                        "<IMG SRC=\"./css/accept.png\" BORDER=0 align=\"bottom\">\n";
				else
					Global.strResult += "	<IMG SRC=\"./css/bullet.gif\" BORDER=0 align=\"bottom\">\n";
				Global.strResult += "<a href='#"+temp + "_sub_"+i+ "'"+" name='"+temp + "_sub_"+i+"'"+" style=\"text-decoration:none\" onclick=\"showIndividuals('"
						+ temp
						+ "','listID')\" >"
						+ temp
						+ "</a>\n";
				if (Global.classesArrayList.get(i).getSubclassCount() != 0) {
					addNodesByRank(Global.classesArrayList.get(i));
				}
                                Global.strResult += "</li>\n";
			}
		}
            Global.strResult += "</ul>";
	}
}
