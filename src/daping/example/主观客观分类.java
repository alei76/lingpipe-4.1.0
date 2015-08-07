package daping.example;
import com.aliasi.util.Files;

import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.classify.JointClassification;
import com.aliasi.classify.JointClassifier;
import com.aliasi.classify.JointClassifierEvaluator;

import com.aliasi.lm.LanguageModel;
import com.aliasi.lm.NGramProcessLM;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.IOException;


public class 主观客观分类 {

    File mPolarityDir;
    String[] mCategories;
    DynamicLMClassifier<NGramProcessLM> mClassifier;

    主观客观分类(String[] args) {
        System.out.println("\nBASIC SUBJECTIVITY DEMO");
        mPolarityDir = new File("E:/JAVAProjects/lingpipe-4.1.0/demos/data/rotten_imdb");//5000 objective, 5000 subjective sentences.90%作为训练
        System.out.println("\nData Directory=" + mPolarityDir);
        mCategories = new String[] { "plot", "quote" };
        int nGram = 8;
        mClassifier = 
            DynamicLMClassifier
            .createNGramProcess(mCategories,nGram);
    }

    void run() throws ClassNotFoundException, IOException {
        train();
        evaluate();
    }

    void train() throws IOException {
        int numTrainingChars = 0;
        System.out.println("\nTraining.");
        for (int i = 0; i < mCategories.length; ++i) {
            String category = mCategories[i];
            Classification classification
                = new Classification(category);
            File file = new File(mPolarityDir,
                                 mCategories[i] + ".tok.gt9.5000");
            String data = Files.readFromFile(file,"ISO-8859-1");
            String[] sentences = data.split("\n");
            System.out.println("# Sentences " + category + "=" + sentences.length);
            int numTraining = (sentences.length * 9) / 10;
            for (int j = 0; j < numTraining; ++j) {
                String sentence = sentences[j];
                numTrainingChars += sentence.length();
                Classified<CharSequence> classified
                    = new Classified<CharSequence>(sentence,classification);
                mClassifier.handle(classified);
            }
        }
        
        System.out.println("\nCompiling.\n  Model file=subjectivity.model");
        FileOutputStream fileOut = new FileOutputStream("subjectivity.model");
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
        mClassifier.compileTo(objOut);
        objOut.close();

        System.out.println("  # Training Cases=" + 9000);
        System.out.println("  # Training Chars=" + numTrainingChars);
    }

    void evaluate() throws IOException {
        // classifier hasn't been compiled, so it'll be slower
        boolean storeInputs = false;
        JointClassifierEvaluator<CharSequence> evaluator
            = new JointClassifierEvaluator<CharSequence>(mClassifier, mCategories,storeInputs);
        System.out.println("\nEvaluating.");
        for (int i = 0; i < mCategories.length; ++i) {
            String category = mCategories[i];
            Classification classification
                = new Classification(category);
            File file = new File(mPolarityDir,
                                 mCategories[i] + ".tok.gt9.5000");
            String data = Files.readFromFile(file,"ISO-8859-1");
            String[] sentences = data.split("\n");
            int numTraining = (sentences.length * 9) / 10;
            for (int j = numTraining; j < sentences.length; ++j) {
                Classified<CharSequence> classified
                    = new Classified<CharSequence>(sentences[j],classification);
                evaluator.handle(classified);
            }
        }
        System.out.println();
        System.out.println(evaluator.toString());
    }

    public static void main(String[] args) {
        try {
            new 主观客观分类(args).run();
        } catch (Throwable t) {
            System.out.println("Thrown: " + t);
            t.printStackTrace(System.out);
        }
    }

}

