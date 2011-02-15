/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.util;

/**
 *
 * @author danhit
 */
public class Constants {

    /* Dictionary VN */
    public static String[] unicodeWords = {"Ã¡", "Ã ",  "áº£", "",  "áº¡",
                                           "",  "",  "",  "áº³",  "",  "",
                                           "Ã¢",  "áº¥",  "áº§",  "",  "",  "áº­",
                                           "Ä", "Ä",
                                           "",  "",  "",  "",  "",
                                           "Ãª",  "áº¿",  "á»",  "á»",  "",  "á»",
                                           "Ã­",  "Ã¬",  "",  "",  "á»",
                                           "",  "",  "",  "",  "á»",
                                           "Ã´",  "á»",  "",  "á»",  "",  "á»",
                                           "Æ¡",  "",  "á»",  "á»",  "",  "",
                                           "Ãº",  "",  "",  "",  "á»¥",
                                           "Æ°",  "á»©",  "",  "",  "á»¯",  "á»±",
                                           "",  "",  "",  "á»¹",  ""};
    public static String[] utf8Words    = {"á",  "à", "ả",   "ã", "ạ",
                                           "ă", "ắ", "ằ", "ẳ", "ẵ", "ặ",
                                           "â", "ấ", "ầ", "ẩ", "ẫ", "ậ",
                                           "đ", "đ",
                                           "é", "è", "ẻ", "ẽ", "ẹ",
                                           "ê", "ế", "ề", "ể", "ễ", "ệ",
                                           "í", "ì", "ỉ", "ĩ", "ị",
                                           "ó", "ò", "ỏ", "õ", "ọ",
                                           "ô", "ố", "ồ", "ổ", "ỗ", "ộ",
                                           "ơ", "ớ", "ờ", "ở", "ỡ", "ợ",
                                           "ú", "ù", "ủ", "ũ", "ụ",
                                           "ư", "ứ", "ừ", "ử", "ữ", "ự",
                                           "ý", "ỳ", "ỷ", "ỹ", "ỵ"};

    /* number hÃ nh */

    /* path */
//    public static String PATH_ONTOLOGY = Thread.currentThread().getContextClassLoader().getResource("com/tkorg/data/OVIT.owl").getPath();
//    public static String PATH_TRAIN = Thread.currentThread().getContextClassLoader().getResource("com/tkorg/training/train").getPath();
//    public static String PATH_LAST_TRAIN = Thread.currentThread().getContextClassLoader().getResource("com/tkorg/training/lastTrain").getPath();
//    public static String PATH_TRAIN = Thread.currentThread().getContextClassLoader().getResource("com/tkorg/tokenizer").getPath();
//    public static String PATH_STOPWORD = Thread.currentThread().getContextClassLoader().getResource("com/tkorg/data/stopwords.txt").getPath();
    public static String PATH_STOPWORD = "C:/HOC TAP/ThucHanh/NetBeans/EnrichOntology/src/java/com/tkorg/data/stopwords.txt";
    public static String PATH_TRAIN = "C:/Train";
    public static String PATH_ONTOLOGY = "C:/OVIT.owl";
    public static String PATH_LAST_TRAIN = "C:/lastTrain";
    public static String PATH_FILES = "C:/download files";

    /* text */
    public static String GOOGLE = "google";
    public static String YAHOO = "yahoo";
    public static String GOOGLE_LIST = "googleList";
    public static String YAHOO_LIST = "yahooList";
    public static String IS_GOOGLE = "isGoogle";
    public static String IS_YAHOO = "isYahoo";
}
