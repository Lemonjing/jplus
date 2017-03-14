package com.ryan.time;

import com.ryan.util.Parameters;

import static java.util.Calendar.*;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * {@link Date}s utilities.
 *
 * @author Osman KOCAK
 */
public final class Dates {
    /**
     * Returns a copy of the given {@code Date}.
     *
     * @param date the {@code Date} to copy.
     * @return a copy of the given {@code Date} or {@code null} if it was
     * {@code null}.
     */
    public static Date copy(Date date) {
        return date == null ? null : new Date(date.getTime());
    }

    /**
     * Returns a new {@code Date} representing the current instant.
     *
     * @return a new {@code Date} representing the current instant.
     */
    public static Date now() {
        return new Date();
    }

    /**
     * Returns whether the given {@code Date}s represent the same instant.
     *
     * @param date1 the first {@code Date}.
     * @param date2 the second {@code Date}.
     * @return whether the given {@code Date}s represent the same instant.
     * @throws NullPointerException if one of the arguments is {@code null}.
     */
    public static boolean isSameInstant(Date date1, Date date2) {
        return date1.getTime() == date2.getTime();
    }

    /**
     * Returns whether the given {@code Calendar}s represent the same instant.
     *
     * @param cal1 the first {@code Calendar}.
     * @param cal2 the second {@code Calendar}.
     * @return whether the given {@code Calendar}s represent the same instant.
     * @throws NullPointerException if one of the arguments is {@code null}.
     */
    public static boolean isSameInstant(Calendar cal1, Calendar cal2) {
        return isSameInstant(cal1.getTime(), cal2.getTime());
    }

    /**
     * Returns whether the given {@code Date}s represent the same local time.
     *
     * @param date1 the first {@code Date}.
     * @param date2 the second {@code Date}.
     * @return whether the given {@code Date}s represent the same local time.
     * @throws NullPointerException if one of the arguments is {@code null}.
     */
    public static boolean isSameLocalTime(Date date1, Date date2) {
        return isSameLocalTime(toCalendar(date1), toCalendar(date2));
    }

    /**
     * Returns whether the given {@code Calendar}s represent the same local time.
     *
     * @param cal1 the first {@code Calendar}.
     * @param cal2 the second {@code Calendar}.
     * @return whether the given {@code Calendar}s represent the same local time.
     * @throws NullPointerException if one of the arguments is {@code null}.
     */
    public static boolean isSameLocalTime(Calendar cal1, Calendar cal2) {
        return cal1.get(MILLISECOND) == cal2.get(MILLISECOND)
                && cal1.get(SECOND) == cal2.get(SECOND)
                && cal1.get(MINUTE) == cal2.get(MINUTE)
                && cal1.get(HOUR_OF_DAY) == cal2.get(HOUR_OF_DAY)
                && cal1.get(DAY_OF_YEAR) == cal2.get(DAY_OF_YEAR)
                && cal1.get(YEAR) == cal2.get(YEAR)
                && cal1.get(ERA) == cal2.get(ERA);
    }

    /**
     * Returns whether the given {@code Date}s represent the same day.
     *
     * @param date1 the first {@code Date}.
     * @param date2 the second {@code Date}.
     * @return whether the given {@code Date}s represent the same day.
     * @throws NullPointerException if one of the arguments is {@code null}.
     */
    public static boolean isSameDay(Date date1, Date date2) {
        return isSameDay(toCalendar(date1), toCalendar(date2));
    }

    /**
     * Returns whether the given {@code Calendar}s represent the same day.
     *
     * @param cal1 the first {@code Calendar}.
     * @param cal2 the second {@code Calendar}.
     * @return whether the given {@code Calendar}s represent the same day.
     * @throws NullPointerException if one of the arguments is {@code null}.
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        return cal1.get(ERA) == cal2.get(ERA)
                && cal1.get(YEAR) == cal2.get(YEAR)
                && cal1.get(DAY_OF_YEAR) == cal2.get(DAY_OF_YEAR);
    }

    /**
     * Returns whether the given {@code Date}s represent the same week.
     *
     * @param date1 the first {@code Date}.
     * @param date2 the second {@code Date}.
     * @return whether the given {@code Date}s represent the same week.
     * @throws NullPointerException if one of the arguments is {@code null}.
     */
    public static boolean isSameWeek(Date date1, Date date2) {
        return isSameWeek(toCalendar(date1), toCalendar(date2));
    }

    /**
     * Returns whether the given {@code Calendar}s represent the same week.
     *
     * @param cal1 the first {@code Calendar}.
     * @param cal2 the second {@code Calendar}.
     * @return whether the given {@code Calendar}s represent the same week.
     * @throws NullPointerException if one of the arguments is {@code null}.
     */
    public static boolean isSameWeek(Calendar cal1, Calendar cal2) {
        return cal1.get(ERA) == cal2.get(ERA)
                && cal1.get(YEAR) == cal2.get(YEAR)
                && cal1.get(WEEK_OF_YEAR) == cal2.get(WEEK_OF_YEAR);
    }

