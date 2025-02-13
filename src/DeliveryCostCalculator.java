//version GPT

public class DeliveryCostCalculator {

    // Константы для расчета стоимости
    private static final int MIN_DELIVERY_COST = 400;
    private static final int DISTANCE_OVER_30_KM = 300;
    private static final int DISTANCE_UP_TO_30_KM = 200;
    private static final int DISTANCE_UP_TO_10_KM = 100;
    private static final int DISTANCE_UP_TO_2_KM = 50;
    private static final int LARGE_DIMENSIONS_COST = 200;
    private static final int SMALL_DIMENSIONS_COST = 100;
    private static final int FRAGILE_COST = 300;

    // Enum для загруженности службы доставки
    public enum Workload {
        VERY_HIGH(1.6),
        HIGH(1.4),
        INCREASED(1.2),
        NORMAL(1.0);

        private final double coefficient;

        Workload(double coefficient) {
            this.coefficient = coefficient;
        }

        public double getCoefficient() {
            return coefficient;
        }
    }

    // Метод для расчета стоимости доставки
    public static int calculateDeliveryCost(int distance, boolean isLargeDimensions, boolean isFragile, Workload workload) {
        // Проверка на хрупкость и расстояние
        if (isFragile && distance > 30) {
            throw new IllegalArgumentException("Хрупкие грузы нельзя возить на расстояние более 30 км.");
        }

        // Расчет стоимости в зависимости от расстояния
        int distanceCost;
        if (distance > 30) {
            distanceCost = DISTANCE_OVER_30_KM;
        } else if (distance > 10) {
            distanceCost = DISTANCE_UP_TO_30_KM;
        } else if (distance > 2) {
            distanceCost = DISTANCE_UP_TO_10_KM;
        } else {
            distanceCost = DISTANCE_UP_TO_2_KM;
        }

        // Расчет стоимости в зависимости от габаритов
        int dimensionsCost = isLargeDimensions ? LARGE_DIMENSIONS_COST : SMALL_DIMENSIONS_COST;

        // Расчет стоимости в зависимости от хрупкости
        int fragileCost = isFragile ? FRAGILE_COST : 0;

        // Общая стоимость до применения коэффициента загруженности
        int totalCost = distanceCost + dimensionsCost + fragileCost;

        // Применение коэффициента загруженности
        totalCost *= workload.getCoefficient();

        // Проверка на минимальную стоимость доставки
        return Math.max(totalCost, MIN_DELIVERY_COST);
    }

    public static void main(String[] args) {
        // Пример использования
        int distance = 25; // Расстояние в км
        boolean isLargeDimensions = true; // Большие габариты
        boolean isFragile = false; // Не хрупкий
        Workload workload = Workload.HIGH; // Высокая загруженность

        int cost = calculateDeliveryCost(distance, isLargeDimensions, isFragile, workload);
        System.out.println("Стоимость доставки: " + cost + " рублей");
    }
}