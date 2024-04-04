package org.example.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entity.Ad;
import org.example.entity.User;

import java.util.List;

public class AdRepository extends RepositoryManager<Ad, Long>{
    public AdRepository() {
        super(Ad.class);
    }

    public void getAdsByUser(User user){
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Ad> cr = criteriaBuilder.createQuery(Ad.class);
        Root<Ad> root = cr.from(Ad.class);

        cr.select(root).where(criteriaBuilder.equal(root.get("user"), user));

        List<Ad> userAds = getEntityManager().createQuery(cr).getResultList();
        System.out.printf("------------------------------------------------------------------------------------%n");
        System.out.printf("| %-2s | %-15s | %-7s | %-30s | %-15s |%n", "Id", "Title", "Price", "Description", "Category");
        System.out.printf("------------------------------------------------------------------------------------%n");
        for (Ad a : userAds) {
            System.out.printf("| %-2s | %-15s | %-7s | %-30s | %-15s |%n",
                    a.getId(), a.getTitle(), a.getPrice(), a.getDescription(), a.getCategory().getCategory().name());
        }
        System.out.printf("------------------------------------------------------------------------------------%n");
    }

}
