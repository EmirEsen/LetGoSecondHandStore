package org.example;

import org.example.entity.Ad;
import org.example.entity.FavoriteAd;
import org.example.entity.User;
import org.example.service.AdService;
import org.example.service.FavoriteAdService;
import org.example.service.MessageService;
import org.example.service.UserService;
import org.example.util.Util;
import java.util.Optional;

public class Main {
    static UserService userService = new UserService();
    static AdService adService = new AdService();
    static FavoriteAdService favoriteAdService = new FavoriteAdService();
    static MessageService messageService = new MessageService();

    public static void main(String[] args) {




//        System.out.printf("%45s%n", "emir");

//        String adOwnerMessage = """
//                    %45s FROM: %s
//                    %45s Date: %s
//                    %45s %s \n
//                   """.formatted("", "emir",
//                "", "2024",
//                "","bir sey bir sey");
//
//        System.out.println(adOwnerMessage);
        menu();

    }

    public static void menu() {
        while (true) {
            System.out.println("""
                    ------- MENU -------
                     1- Login
                     2- Register
                     0- Quit
                     """);
            int input = Util.intScanner("input: ");
            switch (input) {
                case 1 -> {
                    User user = userService.login();
                    userMenu(user);
                }
                case 2 -> {
                    userService.register();
                }
                case 0 -> {
                    System.exit(0);
                }

            }
        }
    }


    public static void userMenu(User user) {
        while (true) {
            System.out.println("""
                    -------USER MENU -------
                     1- Publish New Ad
                     2- Show My Ads
                     3- Show My Favorite Ads
                     4- Show All Ads
                     0- Log Out
                     """);
            int input = Util.intScanner("input: ");

            switch (input) {
                case 1:
                    adService.publishAd(user);
                    break;
                case 2:
                    adService.listUserAds(user);
                    Long mySelectedAd = Util.longScanner("Go to my Ad[id]: ");
                    adDetailMenu(adService.getAdById(mySelectedAd), user);
                    break;
                case 3:
                    if (!favoriteAdService.listUserFavoriteAds(user).isEmpty()) {
                        Long selectedFavAd = Util.longScanner("Go to Fav. Ad[id]: ");
                        favoriteAdDetailMenu(adService.getAdById(selectedFavAd), user);
                    }
                    break;
                case 4:
                    adService.showAllAds();
                    Long selectedAd = Util.longScanner("Go to Ad[id]: ");
                    adDetailMenu(adService.getAdById(selectedAd), user);
                    break;
                case 5:

                case 0:
                    menu();
                    break;
            }
        }
    }

    public static void adDetailMenu(Ad ad, User user) {
        System.out.printf("""
                        ----------------------------------------------AD DETAILS------------------------------------------
                        | %-2s | %-10s | %-15s | %-7s | %-30s | %-15s |
                        --------------------------------------------------------------------------------------------------
                        | %-2s | %-10s | %-15s | %-7s | %-30s | %-15s |
                        --------------------------------------------------------------------------------------------------
                        """, "Id", "Seller", "Title", "Price", "Description", "Category"
                , ad.getId(), ad.getUser().getUsername(), ad.getTitle(), ad.getPrice(), ad.getDescription(), ad.getCategory().getCategory().name());
        while (true) {
            System.out.println("[1]- Add To Favorites [2]- Send Message [3]- Show Messages [0]- Back");
            int input = Util.intScanner("input: ");
            switch (input) {
                case 1 -> {
                    Optional<FavoriteAd> favoriteAd = favoriteAdService.addToFavorites(user, ad);
                    favoriteAd.ifPresent(value -> System.out.printf("%s [%s] added to favorites\n",
                            value.getAd().getTitle(), value.getAd().getCategory().getCategory().name()));
                }
                case 2 -> {
                    messageService.sendMessageViaAd(user, ad);
                }
                case 3 -> {
                    messageService.showMessagesViaAd(user, ad);
                }
                case 0 -> {
                    userMenu(user);
                }
            }
        }
    }


    public static void favoriteAdDetailMenu(Ad ad, User user) {
        System.out.printf("""
                        ----------------------------------------------FAVORITE AD DETAILS---------------------------------
                        | %-2s | %-10s | %-15s | %-7s | %-30s | %-15s |
                        --------------------------------------------------------------------------------------------------
                        | %-2s | %-10s | %-15s | %-7s | %-30s | %-15s |
                        --------------------------------------------------------------------------------------------------
                        [1]- Remove From Favorites [2]- Send Message [0]- Back
                        """, "Id", "Seller", "Title", "Price", "Description", "Category"
                , ad.getId(), ad.getUser().getUsername(), ad.getTitle(), ad.getPrice(), ad.getDescription(), ad.getCategory().getCategory().name());
        while (true) {
            int input = Util.intScanner("input: ");
            switch (input) {
                case 1 -> {
                    System.out.println(favoriteAdService.removeFromFavorites(ad));
                    System.out.printf("%s [%s] removed from favorites\n",
                            ad.getTitle(), ad.getCategory().getCategory().name());
                }
                case 2 -> {
                    messageService.sendMessageViaAd(user, ad);
                }
                case 0 -> {
                    userMenu(user);
                }
            }
        }
    }


}