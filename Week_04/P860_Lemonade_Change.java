package week04;

public class P860_Lemonade_Change {
    public boolean _lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;
        for (int bill : bills) {
            if (bill == 5) five++;
            if (bill == 10) {
                if (five > 0) {
                    five--;
                    ten++;
                } else return false;
            }
            if (bill == 20) {
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else {
                    if (five >= 3) five -= 3;
                    else return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int[] a = {5, 5, 5, 10, 20};
        P860_Lemonade_Change p = new P860_Lemonade_Change();
        System.out.println(p._lemonadeChange(a));
    }

}
