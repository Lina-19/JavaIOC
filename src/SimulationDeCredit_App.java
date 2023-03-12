import dao.CreditDao;
import dao.IDao;
import metier.CreditMetier;
import metier.IMetier;
import model.Credit;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import presentation.CreditController;
import presentation.ICreditController;
import lombok.*;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimulationDeCredit_App {
    static Scanner clavier = new Scanner(System.in);
    static ICreditController creditController;

    private static boolean estUnNombre(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void test1() {
        var dao = new CreditDao();
        var metier = new CreditMetier();
        var controleur = new CreditController();
        metier.setCreditDao(dao);
        controleur.setCreditMetier(metier);
        String rep = "";

        do {
            System.out.println("=> [Test 1] Calcule de mensualite de credit <=\n");
            try {
                String input = "";
                while (true) {
                    System.out.println("=> Enter l'id du credit");
                    input = clavier.nextLine();
                    if (estUnNombre(input)) break;
                    System.err.println("Entre non valide !!");
                }
                long id = Long.parseLong(input);
                controleur.afficher_Mensualite(id);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            System.out.println("Voumez vous quitte (oui/non) ? ");
            rep = clavier.nextLine();
        } while (!rep.equalsIgnoreCase("oui"));
        System.out.println("Au revoir !!");
    }
public static void test2() throws Exception{
        String daoClass;
        String serviceClass;
        String controllerClass;
    Properties  properties=new Properties();
    ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
    InputStream propertiesFile= classLoader.getResourceAsStream("config.properties");
    if(propertiesFile==null) throw new Exception("Fichier config introuvable");
    else{
        try{
            properties.load(propertiesFile);
            daoClass= properties.getProperty("DAO");
            serviceClass=properties.getProperty("SERVICE");
            controllerClass=properties.getProperty("CONTROLLER");
            propertiesFile.close();
        }
        catch(IOException e){
            throw new Exception("Probleme de chargement des proprirtrs du fichier config");
        }
        finally {
            properties.clear();
        }
    }
    try {
        Class cDao = Class.forName(daoClass);
        Class cMetier = Class.forName(serviceClass);
        Class cController = Class.forName(controllerClass);
        var dao = (IDao<Credit, Long>) cDao.getDeclaredConstructor().newInstance();//ca donne de type object dans ce cas on doit le caster
        var metier = (IMetier) cMetier.getDeclaredConstructor().newInstance();
        creditController = (ICreditController) cController.getDeclaredConstructor().newInstance();
        Method setDao = cMetier.getMethod("setCreditDao", CreditDao.class);
        setDao.invoke(metier, dao);
        Method setMetier = cController.getMethod("setCreditMetier", CreditMetier.class);
        setMetier.invoke(creditController, metier);
        creditController.afficher_Mensualite(1L);
    }

    catch (Exception e){
        e.printStackTrace();
    }
}
public static void test3() throws Exception{
ApplicationContext contexte=new ClassPathXmlApplicationContext("spring-ioc.xml");
creditController =(ICreditController) contexte.getBean("controleur");
creditController.afficher_Mensualite(1L);
}

public static void test4() throws Exception{
        ApplicationContext context=new AnnotationConfigApplicationContext("dao","metier","presentation");
        creditController=(ICreditController)context.getBean(ICreditController.class);
        creditController.afficher_Mensualite(1L);
}
public static void main(String[] args)  throws Exception{

    test4();
    }
}