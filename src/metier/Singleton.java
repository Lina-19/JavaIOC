package metier;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Singleton {
    private static Singleton INSTANCE=null;
    private static Connection session=null;
    private Singleton(){
        try{
            ClassLoader cl=Thread.currentThread().getContextClassLoader();
            var config=cl.getResourceAsStream("application.propeties");
            if(config==null) throw new IOException("Fichier introuvable");
            Properties propertiesFile=new Properties();
            propertiesFile.load(config);
            var url=propertiesFile.getProperty("URL");
            var lg=propertiesFile.getProperty("USERNAME");
            var pass=propertiesFile.getProperty("PASSWORD");

            session= DriverManager.getConnection(url,lg,pass);
            System.out.println("Connexion etablie");
        } catch (IOException e) {
           e.printStackTrace();
        } catch (SQLException e) {
             System.err.println("Connexion echoue");
        }
    }
    public static Singleton getInstance(){
        if(INSTANCE==null) INSTANCE=new Singleton();
        return INSTANCE;
    }
    public static  Connection getSession(){
        if(session==null) getInstance();
        return session;
    }
    public static void closeSession(){
        if(session!=null){
            try{
                session.close();
                System.out.println("Fermeture de session avec succès");

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
