package BD;

import lombok.Data;
import model.Client;
import model.Credit;

import java.sql.*;
import java.util.ArrayList;

@Data
public class ApprocheJDBC {

    public static void main(String[] args)  {
        String  url="jdbc:mysql://localhost:3306/bankati",
                username="root",
                pass="",
                Driver="com.mysql.cj.jdbc.Driver";
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        ArrayList credits=new ArrayList<Credit>();
        ArrayList clients =new ArrayList<Client>();

        try {
            Class.forName(Driver);
            System.out.println("le Driver Mysql a ete ajouté par succès");
            connection= DriverManager.getConnection(url,username,pass);
            System.out.println("connexion etablie a la bd etablie");
//            statement=connection.createStatement();
//            statement.executeQuery("SELECT * from Credit");
//            statement= connection.prepareStatement("SELECT * from Credit where id=? ");
//            statement.setLong(1,2L);
            statement= connection.prepareStatement("SELECT * from credit");
           rs= statement.executeQuery();
           while (rs.next()){
              var id = rs.getLong("id");
              var capital=rs.getDouble("capitale");
              var nbrMois=rs.getInt("nbrMois");
              var taux=rs.getDouble("taux") ;
              var Demandeur=rs.getLong("demandeur");
               var nomdemandeur=rs.getString("nom");
               var prenomdemandeur=rs.getString("prenom");
               var mensualite=rs.getDouble("mensualite");

               var client=new Client();
               client.nomComplet(nomdemandeur,prenomdemandeur);
               credits.add(new Credit(id,capital,nbrMois,taux,client,mensualite));



              Credit credit =null; //new Credit(id,capilate_Emprunt,nombfre_mois,taux_max,Demandeur,mensualite);

              credits.add(credit);
           }
credits.forEach(System.out::println);
        } catch (ClassNotFoundException e) {
            System.err.println("le Driver MYSQL est introuvable");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(connection!=null){
                try {
                    connection.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
