package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {
    private Long id;
    protected String nom,prenom,login,pass;
    Role role;
    public String nomComplet(){return getNom()+" "+getPrenom();}
    @Override
    public String toString() {
        var creditstr="===================================================\n";
        creditstr+="=> Credit n° "+getId()+"                              \n";
        creditstr+="------------------------------------------------------\n";
        creditstr+="=> Nom Complet n° "+nomComplet()+"                              \n";
        creditstr+="=> login : "+getLogin()+"          \n";
        creditstr+="------------------------------------------------------\n";
        creditstr+="=> pass     : "+getPass()+"   \n";
        creditstr+="------------------------------------------------------\n";
        creditstr+="=> role     : "+getRole()+"   \n";
        creditstr+="-------------------------------------------------------\n";
        return creditstr;
    }

    public void nomComplet(String nomdemandeur, String prenomdemandeur) {
    this.setNom(nomdemandeur);
    this.setPrenom(prenomdemandeur);
    }
}
