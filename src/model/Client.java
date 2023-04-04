package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client extends Utilisateur{
    String email,cin,tel;
    Sexe sexe;

    public Client(long l, String test, String test1,Role role, String testnom, String testprenom, String s, String zef6d354, String s1, Sexe femme) {
    this.setId(l);
    this.setLogin(test);
    this.setPass(test1);
    this.setRole(role);
    this.setNom(testnom);
    this.setPrenom(testprenom);
    this.setEmail(s);
    this.setCin(zef6d354);
    this.setTel(s1);
    this.setSexe(femme);
    }

    @Override
    public String toString() {
        var creditstr="===================================================\n";
        creditstr+="=> nom  "+getNom()+"                              \n";
        creditstr+="=> prenom : "+getPrenom()+"          \n";
        creditstr+="------------------------------------------------------\n";
        creditstr+="=> email     : "+getEmail()+"   \n";
        creditstr+="------------------------------------------------------\n";
        creditstr+="=> cin     : "+getCin()+"   \n";
        creditstr+="-------------------------------------------------------\n";
        return creditstr;
    }
}
