import javax.faces.context.FacesContext;
import javax.persistence.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.LinkedList;

@ManagedBean
@SessionScoped
public class PointsBean implements Serializable {
    private EntityManagerFactory emf;
    private Point newPoint = new Point();
    private LinkedList<Point> users_points = new LinkedList<Point>();
    private String sessionID;

    public PointsBean(){
        try{
            Persistence.createEntityManagerFactory("unit");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        sessionID = session.getId();
        users_points = getUsersPoints(sessionID);
    }

    public void addPoint(){
        double x = newPoint.getX();
        double y = newPoint.getY();
        double r = newPoint.getR();
        newPoint.setResult(check_location(x, y, r));
        newPoint.setSessionId(sessionID);
        if (emf != null){
            try {
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                em.persist(newPoint);
                em.getTransaction().commit();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        users_points.addFirst(newPoint);
        newPoint = new Point();
    }

    private boolean check_location(double x, double y, double r){
        return (x >= 0 && y >= 0 && x <= r && y <= r/2)
                || (x >= 0 && y <= 0 && Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r , 2))
                || (x <= 0 && y >= 0 && y - 2*x <= r );
    }
    private LinkedList<Point> getUsersPoints(String sessionID){
        if (emf != null) {
            try {
                EntityManager em = emf.createEntityManager();
                TypedQuery<Point> query = em.createQuery("from Point where sessionId = :id", Point.class);
                query.setParameter("id", sessionID);
                users_points = new LinkedList<Point>(query.getResultList());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return users_points;
    }

    public Point getNewPoint() {
        return newPoint;
    }

    public void setNewPoint(Point newPoint) {
        this.newPoint = newPoint;
    }

    public LinkedList<Point> getUsers_points() {
        return users_points;
    }

    public void setUsers_points(LinkedList<Point> points) {
        this.users_points = points;
    }
}

