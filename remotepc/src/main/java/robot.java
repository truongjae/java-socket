import java.awt.*;

public class robot {
    public static void main(String[] args) throws AWTException {
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        int x = (int) b.getX();
        int y = (int) b.getY();
        //int x=100,y=400;
        //Robot robot = new Robot();
        //robot.mouseMove(x,y);
        System.out.println(x+"=="+y);
    }
}
