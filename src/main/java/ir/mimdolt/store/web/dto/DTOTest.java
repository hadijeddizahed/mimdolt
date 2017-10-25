package ir.mimdolt.store.web.dto;

import ir.mimdolt.store.model.BusinessException;

/**
 * Created by Hadi Jeddi Zahed on 11/12/2016.
 */
public class DTOTest {
    public static void main(String... args) throws BusinessException {
        Double value1 = 2.2 ;
        Double value2 = 2.2 ;
        System.out.println(value1.equals(value2) );
        Integer num1 = 8886 ;
        Integer num2 = 8886 ;
        System.out.println(num1 == num2); // true

        double data[][];
        if(true)
             data = new double[16][5];
        else
             data = new double[14][5];
    }

    private static boolean inArray(int a[], int p) {
        for (int i = 0; i < a.length; i++) {
            if (p == a[i])
                return true;
        }
        return false;
    }
    private static class Data{
        private int i;
        private int j;
        private int h;
        private int p;

        public Data(int i, int j, int h) {
            this.i = i;
            this.j = j;
            this.h = h;
        }

        public Data(int i, int j, int h, int p) {
            this.i = i;
            this.j = j;
            this.h = h;
            this.p = p;
        }
    }
}
