/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package check;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class CheckDate {

    Pattern datePattern = Pattern.compile("^\\d{2}-\\d{2}-\\d{4}$");

    public boolean isValidFormat(String dateString) {
        if (datePattern.matcher(dateString).matches()) {
            return true;
        }
        return false;
    }
     public void ValidFormat(String dateString) {
        if (datePattern.matcher(dateString).matches()) {
            System.out.println("Dung pattern");
        }
        System.out.println("Sai pattern");
    }
    

    public boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean checkIntOfArray(String[] list) {
        for (String string : list) {
            if (isInt(string) == false) {
                return false;
            }
        }
        return true;
    }

    public String returnDate() {
        //  DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate currentDate = LocalDate.now();
        return currentDate.toString();

    }

    public boolean checkDate(int date, int month, int year) {
        if (date < 1 || date > 31 || month < 1 || month > 12) {
            return false;
        } else {
            if (month == 2 && ((year % 400 == 0 || (year % 4 == 0 && year % 100 == 0)))) {
                if (date > 29) {
                    return false;
                } else {
                    return true;
                }
            } else if (month == 2) {
                if (date > 28) {
                    return false;
                } else {
                    return true;
                }
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (date > 30) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return true;
    }

    public boolean isValidDate(String x) {
        if (isValidFormat(x) == false) {
            return false;
        } else {
            String[] text = x.split("-");
            if (checkIntOfArray(text) == false) {
                return false;
            } else {
                int date = Integer.parseInt(text[0]);
                int month = Integer.parseInt(text[1]);
                int year = Integer.parseInt(text[2]);
                String[] textGetDate = returnDate().split("-");
                int currentDay = Integer.parseInt(textGetDate[2]);
                int currentMonth = Integer.parseInt(textGetDate[1]);
                int currentYear = Integer.parseInt(textGetDate[0]);
                if (year > currentYear) {
                    return false;
                } else if (year == currentYear) {
                    if (month > currentMonth) {
                        return false;
                    } else if (month == currentMonth) {
                        if (date > currentDay) {
                            return false;
                        } else {
                            return true;
                        }
                    } 
                    else {
                       // month < currentMonth
                       return checkDate(date,month,year);
                    }
                } else {
                    // year < currentYear
                    return checkDate(date,month,year);
                }
            }
        }
    }
    public void validDate(String x) {
        if (isValidFormat(x) == false) {
            System.out.println("Nhap khong dung ngay o dau");
        } else {
            String[] text = x.split("-");
            if (checkIntOfArray(text) == false) {
                System.out.println("Nhap khong dung 2");
            } else {
                int date = Integer.parseInt(text[0]);
                int month = Integer.parseInt(text[1]);
                int year = Integer.parseInt(text[2]);
                String[] textGetDate = returnDate().split("-");
                int currentDay = Integer.parseInt(textGetDate[2]);
                int currentMonth = Integer.parseInt(textGetDate[1]);
                int currentYear = Integer.parseInt(textGetDate[0]);
                if (year > currentYear) {
                    System.out.println("Lon hon nam hien tai");
                } else if (year == currentYear) {
                    if (month > currentMonth) {
                        System.out.println("Lon hon thang hien tai");
                    } else if (month == currentMonth) {
                        if (date > currentDay) {
                            System.out.println("Lon hon ngay hien tai");
                        } else {
                            System.out.println("Cung nam cung thang");
                        }
                    } 
                    else {
                       // month < currentMonth
                       if(checkDate(date,month,year)==false){
                          System.out.println("Nhap khong dung 3");
                       }else{
                           System.out.println("Dung 1");
                       }
                    }
                } else {
                    // year < currentYear
                     if(checkDate(date,month,year)==false){
                          System.out.println("Nhap khong dung 4");
                       }else{
                           System.out.println("Dung 2");
                       }
                }
            }
        }
    }

}

//  check = true;

