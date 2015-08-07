import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.Chunking;

import com.aliasi.util.AbstractExternalizable;

import java.io.File;

public class RunChunker {

    public static void main(String[] args) throws Exception {
	
    File modelFile = new File("E:/JAVAProjects/lingpipe-4.1.0/demos/data/ne-en-bio-genetag.HmmChunker");

	System.out.println("Reading chunker from file=" + modelFile);
	Chunker chunker 
	    = (Chunker) AbstractExternalizable.readObject(modelFile);

	String texts[]={"p53 regulates human insulin-like growth factor II gene expression through active P4 promoter in rhabdomyosarcoma cells"};    
	for (int i = 0; i < texts.length; ++i) {
	    Chunking chunking = chunker.chunk(texts[i]);
	    System.out.println("Chunking=" + chunking);
	}

	System.out.println(texts[0].substring(0, 3));
	System.out.println(texts[0].substring(20, 54));
	System.out.println(texts[0].substring(81, 92));
    }
    
    

}
