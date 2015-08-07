package daping.example;
import com.aliasi.spell.WeightedEditDistance;

import com.aliasi.util.Distance;

public class 特定字符相关权重的编辑距离 {

    public static void main(String[] args) {
        Distance<CharSequence> d = new CasePunctuationDistance();
        
        String daping_args1[]={"hte","hte","hte","hte","hte","hte","htne","htne","htne","htne"};
        String daping_args2[]={"hte","htne","thhe","the","then","The","THE","hte","htne","thhe"};

        for (String s1 : daping_args1)
            for (String s2 : daping_args2)
                System.out.printf("%12s  %12s  %5.1f\n",
                                  s1,s2, d.distance(s1,s2));
    }


    static class CasePunctuationDistance extends WeightedEditDistance {

        public double deleteWeight(char c) {
            return (Character.isLetter(c) || Character.isDigit(c))
                ? -1//删除字母或数字代价为-1
                : 0;//否则为0，即无代价
        }

        public double insertWeight(char c) {
            return deleteWeight(c);
        }

        public double substituteWeight(char cDeleted, char cInserted) {
            return (Character.toLowerCase(cDeleted) == Character.toLowerCase(cInserted))
                ? 0
                : -1;
        }

        public double matchWeight(char cMatched) {
            return 0;
        }

        public double transposeWeight(char cFirst, char cSecond) {
            return Double.NEGATIVE_INFINITY;
        }
    }

}