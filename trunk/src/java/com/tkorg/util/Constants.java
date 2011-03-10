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
    public static String[] unicodeWords = {"Ã¡", "Ã ", "áº£", "",  "áº¡",
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
    public static String[] extraStopwords = {"bao_giờ", "bây_giờ", "bấy_giờ", "bỏ_mẹ", "dễ_thường", "đều", "đồng_thời", "họ",
                                            "học", "mọi", "mười", "nền", "ngọn", "ngọt", "người", "nhất_tề", "nhiều", "nọ",
                                            "ối_giời", "ối_giời ơi", "ối_trời", "ờ", "phỏng", "phỏng như", "quá_trời",
                                            "ráo_trọi", "thỏm", "thọt", "tỏ_ra", "tỏ_vẻ", "tọt", "trỏng", "trời_ơi",
                                            "trời_đất_ơi", "về", "về_mặt", "về_phía", "ví_phỏng", "xềnh_xệch"};
    public static String[] extraCapitalStopwords = {"Á", "Á_à", "Ái", "Ái_chà", "Ái_dà", "Áng","Bao_giờ", "Bây_giờ",
                                            "Bấy_giờ", "Bỏ_mẹ", "Dễ_thường", "Đã", "Đại_để", "Đại_nhân", "Đại_phàm",
                                            "Đang", "Đáng_lẻ", "Đáng_lẽ", "Đáng_lí", "Đáng_lý", "Đàng_đạch",
                                            "Đánh_đùng", "Đáo_để", "Đằng_sau", "Đằng_trước", "Đâu", "Đây", "Đến", "Đều",
                                            "Để", "Đó", "Đồng_thời", "Đủ", "Được", "Họ",
                                            "Học", "Ít", "Mọi", "Mười", "Nền", "Ngọn", "Ngọt", "Người", "Nhất_tề", "Nhiều", "Nọ",
                                            "Ối", "Ối_dào", "Ối_giời", "Ối_giời_ơi", "Ối_trời", "Ờ", "Ở", "Ớ", "Phỏng",
                                            "Phỏng_như", "Quá_trời",
                                            "Ráo_trọi", "Thỏm", "Thọt", "Tỏ_ra", "Tỏ_vẻ", "Tọt", "Trỏng", "Trời_ơi",
                                            "Trời_đất_ơi", "Úi", "Úi_chà", "Úi_dào", "Về", "Về_mặt", "Về_phía", "Ví_phỏng",
                                            "Xềnh_Xệch", "Ý", "Ý_chừng", "Ý_da"};
    public static String[] words = {".", "(", ")", ";", "!", "-", "?", "...", ":", "“", "”", "\n"};
    public static String[] followwords = {" là", " có nghĩa là", " được định nghĩa là", " được gọi là", " được hiểu như là", " được hiểu là",
                                            " có thể là", " được biết như là", " được biết là", " được dùng trong", " được dùng để", " gồm", ":", " - ", "-"};
    public static String[] forwardwords = {"được gọi là ", "gọi là "};
    public static String[] removewords = {"Trong đó", "Vì vậy", "Theo đó", "Do đó"};

    /* number */
    

    /* path */
    public static String PATH_ONTOLOGY = Thread.currentThread().getContextClassLoader().getResource("com/tkorg/data/OVIT.owl").getPath().replace("%20", " ").replaceFirst("/", "").replace("build/web/WEB-INF/classes", "src/java");
    public static String PATH_STOPWORD = Thread.currentThread().getContextClassLoader().getResource("com/tkorg/data/stopwords.txt").getPath().replace("%20", " ").replaceFirst("/", "").replace("build/web/WEB-INF/classes", "src/java");

    public static String PATH_SVM_TRAIN_TRAINDATA = Thread.currentThread().getContextClassLoader().getResource("com/tkorg/data/SVM/Train/TrainData").getPath().replace("%20", " ").replaceFirst("/", "").replace("build/web/WEB-INF/classes", "src/java");
    public static String PATH_SVM_TRAIN_REMOVESTOPWORDFILES = Thread.currentThread().getContextClassLoader().getResource("com/tkorg/data/SVM/Train/RemoveStopwordFiles").getPath().replace("%20", " ").replaceFirst("/", "").replace("build/web/WEB-INF/classes", "src/java");
    public static String PATH_SVM_TRAIN_ITFEATURES = Thread.currentThread().getContextClassLoader().getResource("com/tkorg/data/SVM/Train/IT_features.txt").getPath().replace("%20", " ").replaceFirst("/", "").replace("build/web/WEB-INF/classes", "src/java");
    public static String PATH_SVM_TRAIN_TFIDFFEATURES = Thread.currentThread().getContextClassLoader().getResource("com/tkorg/data/SVM/Train/tfidf_features.txt").getPath().replace("%20", " ").replaceFirst("/", "").replace("build/web/WEB-INF/classes", "src/java");
    public static String PATH_SVM_TRAIN_MODEL = Thread.currentThread().getContextClassLoader().getResource("com/tkorg/data/SVM/Train/train_model.txt").getPath().replace("%20", " ").replaceFirst("/", "").replace("build/web/WEB-INF/classes", "src/java");

    public static String PATH_SVM_CLASSIFY_DOWNLOADFILES = Thread.currentThread().getContextClassLoader().getResource("com/tkorg/data/SVM/Classify/DownloadFiles").getPath().replace("%20", " ").replaceFirst("/", "").replace("build/web/WEB-INF/classes", "src/java");
    public static String PATH_SVM_CLASSIFY_REMOVESTOPWORDFILES = Thread.currentThread().getContextClassLoader().getResource("com/tkorg/data/SVM/Classify/RemoveStopwordFiles").getPath().replace("%20", " ").replaceFirst("/", "").replace("build/web/WEB-INF/classes", "src/java");
    public static String PATH_SVM_CLASSIFY_CHOSENFILES = Thread.currentThread().getContextClassLoader().getResource("com/tkorg/data/SVM/Classify/ChosenFile").getPath().replace("%20", " ").replaceFirst("/", "").replace("build/web/WEB-INF/classes", "src/java");
    public static String PATH_SVM_CLASSIFY_PDFFILES = Thread.currentThread().getContextClassLoader().getResource("com/tkorg/data/SVM/Classify/PDFFiles").getPath().replace("%20", " ").replaceFirst("/", "").replace("build/web/WEB-INF/classes", "src/java");
    public static String PATH_SVM_CLASSIFY_TFIDFDOWNLOADFILES = Thread.currentThread().getContextClassLoader().getResource("com/tkorg/data/SVM/Classify/tfidf_download_files.txt").getPath().replace("%20", " ").replaceFirst("/", "").replace("build/web/WEB-INF/classes", "src/java");
    public static String PATH_SVM_CLASSIFY_RESULT = Thread.currentThread().getContextClassLoader().getResource("com/tkorg/data/SVM/Classify/result.txt").getPath().replace("%20", " ").replaceFirst("/", "").replace("build/web/WEB-INF/classes", "src/java");

    /* IConstants */
    public static final String LEXICON_DFA = "E:/resources/automata/lexicon_dfa_minimal.xml";
    public static final String LEXER_SPECIFICATION= "E:/resources/lexers/lexers.xml";
    public static final String UNIGRAM_MODEL = "E:/resources/bigram/unigram.xml";
    public static final String BIGRAM_MODEL = "E:/resources/bigram/bigram.xml";
    public static final String NAMED_ENTITY_PREFIX = "E:/resources/prefix/namedEntityPrefix.xml";
    public static final String TOKENIZER_SEGMENTER_LEXICON_DFA = "E:/resources/automata/dfaLexicon.xml";
    public static String TOKENIZER_SEGMENTER_EXTERNAL_LEXICON = "E:/resources/automata/externalLexicon.xml";
    public static String TOKENIZER_SEGMENTER_NORMALIZATION_RULES = "E:/resources/normalization/rules.txt";
    public static final String CONDITIONAL_PROBABILITIES = "E:/resources/prob.xml";

    /* text */
    public static String GOOGLE = "google";
    public static String YAHOO = "yahoo";
    public static String GOOGLE_LIST = "googleList";
    public static String YAHOO_LIST = "yahooList";
    public static String IS_GOOGLE = "isGoogle";
    public static String IS_YAHOO = "isYahoo";
}
