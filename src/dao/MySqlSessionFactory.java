package dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Client;
import model.Credit;
import model.Utilisateur;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class MySqlSessionFactory extends DaoFactory{
    private static MySqlSessionFactory INSTANCE= null;
    private  static Connection session=null;
    private static IDao<Client,Long> CLIENT_DAO=null;
    private static IDao<Credit,Long> CREDIT_DAO=null;
    private static IDao<Utilisateur,Long> USER_DAO=null;


    public MySqlSessionFactory () throws IOException, SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ClassLoader cl =Thread.currentThread().getContextClassLoader();
        var config =cl.getResourceAsStream("application.properties");
        if(config==null)
            throw new IOException("fichier introuvable");

        Properties propertiesFile = new Properties();
        propertiesFile.load(config);
        var url = propertiesFile.getProperty("URL");
        var user = propertiesFile.getProperty("USERNAME");
        var pass=propertiesFile.getProperty("PASSWORD");

        session= DriverManager.getConnection(url,user,pass);
        System.out.println("Connexion etablit avec succès");

        var creditDao=propertiesFile.getProperty("CREDITDAO");
        var clientDao=propertiesFile.getProperty("CLIENTDAO");
        var utilisateurDao=propertiesFile.getProperty("USERDAO");

        Class cCreditDao=Class.forName(creditDao);
        Class cClientDao =Class.forName(clientDao);
        Class cUserDao=Class.forName(utilisateurDao);

        CREDIT_DAO=(IDao<Credit, Long>) cCreditDao.getDeclaredConstructor().newInstance();
        CLIENT_DAO=(IDao<Client,Long>) cClientDao.getDeclaredConstructor().newInstance();
        USER_DAO=(IDao<Utilisateur, Long>) cUserDao.getDeclaredConstructor().newInstance();


        Method setFactory=cCreditDao.getMethod("setFactory",DaoFactory.class);
        setFactory.invoke(CREDIT_DAO,this);
        setFactory=cClientDao.getMethod("setFactory",DaoFactory.class);
        setFactory.invoke(CLIENT_DAO,this);
        setFactory=cUserDao.getMethod("setFactory",DaoFactory.class);
        setFactory.invoke(USER_DAO,this);




    }

    public static MySqlSessionFactory getInstance(){
if (INSTANCE==null) {
    try {
        INSTANCE = new MySqlSessionFactory();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    } catch (InvocationTargetException e) {
        throw new RuntimeException(e);
    } catch (NoSuchMethodException e) {
        throw new RuntimeException(e);
    } catch (InstantiationException e) {
        throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
    }

}
return INSTANCE;
    }
    public static Connection getSession() {
        if (session == null) getInstance();
        return session;
    }
    @Override
    public IDao<Utilisateur, Long> getUtilisateurDao() {
        if(USER_DAO==null) getInstance();
        return USER_DAO;
    }

    @Override
    public IDao<Client, Long> getClientDao() {
       if(CLIENT_DAO==null) getInstance();
       return CLIENT_DAO;
    }

    @Override
    public IDao<Credit, Long> getCreditDao() {
        if(CREDIT_DAO==null) getInstance();
        return  CREDIT_DAO;
    }
    public static void closeSession(){
    if(session!=null){
        try {
            session.close();System.out.println("Fremeture de session avec succès");
        } catch (SQLException e) {
            System.out.println("Fermeture de session échoué");
        }


    }
}}
