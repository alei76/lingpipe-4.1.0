package daping.example;
import com.aliasi.spell.JaroWinklerDistance;

public class 人名的距离 {

    public static void main(String[] args) {
        JaroWinklerDistance jaroWinkler = JaroWinklerDistance.JARO_WINKLER_DISTANCE;
        System.out.printf("\n%18s  %18s  %5s  %5s\n",
                          "String1", "String2", "Dist", "Prox");
        String daping_args[] ={"MARTHA|MARHTA",
        		"JONES|JOHNSON",
        		"DUNNINGHAM|CUNNINGHAM",
        		"MICHELLE|MICHAEL",
        		"NICHLESON|NICHULSON",
        		"MASSEY|MASSIE",
        		"ABROMS|ABRAMS",
        		"HARDIN|MARTINEZ",
        		"ITMAN|SMITH",
        		"JERALDINE|GERALDINE",
        		"JULIES|JULIUS",
        		"TANYA|TONYA",
        		"DWAYNE|DUANE",
        		"SEAN|SUSAN",
        		"JON|JOHN",
        		"JON|JAN",
        		"熊大平|daping"};
        
        for (String s : daping_args) {
            String[] pair = s.split("\\|");
            String s1 = pair[0];
            String s2 = pair[1];
            System.out.printf("%18s  %18s  %5.3f  %5.3f\n",
                              s1, s2,
                              jaroWinkler.distance(s1,s2),
                              jaroWinkler.proximity(s1,s2));
        }
    }

}