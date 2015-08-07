package daping.example;
import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.Chunking;
import com.aliasi.chunk.RegExChunker;

import java.util.Iterator;
import java.util.Set;

public class 提取字符串中的邮箱地址 extends RegExChunker {

    static final long serialVersionUID = 3470881161284804670L;

    public 提取字符串中的邮箱地址() {
        super(EMAIL_REGEX,CHUNK_TYPE,CHUNK_SCORE);
    }

    // regex by Bilou McGyver (real name?), taken from:
    // http://regexlib.com/UserPatterns.aspx?authorId=877482cc-dd7d-4797-a824-7fafada2ab62
    private final static String EMAIL_REGEX
        = "[A-Za-z0-9](([_\\.\\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\\.\\-]?[a-zA-Z0-9]+)*)\\.([A-Za-z]{2,})";

    private final static String CHUNK_TYPE = "email";
    
    private final static double CHUNK_SCORE = 0.0;

    public static void main(String[] args) {
       //从一段文本中挖掘email 
    	String emails[] = {"xdaping@yahoo.com.cn","x@da.com@pn@g1@23.com","John's email is john@his.company.com and his friend's is foo.bar@123.foo.ca."};
    	
    	Chunker chunker = new 提取字符串中的邮箱地址();
        for (int i = 0; i < emails.length; ++i) {
            Chunking chunking = chunker.chunk(emails[i]);

            System.out.println("input=" + emails[i]);
            System.out.println("chunking=" + chunking);
            
            for (Chunk chunk : chunking.chunkSet()) {
                int start = chunk.start();
                int end = chunk.end();
                String text = emails[i].substring(start,end);
                System.out.println("     chunk=" + chunk + "  text=" + text);
            }
            System.out.println();
        }
    }

}