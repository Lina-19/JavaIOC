package BD;

import dao.ClientDao;
import dao.CreditDao;
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
        ResultSet resultSet=null;
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
           resultSet= statement.executeQuery();
           while (resultSet.next()){
              var id = resultSet.getLong("id");
              var capilate_Emprunt=resultSet.getDouble("capilate");
              var nombfre_mois=resultSet.getInt("nbrMois");
              var taux_max=resultSet.getDouble("taux") ;
              var Demandeur=resultSet.getLong("demandeur");
              var mensualite=resultSet.getDouble("mensualite");



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
