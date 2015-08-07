package daping.example;
import com.aliasi.spell.FixedWeightEditDistance;

import com.aliasi.util.Distance;

public class 固定权重的编辑距离 {


    public static void main(String[] args) {
        String weight[]={"0.0", "-10.0", "-8.0", "-9.0", "-9.0"};//match= 0.0  del=-10.0  ins=-8.0  subst=-9.0  trans=-9.0
   	
    	double matchWeight = Double.valueOf(weight[0]);
        double deleteWeight = Double.valueOf(weight[1]);
        double insertWeight = Double.valueOf(weight[2]);
        double substituteWeight = Double.valueOf(weight[3]);
        double transposeWeight = Double.valueOf(weight[4]);

        System.out.printf("match=%4.1f  del=%4.1f  ins=%4.1f  subst=%4.1f  trans=%4.1f\n",
                          matchWeight,
                          deleteWeight,
                          insertWeight,
                          substituteWeight,
                          transposeWeight);

        Distance<CharSequence> fixedEd
            = new FixedWeightEditDistance(matchWeight,
                                          deleteWeight,
                                          insertWeight,
                                          substituteWeight,
                                          transposeWeight);

        System.out.printf("\n%12s  %12s  %5s\n",
                          "String1", "String2",
                          "Dist");
        
        
        String daping_args1[]={"hte","hte","hte","hte","hte","hte","htne","htne","htne","htne"};
        String daping_args2[]={"hte","htne","thhe","the","then","The","THE","hte","htne","thhe"};
        
        for (String s1 : daping_args1)
            for (String s2 : daping_args2)
                System.out.printf("%12s  %12s  %5.1f\n",
                                  s1,
                                  s2,
                                  fixedEd.distance(s1,s2));

    }

}