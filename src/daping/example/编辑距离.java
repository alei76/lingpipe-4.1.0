package daping.example;
import com.aliasi.spell.EditDistance;

import com.aliasi.util.Distance;
import com.aliasi.util.Proximity;

public class 编辑距离 {

    static final Distance<CharSequence> D1
        = new EditDistance(false);

    static final Distance<CharSequence> D2
        = new EditDistance(true);

    static final Proximity<CharSequence> P1
        = new EditDistance(false);

    static final Proximity<CharSequence> P2
        = new EditDistance(true);

    public static void main(String[] args) {
        System.out.printf("\n%12s  %12s  %5s  %5s   %5s  %5s\n",
                          "String1", "String2",
                          "Dist1",
                          "Dist2",
                          "Prox1",
                          "Prox2");
        String daping_args1[]={"hte","hte","hte","hte","hte","hte","htne","htne","htne","htne"};
        String daping_args2[]={"hte","htne","thhe","the","then","The","THE","hte","htne","thhe"};
        
        for (String s1 : daping_args1)
            for (String s2 : daping_args2)
                System.out.printf("%12s  %12s  %5.1f  %5.1f   %5.1f  %5.1f\n",
                                  s1,
                                  s2,
                                  D1.distance(s1,s2),//没有调换
                                  D2.distance(s1,s2),//
                                  P1.proximity(s1,s2),// = -D1.distance(x,y)
                                  P2.proximity(s1,s2));//

    }

}
/*
 * Alternative Proximity from Edit Distance
It also makes sense to define proximity as the multiplicative inverse distance instead of the additive inverse: 

proximity(x,y) = 1 / dist(x,y)
The only problem is that if dist(x,y) = 0, then

proximity(x,y) = 1 / 0
               = Double.POSITIVE.INFINITY

*/