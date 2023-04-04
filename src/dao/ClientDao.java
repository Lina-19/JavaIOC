package dao;

import model.Client;
import model.Credit;
import model.Role;
import model.Sexe;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ClientDao { public static Set<Client> BDCredit(){
    return new HashSet<Client>(
            Arrays.asList(
                   new Client(1L,"test","test", Role.CLIENT,"testnom","testprenom","test@gmail.com","zef6d354","365463436", Sexe.Femme)
            ));

}

}
