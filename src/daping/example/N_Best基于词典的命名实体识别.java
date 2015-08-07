package daping.example;
import com.aliasi.chunk.Chunking;
import com.aliasi.chunk.NBestChunker;

import com.aliasi.util.AbstractExternalizable;
import com.aliasi.util.ScoredObject;

import java.io.File;

import java.util.Iterator;

public class N_Best基于词典的命名实体识别 {


    static final int MAX_N_BEST = 8;

    public static void main(String[] args) throws Exception {
	File modelFile = new File("E:/JAVAProjects/lingpipe-4.1.0/demos/data/ne-en-bio-genetag.HmmChunker");//English Genes: GeneTag

	System.out.println("Reading chunker from file=" + modelFile);
	NBestChunker chunker 
	    = (NBestChunker) AbstractExternalizable.readObject(modelFile);
	
	String texts[]={"p53 regulates human insulin-like growth factor II gene expression through active P4 promoter in rhabdomyosarcoma cells"};    
	for (int i = 0; i < texts.length; ++i) {
	    char[] cs = texts[i].toCharArray();
	    Iterator<ScoredObject<Chunking>> it = chunker.nBest(cs,0,cs.length,MAX_N_BEST);
	    System.out.println(texts[i]);
	    for (int n = 0; it.hasNext(); ++n) {
	    	ScoredObject<Chunking> so = it.next();
	    	double jointProb = so.score();
	    	Chunking chunking = so.getObject();
	    	System.out.println(n + " " + jointProb + " " + chunking.chunkSet());
	    }
	    System.out.println();
	}
    }
}
/*
The results are provided below. 

Reading chunker from file=..\..\models\ne-en-bio-genetag.HmmChunker
p53 regulates human insulin-like growth factor II gene expression through active P4 promoter in rhabdomyosarcoma cells.

0 -182.7325747972808 [0-3:GENE@-Infinity, 20-54:GENE@-Infinity, 81-92:GENE@-Infinity]
1 -183.39842051103167 [0-3:GENE@-Infinity, 14-54:GENE@-Infinity, 81-92:GENE@-Infinity]
2 -185.1133085819252 [0-3:GENE@-Infinity, 20-54:GENE@-Infinity, 74-92:GENE@-Infinity]
3 -185.73246504074297 [0-3:GENE@-Infinity, 20-54:GENE@-Infinity, 81-83:GENE@-Infinity]
4 -185.77915429567608 [0-3:GENE@-Infinity, 14-54:GENE@-Infinity, 74-92:GENE@-Infinity]
5 -186.39831075449385 [0-3:GENE@-Infinity, 14-54:GENE@-Infinity, 81-83:GENE@-Infinity]
6 -187.47968583511886 [0-3:GENE@-Infinity, 20-54:GENE@-Infinity]
7 -188.14553154886974 [0-3:GENE@-Infinity, 14-54:GENE@-Infinity]
The chunkings are ordered in descending (log base 2) joint probability. For instance, the first result has a joint estimate of -182.7, whereas the second has one of -183.4. By some simple math, we can see how much more likely the first analysis is than the second: 

p1/p2 = 2 ** (log (p1/p2))
      = 2 ** (log p1 - log p2)
      = 2 ** -182.7-183.4
      = 2 ** .7
      ~ 1.62
That is, the first analysis is only 1.62 times as likely as the second, analysis. The first analysis is more than five times as likely as the third analysis, with a log probability of -185.1. 


*/