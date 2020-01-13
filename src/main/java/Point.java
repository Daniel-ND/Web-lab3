import org.decimal4j.util.DoubleRounder;

import javax.persistence.*;

@Entity
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private double x = 0;
    private double y;
    private double r = 3;
    private boolean result;
    private String sessionId;

    public Point() {
    }

    public Point(String sessionId, double x, double y, double r, boolean result) {
        this.sessionId = sessionId;
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public boolean getResult() {
        return result;
    }

    public void setX(double x) {
        this.x = DoubleRounder.round(x, 3);
    }

    public void setY(double y) {
        this.y = DoubleRounder.round(y, 3);
    }

    public void setR(double r) {
        this.r = DoubleRounder.round(r, 0);;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getId() {
        return id;
    }

}
