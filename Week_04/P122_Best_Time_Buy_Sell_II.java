package week04;

public class P122_Best_Time_Buy_Sell_II {
    public int maxProfit(int[] prices) {
        int nowPrice = prices[0];
        int today = 1;
        int in = 0;
        while (today < prices.length) {
            if (prices[today] > nowPrice) {
                in += prices[today] - nowPrice;
            }
            nowPrice = prices[today];
            today++;
        }
        return in;
    }
}
