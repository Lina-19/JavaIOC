package dao;

import model.Client;
import model.Credit;
import model.Utilisateur;

public abstract class DaoFactory {
    public static final int MYSQL_DATA_UNIT=1,FILE_DATA_UNIT=2,InMEMORY_DATA_UNIT=3;

    public abstract IDao<Utilisateur,Long> getUtilisateurDao();
    public abstract IDao<Client,Long> getClientDao();
    public abstract IDao<Credit,Long> getCreditDao();

    public static final DaoFactory getDaoFactory(int factorytype){
        switch (factorytype){case 1 : return MySqlSessionFactory.getInstance();
            default: return null;
    }

}}
