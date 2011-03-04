/**
 * (C) Le Hong Phuong, phuonglh@gmail.com
 * vn.hus.toolkit
 * 2006
 */
package vn.hus.nlp.tokenizer;

import com.tkorg.util.Constants;

/**
 * @author LE Hong Phuong
 * <p>
 * 31 déc. 06
 * </p>
 * Some predefined contants for vnTokenizer tool.
 * 
 */
public interface IConstants {
	/**
	 * Vietnamese word set
	 */
	public static final String WORD_SET = "data/dictionaries/words_v4.txt";
	
	/**
	 * The Vietnamese lexicon
	 */
	public static final String LEXICON = "data/dictionaries/words_v4.xml";
	
	/**
	 * The Vietnamese DFA lexicon
	 */
	public static final String LEXICON_DFA = Constants.LEXICON_DFA;
//        public static final String LEXICON_DFA = Thread.currentThread().getContextClassLoader().getResource("lexicon_dfa_minimal.xml").getPath().replace("%20", " ").replaceFirst("/", "").replace("build/web/WEB-INF/classes", "resources/automata");
	
	/**
	 * Lexer specification
	 */
	public static final String LEXER_SPECIFICATION= Constants.LEXER_SPECIFICATION;
//        public static final String LEXER_SPECIFICATION= Thread.currentThread().getContextClassLoader().getResource("lexers.xml").getPath().replace("%20", " ").replaceFirst("/", "").replace("build/web/WEB-INF/classes", "resources/lexers");
	/**
	 * Unigram model
	 */
	public static final String UNIGRAM_MODEL = Constants.UNIGRAM_MODEL;
//        public static final String UNIGRAM_MODEL = Thread.currentThread().getContextClassLoader().getResource("unigram.xml").getPath().replace("%20", " ").replaceFirst("/", "").replace("build/web/WEB-INF/classes", "resources/bigram");
	/**
	 * Bigram model
	 */
	public static final String BIGRAM_MODEL = Constants.BIGRAM_MODEL;
//        public static final String BIGRAM_MODEL = Thread.currentThread().getContextClassLoader().getResource("bigram.xml").getPath().replace("%20", " ").replaceFirst("/", "").replace("build/web/WEB-INF/classes", "resources/bigram");
	
	/**
	 * The named entity prefix.
	 */
	public static final String NAMED_ENTITY_PREFIX = Constants.NAMED_ENTITY_PREFIX;
//        public static final String NAMED_ENTITY_PREFIX = Thread.currentThread().getContextClassLoader().getResource("namedEntityPrefix.xml").getPath().replace("%20", " ").replaceFirst("/", "").replace("build/web/WEB-INF/classes", "resources/prefix");
}