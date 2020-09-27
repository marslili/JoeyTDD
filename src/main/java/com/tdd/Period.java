package com.tdd;

import java.time.LocalDate;
import java.time.YearMonth;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;

public class Period {

    public Period(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private Double overLapRatio;
    private LocalDate startDate;
    private LocalDate endDate;



    public Long getDiffMonth() {
        return MONTHS.between(YearMonth.from(this.startDate), YearMonth.from(this.endDate)) + 1;
    }

    public Double getOverLapRatio(YearMonth currentMonth) {
        Double ratio = 0d;
        //頭
        if (currentMonth.equals(getStartYearMonth())) {
            ratio = (getStartDate().lengthOfMonth() - getStartDate().getDayOfMonth() + 1) * 1.0 / (getStartDate().lengthOfMonth() * 1.0);
            if (getStartYearMonth().equals(getEndYearMonth())) {//起訖同月
                Long diffDays = DAYS.between(getStartDate(), getEndDate());
                ratio = (diffDays + 1) * 1.0 / getStartDate().lengthOfMonth();
            }
        } else if (currentMonth.isAfter(getStartYearMonth()) && currentMonth.isBefore(getEndYearMonth())) {//中間
            ratio = 1d;
        } else if (currentMonth.equals(getEndYearMonth())) {//尾巴
            ratio = getEndDate().getDayOfMonth() * 1.0 / getEndDate().lengthOfMonth();
        }
        return ratio;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public YearMonth getStartYearMonth() {
        return YearMonth.from(this.startDate);
    }


    public YearMonth getEndYearMonth() {
        return YearMonth.from(this.endDate);
    }


}