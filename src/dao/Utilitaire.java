package dao;

import java.sql.*;

public class Utilitaire {
    public static PreparedStatement initPS(Connection cnx,String SQL, boolean generateKey, Object... Columns) throws SQLException {
        PreparedStatement PS=null;
        PS=cnx.prepareStatement(SQL,generateKey ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        for(int i=0 ; i<Columns.length;i++){
            PS.setObject(i+1,Columns[i]);
        }
        return  PS;
    }


    public static void close (PreparedStatement ps){
        if(ps!=null){
            try {
                ps.close();System.out.println("Fermeture de l'objet statement avec succès");
            } catch (SQLException e) {
                System.err.println("Fermeture de l'objet statement echoué");
            }

        }
    }
    public static void close(ResultSet rs){
        if(rs!=null){{
            try {
                rs.close();System.out.println("Fermeture de l'objet resultset avec succès");
            } catch (SQLException e) {
                System.err.println("Fermeture de l'objet resultset echoué");
            }

        }}
    }

    public static void close(PreparedStatement ps, ResultSet rs)  {
        try {
            ps.close();System.out.println("Fermeture de l'objet statement avec succès");
            rs.close();System.out.println("Fermeture de l'objet resultset avec succès");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
