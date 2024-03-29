import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.GregorianCalendar;

@Getter
public class TimeZoneConverter {
    private int year = 0;
    private int month = 0;
    private int day = 0;
    private int hour = 0;
    private int min = 0;

    public void setTime(String data) {
        String regex = "^(?<year>\\d{1,4})/(?<month>\\d{1,2})/(?<day>\\d{1,2})/(?<hour>\\d{1,2}):(?<min>\\d{1,2})$";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(data);

        if (m.matches()) {
            this.year = Integer.parseInt(m.group("year"));
            this.month = Integer.parseInt(m.group("month"));
            this.day = Integer.parseInt(m.group("day"));
            this.hour = Integer.parseInt(m.group("hour"));
            this.min = Integer.parseInt(m.group("min"));
        }
    }

    /**
     * int type의 년, 월, 일, 시, 분을 받아 문자열로 변환 후 정리해 반환합니다.
     *
     * @param year  출력할 연도
     * @param month 출력할 월
     * @param day   출력할 일
     * @param hour  출력할 시간
     * @param min   출력할 분
     * @return YYYY/MM/DD/HH:MM 형식의 시간을 String으로 반환합니다.
     */
    public String getFormattedTime(int year, int month, int day, int hour,
                                   int min) {
        String strTime = null;

        String strYear = Integer.toString(year);
        String strMonth = String.format("%02d", month);
        String strDay = String.format("%02d", day);
        String strhour = String.format("%02d", hour);
        String strMin = String.format("%02d", min);

        strTime = strYear + "/" + strMonth + "/" + strDay + "/" + strhour + ":" +
                strMin;
        return strTime;
    }

