/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.download;

import com.tkorg.util.Constants;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.htmlparser.beans.StringBean;
import vn.hus.nlp.tokenizer.Tokenizer;
import vn.hus.nlp.tokenizer.TokenizerOptions;
import vn.hus.nlp.tokenizer.TokenizerProvider;
import vn.hus.nlp.tokenizer.tokens.TaggedWord;
import vn.hus.nlp.utils.UTF8FileUtility;

/**
 *
 * @author danhit
 */
public class Download {

    private List<TaggedWord> list = null;
    public Tokenizer tokenizer = null;
//    private Tokenizer tokenizer = null;
//    private int dem = 0;

    public Download() {
        try {
            if (tokenizer == null) {
                tokenizer = TokenizerProvider.getInstance().getTokenizer();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error init download");
        }
    }

    public void downloadFileFromLink(String url, String title) throws IOException {
//        String type=".html";
//        if(url.substring(url.length()- 4,url.length()- 3).equals(".")
//                && (!url.substring(url.length() - 3, url.length()).equals("pdf"))
//                && (!url.substring(url.length() - 3, url.length()).equals("doc"))) {
//            type=url.substring(url.length()- 4);
//        }

//        File file = new File(Constants.PATH_FILES);
//
//        if (!file.exists())
//            file.mkdir();
//        JOptionPane.showMessageDialog(null, "success");

        segment(getUrlContentsAsText(url));
        download02(Constants.PATH_FILES + "/" + title + ".txt");
    }

    public void download01(String url, String title, String type) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new java.net.URL(url).openStream());
        FileOutputStream fos = new FileOutputStream(Constants.PATH_FILES + "/" + title + type);
        BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
        byte[] data = new byte[1024];
        int x = 0;
        while((x = in.read(data, 0, 1024)) >= 0) {
            bout.write(data, 0, x);
        }
        bout.close();
        in.close();
    }

    public void download02(String filename) throws IOException {
        UTF8FileUtility.createWriter(filename);
        try {
            for (Iterator<TaggedWord> iter = list.iterator(); iter.hasNext();) {
                TaggedWord token = iter.next();
                UTF8FileUtility.write(token.toString() + "\n");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error download2");
        }
        
	UTF8FileUtility.closeWriter();
    }

    private String segment(String sentence) {
	StringBuffer result = new StringBuffer(1000);
	StringReader reader = new StringReader(sentence);
	// tokenize the sentence
	try {
            tokenizer.tokenize(reader);
            list = tokenizer.getResult();
            for (TaggedWord taggedWord : list) {
                String word = taggedWord.toString();
                if (TokenizerOptions.USE_UNDERSCORE) {
                    word = word.replaceAll("\\s+", "_");
                } else {
                    word = "[" + word + "]";
                }
                result.append(word);
                result.append(' ');
            }
	} catch (Exception e) {
            e.printStackTrace();
	}
	return result.toString().trim();
    }

    public String getUrlContentsAsText(String url) {
        String content = "";
        StringBean stringBean = new StringBean();
        stringBean.setURL(url);
        content = stringBean.getStrings();
        return content;
    }

    public static void main(String[] args) {
        try {
            Download down = new Download();
//            down.downloadFileFromLink("http://www.baohylabel.com/", "công ty-1");
//            down.downloadFileFromLink("http://www.blogcatalog.com/blogs/make-money-to-free", "công ty-2");
            String url = "http://computerjobs.vn/huong-nghiep/kinh-doanh-ict/cong-ty-cntt";
            down.downloadFileFromLink(url, "danhit");
        } catch (Exception ex) {
            Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
