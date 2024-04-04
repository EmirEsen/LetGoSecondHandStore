package org.example.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entity.Ad;
import org.example.entity.FavoriteAd;
import org.example.entity.User;

import java.util.List;

public class FavoriteAdRepository extends RepositoryManager<FavoriteAd, Long>{
    public FavoriteAdRepository() {
        super(FavoriteAd.class);
    }

    public List<Ad> getFavoriteAdsByUser(User user){
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<FavoriteAd> cr = criteriaBuilder.createQuery(FavoriteAd.class);
        Root<FavoriteAd> root = cr.from(FavoriteAd.class);

        cr.select(root).where(criteriaBuilder.equal(root.get("user"), user));

       List<Ad> userFavoriteAds = getEntityManager().createQuery(cr).getResultList()
                .stream()
                .map(FavoriteAd::getAd)
                .toList();
        System.out.printf("--------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-2s | %-10s | %-15s | %-7s | %-30s | %-15s |%n", "Id", "Seller", "Title", "Price", "Description", "Category");
        System.out.printf("--------------------------------------------------------------------------------------------------%n");
        for (Ad ad : userFavoriteAds) {
            System.out.printf("| %-2s | %-10s | %-15s | %-7s | %-30s | %-15s |%n",
                    ad.getId(),ad.getUser().getUsername(), ad.getTitle(), ad.getPrice(), ad.getDescription(), ad.getCategory().getCategory().name());
        }
        System.out.printf("--------------------------------------------------------------------------------------------------%n");
        return userFavoriteAds;
    }

}