    public String printOtherCountry(String str) {
        if ("브라질".equals(str))  {
            // 개인처리 내부 메서드 구현
            // 브런치 이름은 개인별 이니셜로 구현
            int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            int cvtYear = 0;
            int cvtMonth = 0;
            int cvtDay = 0;
            int cvtHour = 0;
            int cvtMin = 0;

            cvtHour = this.hour - 12;

            boolean isLateOneDay = (cvtHour < 0);

            boolean isBeginingOfMonth = (this.day == 1);

            boolean isJanuary = (this.month == 1);

            if(isLateOneDay) {
                cvtHour = cvtHour + 24;
                if(!isBeginingOfMonth) {
                    cvtYear = this.year;
                    cvtMonth = this.month;
                    cvtDay = this.day - 1;
                    cvtMin = this.min;
                } else if(isBeginingOfMonth && !isJanuary) {
                    cvtYear = this.year;
                    cvtMonth = this.month - 1;
                    cvtDay = days[this.month - 1];
                    cvtMin = this.min;
                } else if(isBeginingOfMonth && isJanuary) {
                    cvtYear = this.year - 1;
                    cvtMonth = 12;
                    cvtDay = days[12];
                    cvtMin = this.min;
                }
            } else {
                cvtYear = this.year;
                cvtMonth = this.month;
                cvtDay = this.day;
                cvtMin = this.min;
            }

            String brazilTime = this.getFormattedTime(cvtYear, cvtMonth, cvtDay, cvtHour, cvtMin);
            System.out.println("Brazil: " + brazilTime);

            return brazilTime;

        } else if ("영국".equals(str)) {
            // 개인처리 내부 메서드 구현
            // 브런치 이름은 개인별 이니셜로 구현
        } else if ("터키".equals(str)) {
            // 개인처리 내부 메서드 구현
            // 브런치 이름은 개인별 이니셜로 구현
            int t_year = getYear();
            int t_month = getMonth();
            int t_day = getDay();
            int t_hour = getHour() - 6;
            String turkeyTime = " ";

            if(getHour() < 6){
                t_hour = 24 + t_hour;
                if(getDay() == 1){
                    if (t_month == 2 || t_month == 4 || t_month == 6 || t_month == 9 || t_month == 11) {
                        t_day = 31;
                        t_month = t_month - 1;
                    } else if (t_month == 5 || t_month == 7 || t_month == 8 || t_month == 10 || t_month == 12) {
                        t_day = 30;
                        t_month = t_month - 1;
                    } else if (t_month == 1) {
                        t_day = 31;
                        t_month = 12;
                        t_year = t_year - 1;
                    } else if(t_month == 3) {
                        t_day = 28;
                        t_month = t_month - 1;
                    }
                }
                else if((t_month == 1 || t_month == 3 || t_month == 5 || t_month == 7 || t_month == 8 || t_month == 10 || t_month == 12) && (t_day > 31 || t_day < 1)){
                    System.out.println("날짜 입력 오류 !! \n입력 월의 날짜는 1~31까지있습니다.");
                }
                else if((t_month == 4 || t_month == 6 || t_month == 9 || t_month == 11) && (t_day > 30 || t_day < 1)){
                    System.out.println("날짜 입력 오류 !! \n입력 월의 날짜는 1~30까지있습니다.");

                }
                else if(t_month == 2 && (t_day > 28 || t_day < 1)){
                    System.out.println("날짜 입력 오류 !! \n입력 월의 날짜는 1~28까지있습니다.");
                }
                else{
                    t_day = t_day - 1;
                }
            }

            turkeyTime = "Turkey: " + t_year + "-" + t_month + "-" + t_day + "," + t_hour + ":" + getMin();

            System.out.println("Input: " + getYear() + "-" + getMonth() + "-" + getDay() + "," + getHour() + ":" + getMin());
            System.out.println(turkeyTime);

            return turkeyTime;

        } else if ("프랑스".equals(str)) {
            GregorianCalendar gc = new GregorianCalendar();
            int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
            int convertedYear = 0;
            int convertedMonth = 0;
            int convertedDay = 0;
            int convertedHour = 0;
            int convertedMin = 0;
            int timeDiff = -8;

            summerTimeCalculator stc = new summerTimeCalculator();
            stc.setValue(str);
            if (stc.isSummerTimePeriod(this.year, this.month, this.day, this.hour, this.min)) {
                timeDiff = -7;
            }

            convertedHour = this.hour + timeDiff;

            // 시차를 계산했을 때 그 결과값이 음수면 프랑스는 한국보다 하루 늦음.
            boolean isLateOneDay = (convertedHour < 0);

            // n월 1일에서 하루가 늦으면 n-1월의 마지막 일로 가야함.
            boolean isBeginingOfMonth = (this.day == 1);

            // n월 1일 중 3월 1일은 윤년 여부에 따라 일수를 구분해야함.
            boolean isLeapYearAndMarchFirst = (gc.isLeapYear(this.year) && this.month == 3);

            // 1월 1일인 경우 특별히 전년도 12월의 마지막 일로 가야함.
            boolean isJanuary = (this.month == 1);


            if (isLateOneDay) { // 프랑스가 한국보다 하루 늦는다면
                convertedHour = convertedHour + 24;
                if (!isBeginingOfMonth) { // n월 1일이 아니면 해당 값으로 설정.
                    convertedYear = this.year;
                    convertedMonth = this.month;
                    convertedDay = this.day - 1;
                    convertedMin = this.min;
                } else if (isBeginingOfMonth && !isJanuary) { // n월 1일인 경우(1월 제외)
                    convertedYear = this.year;
                    convertedMonth = this.month - 1;
                    convertedDay = days[convertedMonth];
                    if (isLeapYearAndMarchFirst) { // 윤년이고 3월 1일인 경우, 일수를 29일로 설정.
                        convertedDay = convertedDay + 1;
                    }
                    convertedMin = this.min;
                } else if (isBeginingOfMonth && isJanuary) { // 1월 1일인 경우
                    convertedYear = this.year - 1;
                    convertedMonth = 12;
                    convertedDay = days[12];
                    convertedMin = this.min;
                }
            } else { // 프랑스가 한국과 동일한 일이라면,
                convertedYear = this.year;
                convertedMonth = this.month;
                convertedDay = this.day;
                convertedMin = this.min;
            }

            String franceTime = this.getFormattedTime(convertedYear, convertedMonth, convertedDay, convertedHour, convertedMin);
            System.out.println("France: " + franceTime);

            return franceTime;
        } else if ("미국".equals(str)) {
            // 개인처리 내부 메서드 구현
            // 브런치 이름은 개인별 이니셜로 구현
        }

        return "you must set Time";
    }
    /*
     * t.setTime(); : 시간 설정
     *
     * t.printOtherCountry();
     * ex ) @Param str = "영국" 영국의 시간 출력
     * t.printOtherCountry(영국);
     */
    public static void main(String[] args) {
        TimeZoneConverter t = new TimeZoneConverter();

        t.setTime("2022/06/11/13:10");
        t.printOtherCountry("터키");
        t.printOtherCountry("프랑스");
        t.printOtherCountry("브라질");
    }
}