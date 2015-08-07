package daping.example;
import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.ConfidenceChunker;

import com.aliasi.util.AbstractExternalizable;
import com.aliasi.util.Strings;

import java.io.File;

import java.util.Iterator;

public class Confidence基于词典的命名实体识别 {


    static final int MAX_N_BEST_CHUNKS = 8;

    public static void main(String[] args) throws Exception {
    	File modelFile = new File("E:/JAVAProjects/lingpipe-4.1.0/demos/data/ne-en-bio-genetag.HmmChunker");//English Genes: GeneTag

        System.out.println("Reading chunker from file=" + modelFile);
        ConfidenceChunker chunker
            = (ConfidenceChunker) AbstractExternalizable.readObject(modelFile);

        String texts[]={"p53 regulates human insulin-like growth factor II gene expression through active P4 promoter in rhabdomyosarcoma cells"};    
        for (int i = 0; i < texts.length; ++i) {
            char[] cs = texts[i].toCharArray();
            Iterator<Chunk> it
                = chunker.nBestChunks(cs,0,cs.length,MAX_N_BEST_CHUNKS);
            System.out.println(texts[i]);
            System.out.println("Rank      Conf      Span    Type     Phrase");
            for (int n = 0; it.hasNext(); ++n) {
                Chunk chunk = it.next();
                double conf = java.lang.Math.pow(2.0,chunk.score());
                int start = chunk.start();
                int end = chunk.end();
                String phrase = texts[i].substring(start,end);
                System.out.println(n + " "
                                   + String.format("%12.4f",conf)
                                   + "   (" + start
                                   + ", " + end
                                   + ")   " + chunk.type()
                                   + "     " + phrase);

            }
            System.out.println();
        }
    }
}
