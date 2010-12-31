package com.tkorg.search;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.tkorg.entities.MyStack;
import com.tkorg.entities.OWLModel;

import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.RDFSClass;
import edu.stanford.smi.protegex.owl.model.impl.DefaultRDFSNamedClass;

public class ClassActions {
	public static ArrayList<OWLNamedClass> classesArrayList = null;
	public static ArrayList<String> liClassNameList = null;
	public static String selectedClassName = "";
	public static String strResult = "";
        private static String temp = "";

	public static void viewClasses() {
		classesArrayList = new ArrayList<OWLNamedClass>();
		liClassNameList = new ArrayList<String>();
		MyStack myStack = new MyStack();

		myStack.init();
		OWLNamedClass owlThingClass = OWLModel.owlModel.getOWLThingClass();
		classesArrayList.add(owlThingClass);

		// Add all classes into classesArrayList.
		addClassesIntoArrayListByClass(owlThingClass);

		for (int i = 0; i < classesArrayList.size(); i++) {
			liClassNameList.add("liClosed");
		}

		// Convert all classes into Tree.
		convertClassesIntoTree();
	}

	public static void addClassesIntoArrayListByClass(OWLNamedClass parentClass) {
		Collection classes = parentClass.getSubclasses(false);
		try {
			for (Iterator it = classes.iterator(); it.hasNext();) {
				DefaultRDFSNamedClass cls = (DefaultRDFSNamedClass) it.next();
				if (!cls.isSystem()) {
					classesArrayList.add((OWLNamedClass) cls);
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
		strResult = "<ul class=\"mktree\" id=\"tree1\" >\n";
		for (int i = 0; i < classesArrayList.size(); i++) {
			if (classesArrayList.get(i).getBrowserText().equals("owl:Thing")) {
				strResult += "<li id=\"" + classesArrayList.get(i).getBrowserText() + "\" " +
                                        "class=\"" + liClassNameList.get(i) + "\" >\n";
				if (classesArrayList.get(i).getSubclassCount() != 0) {
					strResult += "	<a href=\"#\" style=\"text-decoration:none\" class=\"bullet\" " +
                                                    "onclick=\"changeTree('" + classesArrayList.get(i).getBrowserText() + "')\" ></a>" +
                                                "<IMG SRC=\"./css/accept.png\" BORDER=0 align=\"bottom\">\n";
				}
				else {
					strResult += "	<IMG SRC=\"./css/bullet.gif\" BORDER=0 align=\"bottom\">\n";
				}
				strResult += "<a href=\"#\" style=\"text-decoration:none\" onclick=\"addConcept('" + classesArrayList.get(i).getBrowserText() + "','listID')\" >"
						+ classesArrayList.get(i).getBrowserText()
						+ "</a>\n";
				addNodesByRank(classesArrayList.get(i));
                                strResult += "\n</li>\n";
				break;
			}
		}
	}

	private static void addNodesByRank(OWLNamedClass parentClass) {
            strResult += "<ul>\n";
		for (int i = 0; i < classesArrayList.size(); i++) {
			if (classesArrayList.get(i).isSubclassOf((RDFSClass) parentClass)) {
                            temp = classesArrayList.get(i).getBrowserText().replace("_", " ");
				strResult += "<li id=\"" + temp + "\" " +
                                        "class=\"" + liClassNameList.get(i) + "\" >";
				if (classesArrayList.get(i).getSubclassCount() != 0)
					strResult += "	<a href=\"#\" style=\"text-decoration:none\" class=\"bullet\" onclick=\"changeTree('"
							+ temp
							+ "')\" ></a>" +
                                                        "<IMG SRC=\"./css/accept.png\" BORDER=0 align=\"bottom\">\n";
				else
					strResult += "	<IMG SRC=\"./css/bullet.gif\" BORDER=0 align=\"bottom\">\n";
				strResult += "<a href=\"#\" style=\"text-decoration:none\" onclick=\"addConcept('"
						+ temp
						+ "','listID')\" >"
						+ temp
						+ "</a>\n";
				if (classesArrayList.get(i).getSubclassCount() != 0) {
					addNodesByRank(classesArrayList.get(i));
				}
                                strResult += "</li>\n";
			}
		}
            strResult += "</ul>";
	}

    @SuppressWarnings("empty-statement")
	public static void main(String[] args) {
		OWLModel owlModel = new OWLModel();
		URI uri = new File("D://OVIT.owl").toURI();
		
		owlModel.loadOWLModelFromExistFile(uri);;
		ClassActions.viewClasses();
		
		for (int i = 0; i < classesArrayList.size(); i++) {
			System.out.println(classesArrayList.get(i).getBrowserText());
		}
	}
}
