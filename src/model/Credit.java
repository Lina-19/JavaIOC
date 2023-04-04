package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.*;
@Data @AllArgsConstructor  @NoArgsConstructor
public class Credit {
    private Long id;
    private Double capilate_Emprunt;
    private Integer nombre_mois;
    private Double taux_max;
    private Client Demandeur;
    private Double mensualite;




    public Credit(long id, double capilateEmprunt, int nombfreMois, double tauxMax,Client demandeur, double mensualite) {
        this.setId(id);
        this.setCapilate_Emprunt(capilateEmprunt);
        this.setNombre_mois(nombfreMois);
        this.setTaux_max(tauxMax);
        this.setDemandeur(demandeur);
        this.setMensualite(mensualite);
    }

    public void setMensualite(Double mensualite) {
        this.mensualite = mensualite;
    }

    public Long getId() {
        return id;
    }

    public Double getCapilate_Emprunt() {
        return capilate_Emprunt;
    }

    public Double getMensualite() {
        return mensualite;
    }

    public Double getTaux_max() {
        return taux_max;
    }

    public Integer getNombfre_mois() {
        return nombre_mois;
    }

    public Client getNom_Demandeur() {
        return Demandeur;
    }

    @Override
    public String toString() {
        var creditstr="===================================================\n";
        creditstr+="=> Credit n° "+getId()+"                              \n";
        creditstr+="=> Nom de demandeur : "+getNom_Demandeur().nomComplet()+"          \n";
        creditstr+="------------------------------------------------------\n";
        creditstr+="=> Capitale Emprunte     : "+getCapilate_Emprunt()+"   \n";
        creditstr+="=> Nombre de mois           :"+getNombfre_mois()+"    \n";
        creditstr+="=> Taux mensuel            :"+getTaux_max()+"%      \n";
        creditstr+=" => Mensualite      :"+(getMensualite()==0.0 ? "Non_Calcule ": getMensualite()+"DH/mois")+"\n";
        creditstr+="-------------------------------------------------------\n";
        return creditstr;
    }
}
