/**
 * (C) Le Hong Phuong, phuonglh@gmail.com
 *  Vietnam National University, Hanoi, Vietnam.
 */
package vn.hus.nlp.tokenizer.segmenter;

import com.tkorg.util.Constants;

/**
 * @author Le Hong Phuong, phuonglh@gmail.com
 * <p>
 * Nov 12, 2007, 8:48:03 PM
 * <p>
 * Some constants for the package.
 */
public interface IConstants {
	/**
	 * The lexicon dfa.
	 */
	static String LEXICON_DFA = Constants.TOKENIZER_SEGMENTER_LEXICON_DFA;
//    static String LEXICON_DFA = Thread.currentThread().getContextClassLoader().getResource("dfaLexicon.xml").getPath().replace("%20", " ").replaceFirst("/", "").replace("build/web/WEB-INF/classes", "resources/automata");
	
	/**
	 * The external lexicon
	 */
	static String EXTERNAL_LEXICON = Constants.TOKENIZER_SEGMENTER_EXTERNAL_LEXICON;
//    static String EXTERNAL_LEXICON = Thread.currentThread().getContextClassLoader().getResource("externalLexicon.xml").getPath().replace("%20", " ").replaceFirst("/", "").replace("build/web/WEB-INF/classes", "resources/automata");
	/**
	 * The file contains normalization rules for Vietnamese accents.
	 */
	static String NORMALIZATION_RULES = Constants.TOKENIZER_SEGMENTER_NORMALIZATION_RULES;
//    static String NORMALIZATION_RULES = Thread.currentThread().getContextClassLoader().getResource("rules.txt").getPath().replace("%20", " ").replaceFirst("/", "").replace("build/web/WEB-INF/classes", "resources/normalization");
}
