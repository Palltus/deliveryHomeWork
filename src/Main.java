// ССылка на ДЗ: https://docs.google.com/document/d/13U2dVcLGw1YRvuGtNO2TbDBIQxss0eBUCprdj-lNmAA/edit?tab=t.0

public class Main {

    public static void main(String[] args) {

        System.out.println(getCostDelivery(20,Size.BIGSIZE,true,Rate.BUSSY));
    }

    public enum Rate {
        BUSSY,
        HIGH,
        VERYHIGH
    }

    public enum Size {
        BIGSIZE,
        SMALLSIZE
    }

    static double sumCost = 0;

    public static double getDist(int dist){
        if (dist >= 30) {
            sumCost += 300;
        } else if (dist > 10) {
            sumCost +=200;
        } else if (dist > 2) {
            sumCost += 100;
        } else {
            sumCost += 50;
        }
        return sumCost;
    }

    public static double getCostSize (Size s) {
        switch (s) {
            case BIGSIZE -> {
                 sumCost += 200;
            }
            case SMALLSIZE -> {
                 sumCost += 100;
            }
        }
        return sumCost;
    }

    public static double getCostFragile (boolean isFragile) {
        if (isFragile) {
             sumCost +=300;
            }
            return sumCost;
        }

    public static double getCostRate (Rate rate) {
        if (rate == null) {
            return sumCost;
        }
        switch (rate) {
            case BUSSY -> {
                return sumCost *= 1.2;
            }
            case HIGH -> {
                return sumCost *= 1.4;
            }
            case VERYHIGH -> {
                return sumCost *= 1.6;
            }
            default -> {
                return sumCost;
            }
        }



    }

    public static double getCostDelivery (int distance, Size s, boolean isFragile, Rate r) {
        sumCost = getDist(distance);
        sumCost = getCostSize(s);
        sumCost = getCostFragile(isFragile);
        sumCost = getCostRate(r);
        if (sumCost < 400) {
            sumCost = 400;
        }
        return sumCost;
    }

}
