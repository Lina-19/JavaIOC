package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.*;
@Data @AllArgsConstructor  @NoArgsConstructor
public class Credit {
    private Long id;
    private Double capilate_Emprunt;
    private Integer nombfre_mois;
    private Double taux_max;
    private String nom_Demandeur;
    private Double mensualite;

    public Credit(long l, double v, int i, double v1, String nom, double v2) {
        this.id=l;
        this.capilate_Emprunt=v;
        this.nombfre_mois=i;
        this.taux_max=v1;
        this.nom_Demandeur=nom;
        this.mensualite=v2;
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
        return nombfre_mois;
    }

    public String getNom_Demandeur() {
        return nom_Demandeur;
    }

    @Override
    public String toString() {
        var creditstr="===================================================\n";
        creditstr+="=> Credit n° "+getId()+"                              \n";
        creditstr+="=> Nom de demandeur : "+getNom_Demandeur()+"          \n";
        creditstr+="------------------------------------------------------\n";
        creditstr+="=> Capitale Emprunte     : "+getCapilate_Emprunt()+"   \n";
        creditstr+="=> Nombre de mois           :"+getNombfre_mois()+"    \n";
        creditstr+="=> Taux mensuel            :"+getTaux_max()+"%      \n";
        creditstr+=" => Mensualite      :"+(getMensualite()==0.0 ? "Non_Calcule ": getMensualite()+"DH/mois")+"\n";
        creditstr+="-------------------------------------------------------\n";
        return creditstr;
    }
}
