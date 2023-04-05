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
        List<Credit> credits = null;
        Connection session = factory.getSession();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String SQL = "SELECT * FROM credit";

        try {
            ps = Utilitaire.initPS(session, SQL, false);
            rs = ps.executeQuery();
            if(rs.next()) credits.add(map(rs));
            System.out.println("[SQL] : " + SQL);
            return credits;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            Utilitaire.close(ps, rs);
        }
        return credits;
    }

    @Override
    public Credit save(Credit credit) {

        Connection session = factory.getSession();
        PreparedStatement ps = null;

        String SQL = "INSERT INTO credit(capital, nbrMois, taux, demandeur, mensualite VALUES (?,?,?,?,?)";
        try {

            ps = Utilitaire.initPS(session,SQL,true,credit.getCapilate_Emprunt(),credit.getNombre_mois(),credit.getTaux_max(),
                    credit.getDemandeur().getId(), credit.getMensualite());

            var statut = ps.executeUpdate();
            if(statut == 0) System.out.println("0 Crédit inséré");
            else
            {
                var rs = ps.getGeneratedKeys();
                var id = rs.getLong(1);
                credit.setId(id);
            }
            System.out.println("[SQL] : " + SQL);
            return credit;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            Utilitaire.close(ps);
        }

        return credit;
    }

    @Override
    public Credit update(Credit credit) {
        Connection session = factory.getSession();
        PreparedStatement ps = null;

        String SQL = "UPDATE credit set capital = ?, nbrMois = ?, taux = ?, demandeur = ?, mensualite = ? WHERE id = ?";
        try {

            ps = Utilitaire.initPS(session,SQL,true,credit.getCapilate_Emprunt(),credit.getNombre_mois(),credit.getTaux_max(),
                    credit.getDemandeur().getId(), credit.getMensualite());

            var statut = ps.executeUpdate();
            if(statut == 0) System.out.println("0 Crédit modifié !");
            System.out.println("[SQL] : " + SQL);
            return credit;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            Utilitaire.close(ps);
        }
        return credit;
    }

    @Override
    public Boolean delete(Credit credit) {
        Connection session = factory.getSession();
        PreparedStatement ps = null;

        String SQL = "DELETE FROM creditr WHERE id = ?";
        try {

            ps = Utilitaire.initPS(session,SQL,true,credit.getId());

            var statut = ps.executeUpdate();
            if(statut == 0) System.out.println("0 Crédit supprimé");

            System.out.println("[SQL] : " + SQL);
            return true;

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            Utilitaire.close(ps);
        }
        return true;
    }

    @Override
    public Boolean deleteById(Long aLong) {
        return null;
    }
}
