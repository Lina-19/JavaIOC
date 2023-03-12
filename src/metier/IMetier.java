package metier;

import model.Credit;

public interface IMetier {
    Credit calculer_Mensualite(Long idCredit) throws Exception;
}
