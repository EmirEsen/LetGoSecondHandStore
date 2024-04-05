package org.example.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entity.Ad;
import org.example.entity.FavoriteAd;
import org.example.entity.User;
import org.example.repository.FavoriteAdRepository;

import java.util.List;
import java.util.Optional;import java.util.stream.Collectors;

public class FavoriteAdService {

    FavoriteAdRepository favoriteAdRepository = new FavoriteAdRepository();


    public Optional<FavoriteAd> addToFavorites(User user, Ad ad) {
        FavoriteAd favoriteAd = FavoriteAd
                .builder()
                .user(user)
                .ad(ad)
                .build();
        if (favoriteAdRepository.findByColumnAndValue("ad", ad).isEmpty()) {
            return Optional.ofNullable(favoriteAdRepository.save(favoriteAd));
        }
        return Optional.empty();

    }

    public boolean removeFromFavorites(Ad ad) {
        if (!favoriteAdRepository.findByColumnAndValue("ad", ad).isEmpty()) {
            return favoriteAdRepository.deleteById(favoriteAdRepository.findByColumnAndValue("ad", ad).getFirst().getId());
        }
        return false;
    }

    public List<FavoriteAd> findFavoriteAdByAd(Ad ad) {
        return favoriteAdRepository.findByColumnAndValue("ad", ad);
    }

    public List<Ad> listUserFavoriteAds(User user) {
        List<Ad> favoriteAdsByUser = favoriteAdRepository.getFavoriteAdsByUser(user);
        if (favoriteAdsByUser.isEmpty()) {
            System.out.println("You have no favorite ads.");
            return favoriteAdsByUser;
        }
        return favoriteAdsByUser;

    }
}