    /**
     * Returns whether the given {@code Date}s represent the same month.
     *
     * @param date1 the first {@code Date}.
     * @param date2 the second {@code Date}.
     * @return whether the given {@code Date}s represent the same month.
     * @throws NullPointerException if one of the arguments is {@code null}.
     */
    public static boolean isSameMonth(Date date1, Date date2) {
        return isSameMonth(toCalendar(date1), toCalendar(date2));
    }

    /**
     * Returns whether the given {@code Calendar}s represent the same month.
     *
     * @param cal1 the first {@code Calendar}.
     * @param cal2 the second {@code Calendar}.
     * @return whether the given {@code Calendar}s represent the same month.
     * @throws NullPointerException if one of the arguments is {@code null}.
     */
    public static boolean isSameMonth(Calendar cal1, Calendar cal2) {
        return cal1.get(ERA) == cal2.get(ERA)
                && cal1.get(YEAR) == cal2.get(YEAR)
                && cal1.get(MONTH) == cal2.get(MONTH);
    }

    /**
     * Returns whether the given {@code Date}s represent the same year.
     *
     * @param date1 the first {@code Date}.
     * @param date2 the second {@code Date}.
     * @return whether the given {@code Date}s represent the same year.
     * @throws NullPointerException if one of the arguments is {@code null}.
     */
    public static boolean isSameYear(Date date1, Date date2) {
        return isSameYear(toCalendar(date1), toCalendar(date2));
    }

    /**
     * Returns whether the given {@code Calendar}s represent the same year.
     *
     * @param cal1 the first {@code Calendar}.
     * @param cal2 the second {@code Calendar}.
     * @return whether the given {@code Calendar}s represent the same year.
     * @throws NullPointerException if one of the arguments is {@code null}.
     */
    public static boolean isSameYear(Calendar cal1, Calendar cal2) {
        return cal1.get(ERA) == cal2.get(ERA)
                && cal1.get(YEAR) == cal2.get(YEAR);
    }

    /**
     * Parses a {@code String} representing a date into a {@code Date}
     * instance. The given formats will be used sequentially until a
     * successful parsing is obtained. A parsing is considered successful
     * if it parses the whole input {@code String}.
     *
     * @param date    the {@code String} to parse.
     * @param formats the date format patterns to use.
     * @return the parsed {@code Date}.
     * @throws NullPointerException     if one of the arguments is {@code null}
     *                                  or if {@code formats} contains a {@code null} reference.
     * @throws IllegalArgumentException if {@code formats} is empty or if
     *                                  {@code date} can't be parsed with any of the specified formats.
     */
    public static Date parse(String date, String... formats) {
        return parse(date, Locale.getDefault(), formats);
    }

    /**
     * Parses a {@code String} representing a date into a {@code Date}
     * instance. The given formats will be used sequentially until a
     * successful parsing is obtained. A parsing is considered successful
     * if it parses the whole input {@code String}.
     *
     * @param date    the {@code String} to parse.
     * @param locale  the {@code Locale} to use.
     * @param formats the date format patterns to use.
     * @return the parsed {@code Date}.
     * @throws NullPointerException     if one of the arguments is {@code null}
     *                                  or if {@code formats} contains a {@code null} reference.
     * @throws IllegalArgumentException if {@code formats} is empty or if
     *                                  {@code date} can't be parsed with any of the specified formats.
     */
    public static Date parse(String date, Locale locale, String... formats) {
        Parameters.checkNotNull(date);
        Parameters.checkCondition(formats.length > 0);
        for (String format : formats) {
            DateFormat df = new SimpleDateFormat(format, locale);
            df.setLenient(true);
            ParsePosition position = new ParsePosition(0);
            Date d = df.parse(date, position);
            if (d != null && position.getIndex() == date.length()) {
                return d;
            }
        }
        throw new IllegalArgumentException("Unparseable date: " + date);
    }

    /**
     * Returns the {@code String} representation of the given {@code Date}
     * according to the specified format pattern.
     *
     * @param date   the {@code Date} to format.
     * @param format the format pattern to use.
     * @return the {@code String} representation of {@code date}.
     * @throws NullPointerException if one of the arguments is {@code null}.
     */
    public static String format(Date date, String format) {
        Parameters.checkNotNull(date);
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * Converts a {@code Date} into a {@code Calendar}.
     *
     * @param date the {@code Date} to convert to a {@code Calendar}.
     * @return the created {@code Calendar} instance.
     * @throws NullPointerException if {@code date} is {@code null}.
     */
    public static Calendar toCalendar(Date date) {
        Calendar calendar = getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private Dates() {
        /* ... */
    }
}
