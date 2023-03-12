package presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import metier.CreditMetier;
import metier.IMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Controller
public class CreditController implements ICreditController {
    CreditMetier creditMetier;

    @Autowired
            @Qualifier("metier")
    IMetier crediMetier;
//    public void afficher_Mensualite(long idCredit) throws Exception{
//        var creditavecMensualite=creditMetier.calculer_Mensualite(idCredit);
//        System.out.println(creditavecMensualite);
//    }

    @Override
    public void afficher_Mensualite(Long id) throws Exception {
        var creditavecMensualite=crediMetier.calculer_Mensualite(id);
        System.out.println(creditavecMensualite);
    }

    public void setCreditMetier(CreditMetier metier) {
        this.creditMetier=metier;
    }
}
