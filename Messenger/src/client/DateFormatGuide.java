/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author itcde
 */
public class DateFormatGuide {

    public static String FROM_YMD_TO_DMY = "dd/MM/yyyy";
    public static String FROM_DMY_TO_YMD = "yyyy/MM/dd";
    public static String NEW_DATE = "";

    public DateFormatGuide(String date, String format) {
        if (format.equals(DateFormatGuide.FROM_DMY_TO_YMD)) {
            DateFormatGuide.NEW_DATE = FROM_DMY_TO_YMD(date);
        } else if (format.equals(DateFormatGuide.FROM_YMD_TO_DMY)) {
            DateFormatGuide.NEW_DATE = FROM_YMD_TO_DMY(date);
        }
    }

    public static String FROM_YMD_TO_DMY(String date) {

        try {
            //Lấy ra day, month, year để chuyển đổi. Và giữ phần còn lại của "date"(nếu có)
            String year = date.substring(0, 4);
            String month = date.substring(5, 7);
            String day = date.substring(8, 10);
            String rest = date.substring(10, date.length());
            //Chuỗi ngày mới với định dạng dd/MM/yyyy
            String newDate = day + "/" + month + "/" + year + rest;
            return newDate;
        } catch (Exception e) {
            return date;
        }

    }

    public static String FROM_DMY_TO_YMD(String date) {

        try {
            //Lấy ra day, month, year để chuyển đổi. Và giữ phần còn lại của "date"(nếu có)
            String year = date.substring(6, 10);
            String month = date.substring(3, 5);
            String day = date.substring(0, 2);
            String rest = date.substring(10, date.length());
            //Chuỗi ngày mới với định dạng yyyy/MM/dd
            String newDate = year + "/" + month + "/" + day + rest;
            return newDate;
        } catch (Exception e) {
            return date;
        }
    }
}
