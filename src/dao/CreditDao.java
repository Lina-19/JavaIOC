package dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Client;
import model.Credit;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
@AllArgsConstructor

@Component("dao")
public class CreditDao implements IDao<Credit,Long> {

MySqlSessionFactory factory;

    public CreditDao() {

    }

    public Credit map(ResultSet rs)  {
    try {
        var id=rs.getLong("id");
        var capital =rs.getDouble("capital");
        var nbrMois=rs.getInt("nbrMois");
        var taux =rs.getDouble("taux");
        var demandeur=rs.getInt("demandeur");
        var mensualite=rs.getDouble("mensualite");


        var client=factory.getClientDao().trouverParId((long) demandeur);
        return new Credit(id,capital,nbrMois,taux,client,mensualite);


    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

}

    public static Set<Credit> BDCredit(){

   return new HashSet<Credit>(
           Arrays.asList(


   ));

   }
//   public Credit trouverparId(Long id){
//       System.out.println("[DAO - DS volatile ] trouver le credit n° : "+ id);
//       return BDCredit().stream().filter(credi -> credi.getId() == id).findFirst().orElse(null);
//   }

    @Override
    public Credit trouverParId(Long aLong) {
        Credit credit=null;
        Connection session=factory.getSession();
        PreparedStatement ps=null;
        ResultSet rs=null;

        String sql="SELECT * FROM credit where id=?";
        try {
            ps=Utilitaire.initPS(session,sql,false,aLong);
            rs= ps.executeQuery();
            if(rs.next()) credit=map(rs);
            System.out.println("[SQL] : "+sql);
            return  credit;
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        finally {
            Utilitaire.close(ps,rs);
        }
    }

    @Override
    public List<Credit> findAll() {
        return null;
    }

    @Override
    public Credit save() {
        return null;
    }

    @Override
    public Credit update() {
        return null;
    }

    @Override
    public Boolean delete() {
        return null;
    }

    @Override
    public Boolean deleteById(Long aLong) {
        return null;
    }
}
