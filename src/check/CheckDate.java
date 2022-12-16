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

    public boolean isValidFormat(String date, String month, String year) {
        String Date = date.concat("-").concat(month).concat("-").concat(year);
        if (datePattern.matcher(Date).matches()) {
            return true;
        }
        return false;
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
            if (month == 2) {
                if (((year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)))) {
                    if (date > 29) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    if (date > 28) {
                        return false;
                    } else {
                        return true;
                    }
                }
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (date > 30) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }
    }

    public int checkDateString(int date, int month, int year) {
        int result = -1;
        if (date < 1 || date > 31 || month < 1 || month > 12) {
            return result = 0;
        } else {
            if (month == 2) {
                if (((year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)))) {
                    if (date > 29) {
                        return result = 1;
                    } else {
                        return result = 2;
                    }
                } else {
                    if (date > 28) {
                        return result = 3;
                    } else {
                        return result = 4;
                    }
                }
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (date > 30) {
                    return result = 5;
                } else {
                    return result = 6;
                }
            } else {
                return result = 8;
            }
        }
        //return result;
    }

    public boolean isValidDate(String Date, String Month, String Year) {
        int date;
        int month;
        int year;
        String[] textGetDate = returnDate().split("-");
        int currentDay = Integer.parseInt(textGetDate[2]);
        int currentMonth = Integer.parseInt(textGetDate[1]);
        int currentYear = Integer.parseInt(textGetDate[0]);
        if (Date.equals("")) {
            year = Integer.parseInt(Year);
            if (Month.equals("")) {
                if (year > currentYear) {
                    return false;
                } else {
                    return true;
                }
            } else {
                if (year > currentYear) {
                    return false;
                } else if (year == currentYear) {
                    month = Integer.parseInt(Month);
                    if (month > currentMonth) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        } else {
            date = Integer.parseInt(Date);
            month = Integer.parseInt(Month);
            year = Integer.parseInt(Year);
            if (year > currentYear) {
                return false;
            } else if (year == currentYear) {
                if (month > currentMonth) {
                    return false;
                } else if (month == currentMonth) {
                    if (date > currentDay) {
                        return false;
                    } else {
                        return checkDate(date, month, year);
                    }
                } else {
                    return checkDate(date, month, year);
                }

            } else {
                return checkDate(date, month, year);
            }

        }

    }

  
}
//check = true;

