/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.util;

import java.net.URL;

/**
 *
 * @author danhit
 */
public class Constants {

    /* Dictionary VN */
    public static String[] unicodeWords = {"Ã´", "Ã¡", "á»", "Æ°á»", "á»", "á»±", "á»", "áº£", "á»", "á»©", "Ãª",
                                          "áº§", "Æ°á»", "Ã­", "Ã¢", "áº¡", "á»"};
    public static String[] utf8Words    = { "ô",  "á",   "ệ",   "ườ",  "ọ",   "ự",    "ộ",   "ả",   "ổ",   "ứ",  "ê",
                                            "ầ",    "ưở",  "í",  "â",   "ạ",   "ề"};

    /* number */

    /* path */
    public static String PATH_ONTOLOGY = Thread.currentThread().getContextClassLoader().getResource("com/tkorg/data/OVIT.owl").getPath();
    public static String PATH_FILES = "D:\\download files\\";

    /* text */
    public static String GOOGLE = "google";
    public static String YAHOO = "yahoo";
    public static String GOOGLE_LIST = "googleList";
    public static String YAHOO_LIST = "yahooList";
    public static String IS_GOOGLE = "isGoogle";
    public static String IS_YAHOO = "isYahoo";
}
