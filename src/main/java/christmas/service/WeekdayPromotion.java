package christmas.service;

import static christmas.domain.constants.WeekInfo.WEEKDAY;

import christmas.domain.Benefit;
import christmas.domain.Discount;
import christmas.domain.Orders;
import christmas.domain.constants.MenuCategory;
import christmas.domain.constants.WeekInfo;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;

public class WeekdayPromotion implements PromotionService {
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int DISCOUNT_PER_MENU = 2023;

    @Override
    public Optional<Benefit> apply(int day, Orders orders) {
        if (isQualified(day)) {
            return Optional.of(
                    new Discount(calculateDiscount(orders))
            );
        }
        return Optional.empty();
    }

    private boolean isQualified(int day) {
        DayOfWeek dayOfWeek = LocalDate.of(YEAR, MONTH, day).getDayOfWeek();
        return WeekInfo.from(dayOfWeek).equals(WEEKDAY);
    }

    private int calculateDiscount(Orders orders) {
        int dessertMenu = orders.getCountOfCategory(MenuCategory.DESSERT);
        return dessertMenu * DISCOUNT_PER_MENU;
    }
}