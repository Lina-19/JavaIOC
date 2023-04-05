package BD;

import metier.Singleton;
import model.Client;
import model.Credit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class JDBC_Test {
    public static void main(String[] args)  {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.printf("le Driver jdbs Mysql a ete ajouté par succès");
//
//            DriverManager.getConnection("jdbc:mysql://localhost:3306/bankati","root","");
//            System.out.println("Connexion etablit avec succès ^_^");
//
//        } catch (ClassNotFoundException e) {
//            System.err.println("Driver jdbc Mysql introuvale");
//        } catch (SQLException e) {
//            System.err.println("Connexion échoué ");
//        }


        Connection connection= Singleton.getSession();
        var credits =new ArrayList<Credit>();
        try {
        ClassLoader cl =Thread.currentThread().getContextClassLoader();
        var config =cl.getResourceAsStream("application.properties");
        if(config==null)
            throw new IOException("fichier introuvable");

            Properties propertiesFile = new Properties();
            propertiesFile.load(config);
            var url = propertiesFile.getProperty("URL");
            var user = propertiesFile.getProperty("USERNAME");
            var pass=propertiesFile.getProperty("PASSWORD");

            connection=DriverManager.getConnection(url,user,pass);
            System.out.println("Connexion etablit avec succès");

            var ps=connection.prepareStatement(
                    "select * from credit cr,client cl,utilisateur u"

            );
            var rs=ps.executeQuery();

//Creation du statement et du ResultSet
//            var statement= connection.createStatement();
//            var rs=statement.executeQuery(
//                    "select cr.id, cr.capital, cr.nbrMois, cr.taux, cr.demandeur, cr.mensualite, u.nom, u.prenom " +
//                            "FROM credit cr, client cl, utilisateur u "
//
//            );

            while (rs.next()){
                var id=rs.getLong("id");
                var capital =rs.getDouble("capital");
                var nbrMois=rs.getInt("nbrMois");
                var taux =rs.getDouble("taux");
                var nomdemandeur=rs.getString("nom");
                var prenomdemandeur=rs.getString("prenom");
                var mensualite=rs.getDouble("mensualite");

                var client=new Client();
                client.nomComplet(nomdemandeur,prenomdemandeur);
                credits.add(new Credit(id,capital,nbrMois,taux,client,mensualite));
            }
            if(credits.isEmpty())throw new SQLException("Aucun credit trouvé");
            else credits.forEach(System.out::println);


        } catch (IOException e) {
           e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
//            System.err.println("Connexion échoué");
        }
      Singleton.closeSession();


    }
}
