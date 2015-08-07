package daping.example;
import com.aliasi.spell.TfIdfDistance;

import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.TokenizerFactory;

public class 文档TfIdf距离 {


    public static void main(String[] args) {
        TokenizerFactory tokenizerFactory = IndoEuropeanTokenizerFactory.INSTANCE;
        TfIdfDistance tfIdf = new TfIdfDistance(tokenizerFactory);

        String daing_args[]={"The gene is named TP53 after the protein it codes for (TP53 is another name for p53",
		"p53, also known as protein 53 (TP53), is a transcription factor that regulates the cell cycle and hence functions as a tumor suppressor",
        "The human gene that encodes for p53 is TP53",
		"It is important in multicellular organisms as it helps to suppress cancer"};

        
        for (String s : daing_args)
            tfIdf.handle(s);

        System.out.printf("\n  %18s  %8s  %8s\n",
                          "Term", "Doc Freq", "IDF");
        for (String term : tfIdf.termSet())
            System.out.printf("  %18s  %8d  %8.2f\n",
                              term,
                              tfIdf.docFrequency(term),
                              tfIdf.idf(term));

        for (String s1 : daing_args) {
            for (String s2 : daing_args) {
                System.out.println("\nString1=" + s1);
                System.out.println("String2=" + s2);
                System.out.printf("distance=%4.2f  proximity=%4.2f\n",
                                  tfIdf.distance(s1,s2),
                                  tfIdf.proximity(s1,s2));
            }
        }
    }
}