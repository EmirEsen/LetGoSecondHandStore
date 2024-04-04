package org.example.service;

import org.example.entity.Ad;
import org.example.entity.Category;
import org.example.entity.User;
import org.example.entity.enums.Categories;
import org.example.repository.AdRepository;
import org.example.util.Util;

import java.sql.Timestamp;

public class AdService {
    AdRepository adRepository;

    public AdService() {
        this.adRepository = new AdRepository();
    }

    public void publishAd(User user){
        Categories category = selectCategory();
        String title = Util.stringScanner("Title: ");
        String description = Util.stringScanner("Description: ");
        String location = Util.stringScanner("Location: ");
        Integer price = Util.intScanner("Price: ");

       Ad ad = Ad.builder()
                .category(Category.builder().category(category)
                        .build())
               .title(title.toUpperCase())
               .description(description)
               .location(location)
               .price(price)
               .user(user)
               .build();


       adRepository.save(ad);
    }


    private Categories selectCategory(){
        System.out.println("Select Category: ");
        for (Categories c : Categories.values()) {
            System.out.printf("%s - %s\n", c.ordinal(), c.name());
        }
        int input = Util.intScanner("Input: ");
        return Categories.values()[input];
    }

    public void listUserAds(User user) {
        adRepository.getAdsByUser(user);
    }

    public void showAllAds(){
        System.out.printf("--------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-2s | %-10s | %-15s | %-7s | %-30s | %-15s |%n", "Id", "Seller", "Title", "Price", "Description", "Category");
        System.out.printf("--------------------------------------------------------------------------------------------------%n");
        adRepository.findAll().forEach(ad -> {
                System.out.printf("| %-2s | %-10s | %-15s | %-7s | %-30s | %-15s |%n",
                        ad.getId(),ad.getUser().getUsername(), ad.getTitle(), ad.getPrice(), ad.getDescription(), ad.getCategory().getCategory().name());
        });
        System.out.printf("--------------------------------------------------------------------------------------------------%n");
    }

    public Ad getAdById(Long id){
        return adRepository.findById(id).orElse(null);
    }



}
