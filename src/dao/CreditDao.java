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
@NoArgsConstructor
@Component("dao")
public class CreditDao implements IDao<Credit,Long> {

   public static Set<Credit> BDCredit(){
   return new HashSet<Credit>(
           Arrays.asList(
           new Credit(1L,3000000.0,120,2.5,"Amine",0.0),
           new Credit(2L,850000.0,240,2.5,"Tarek",0.0),
           new Credit(3L,20000.0,30,1.5,"Sarah",0.0),
           new Credit(4L,650000.0,60,2.0,"Tanae",0.0)
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
