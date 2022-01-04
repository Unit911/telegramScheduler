import org.sikuli.script.*;

public class Main {

    static int[] days = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static Integer sentThisMessageLocationX;
    static Integer sentThisMessageLocationY;
    static Screen s;

    static int startMonth = 3;
    static int startDay = 3;

    static Integer endMonth = 3;
    static Integer endDay = 12;

    public static void main(String[] args) throws FindFailed {
        s = new Screen();

        ImagePath.add(System.getProperty("user.dir")+"/src/main/resources/");
        for (int i = startMonth; i < days.length; i++) {
            for (int j = 1; j <= days[i]; j++) {
                if (i == startMonth && j == 1 ) {
                    j = startDay;
                }
                if (i == endMonth && j == endDay) {
                    return;
                }
                System.out.println(String.format("Addint task for month %s and day %s", i, j));
                s.click("writeMessage.png");
                s.type("/pidor");
                s.click("send.png");

                clickDate();
                s.wait("downArrow.png");
                for (int k = 0; k < i; k++) {
                    s.click("downArrow.png");
                }
                s.click(String.format("%s.png", j));

                selectHours();
                selectMinutes();

                s.click("schedule.png");
            }
        }


    }

    private static void clickDate() throws FindFailed {
        int x = getSentThisMessageLocationX();
        int y = getSentThisMessageLocationY();
        Location l = new Location(x + 100, y + 80);
        s.click(l);
    }

    private static void selectHours() throws FindFailed {
        int x = getSentThisMessageLocationX();
        int y = getSentThisMessageLocationY();
        Location l = new Location(x + 250, y + 80);
        s.doubleClick(l);
        s.type("00");
    }

    private static void selectMinutes() throws FindFailed {
        int x = getSentThisMessageLocationX();
        int y = getSentThisMessageLocationY();
        Location l = new Location(x + 285, y + 80);
        s.doubleClick(l);
        s.type("01");
    }

    private static Integer getSentThisMessageLocationX() throws FindFailed {
        if (sentThisMessageLocationX == null) {
            findSentThisMessageLocation();
        }
        return sentThisMessageLocationX;
    }

    private static Integer getSentThisMessageLocationY() throws FindFailed {
        if (sentThisMessageLocationY == null) {
            findSentThisMessageLocation();
        }
        return sentThisMessageLocationY;
    }

    private static void findSentThisMessageLocation() throws FindFailed {
        s.wait("C:\\DEV\\Project\\sikuliTele\\src\\main\\resources\\sendThisMessageOn.png");
        Match m = s.find("C:\\DEV\\Project\\sikuliTele\\src\\main\\resources\\sendThisMessageOn.png");

        sentThisMessageLocationX = m.getX();
        sentThisMessageLocationY = m.getY();
    }


}