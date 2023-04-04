package dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Credit;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
@Data
@AllArgsConstructor

@Component("dao")
public class CreditDao implements IDao<Credit,Long> {



    public static Set<Credit> BDCredit(){

   return new HashSet<Credit>(
           Arrays.asList(


   ));

   }
//   public Credit trouverparId(Long id){
//       System.out.println("[DAO - DS volatile ] trouver le credit n° : "+ id);
//       return BDCredit().stream().filter(credi -> credi.getId() == id).findFirst().orElse(null);
//   }

    @Override
    public Credit trouverParId(Long aLong) {
        System.out.println("[DAO - DS volatile ] trouver le credit n° : "+ aLong);
        return BDCredit().stream().filter(credi -> credi.getId() == aLong).findFirst().orElse(null);
    }
}
