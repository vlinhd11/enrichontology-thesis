/**
 * 
 */
package vn.hus.nlp.lang.model;

import com.tkorg.util.Constants;

/**
 * @author phuonglh
 * 
 * Some constants for the plugin.
 *
 */
public interface IConstants {
	
	/**
	 * Debug the package or not 
	 */
	static final boolean DEBUG = true;
	/**
	 * The reference corpora directory that contains text files to train 
	 * the model.
	 */
	static final String CORPORA_DIRECTORY = "corpora/ref";
	/**
	 * Unigram model
	 */
	static final String UNIGRAM_MODEL = Constants.UNIGRAM_MODEL;
	/**
	 * Bigram model
	 */
	static final String BIGRAM_MODEL = Constants.BIGRAM_MODEL;
	
	/**
	 * The conditional probabilities.
	 */
	static final String CONDITIONAL_PROBABILITIES = Constants.CONDITIONAL_PROBABILITIES;
//        static final String CONDITIONAL_PROBABILITIES = Thread.currentThread().getContextClassLoader().getResource("prob.xml").getPath().replace("%20", " ").replaceFirst("/", "").replace("build/web/WEB-INF/classes", "resources");
}
