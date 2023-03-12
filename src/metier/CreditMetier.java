package metier;

import dao.CreditDao;
import dao.IDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service("metier")
public class CreditMetier implements IMetier{
    CreditDao creditDao;
    @Autowired
            @Qualifier("dao")
    IDao<Credit,Long> crediDao;
    public Credit calculer_Mensualite(Long idCredit) throws Exception{
        var credit=crediDao.trouverParId(idCredit);
        if(credit==null)
            throw new Exception("L'id du credit est incorrecte :: [ Credit non trouvé ]");
        else{
            double taux=credit.getTaux_max();
            taux=taux/1200;
            double capitale=credit.getCapilate_Emprunt();
            int nbr_mois=credit.getNombfre_mois();

            double mensualite=(capitale*taux)/(1-(Math.pow((1+taux),-1*nbr_mois)));
            mensualite=Math.round(mensualite*100)/100;
            credit.setMensualite(mensualite);
            return credit;
        }
    }

    public void setCreditDao(CreditDao dao) {
        this.creditDao=dao;
    }
}
