/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EduSys.uitils;

import com.EduSys.entity.NhanVien;
import java.text.*;
import java.util.Date;

/**
 *
 * @author Lê Minh Khôi
 */
public class XDate {
    static SimpleDateFormat formater = new SimpleDateFormat();
    public static Date toDate(String date, String pattern){
        try{
            formater.applyPattern(pattern);
            return formater.parse(date);
        }catch(ParseException e){
            throw new RuntimeException(e);
        }
    }
    public static String toString(Date date,String pattern){
        formater.applyPattern(pattern);
        return formater.format(date);
    }
    public static Date addDate(Date date, long days){
        date.setTime(date.getTime() + days *24*60*60*1000);
        return date;
    }
}
