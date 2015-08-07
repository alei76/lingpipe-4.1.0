package daping.example;
import com.aliasi.spell.JaccardDistance;

import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.TokenizerFactory;

public class 文档Jaccard距离 {



    public static void main(String[] args) {
        TokenizerFactory tokenizerFactory = IndoEuropeanTokenizerFactory.INSTANCE;
        JaccardDistance jaccard = new JaccardDistance(tokenizerFactory);

        String args1[]={"The gene is named TP53 after the protein it codes for (TP53 is another name for p53",
        		"p53, also known as protein 53 (TP53), is a transcription factor that regulates the cell cycle and hence functions as a tumor suppressor"};
        String args2[]={"The human gene that encodes for p53 is TP53",
        		"It is important in multicellular organisms as it helps to suppress cancer"};

        
        for (String s1 : args1) {
            for (String s2 : args2) {
                System.out.println("\nString1=" + s1);
                System.out.println("String2=" + s2);
                System.out.printf("distance=%4.2f  proximity=%4.2f\n",
                                  jaccard.distance(s1,s2),// =1.0 - jaccard.proximity(s1,s2);
                                  jaccard.proximity(s1,s2));//邻近度 consider the size of the set of words in both sentences divided by the total number of words in both 


            }
        }
        
       
    }
}