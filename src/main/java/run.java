import kz.javalab.va.dao.DaoFactory;

public class run {
    public static void main(String[] args) {
        DaoFactory factory = null;
        try {
            factory = new DaoFactory();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        factory.DaoFactory();
    }

}
