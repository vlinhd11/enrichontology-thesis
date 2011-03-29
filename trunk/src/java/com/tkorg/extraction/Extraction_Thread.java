/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.extraction;

import com.tkorg.util.Constants;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DANHIT
 */
public class Extraction_Thread extends Thread {

    private String sentence;
    private String keyword;
    private String individual;

    @Override
    public void run() {
        this.individual = extractSentence(this.sentence, this.keyword);
    }

    public String extractSentence(String sentence, String keywordname) {
        individual = "";
        if (individual.equals("")) {
            if (sentence.indexOf(keywordname) > -1) { //có tồn tại keyword trong câu
                String temp = sentence;
                String[] list = temp.split(keywordname);
                for (int i = 0; i < list.length; i++) {
                    if (list[i].startsWith("(") || list[i].startsWith(" (")) {
                        for (int j = 0; j < Constants.followwords.length; j++) {
                            int index02 = sentence.indexOf(")" + Constants.followwords[j]);
                            if (index02 > -1) {
                                individual = sentence.substring(index02 + Constants.followwords[j].length() + 1);
                                break;
                            }
                        }
                    } else {
                        for (int j = 0; j < Constants.followwords.length; j++) {
                            if (list[i].startsWith(Constants.followwords[j])) { //Có từ đặc biệt theo sau keyword
                                individual = list[i].substring((Constants.followwords[j]).length() + 1);
                                break;
                            }
                        }
                    }
                }
            }
        }

        return individual;
    }

    public String getIndividual() {
        return individual;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public static void main(String[] args) {
        try {
            Thread e = new Extraction_Thread();
            ((Extraction_Thread) e).setKeyword("Tin_học");
            ((Extraction_Thread) e).setSentence("Tin_học Bách_khoa_toàn_thư mở Wikipedia Tin_học là ngành nghiên cứu về việc tự động hóa xử lý thông tin bởi một hệ thống máy tính cụ thể hoặc trừu tượng");
            ((Extraction_Thread) e).start();
            ((Extraction_Thread) e).join();
            String t = ((Extraction_Thread) e).getIndividual();
            ((Extraction_Thread) e).stop();
            JOptionPane.showMessageDialog(null, "rut ra: " + t);
        } catch (InterruptedException ex) {
            Logger.getLogger(Extraction_Thread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
