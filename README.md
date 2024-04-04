## LetGo Online Store

#### User uses program via command-line inputs to;
* Register to Website
* Login with username and password
* Publish Ads to sell
* Add other users' ads to their favorite ad list
* Chat with other users



#### Technologies used:
* Postgres
* Jakarta Persistence API -> Hibernate
* Lombok

```java
------- MENU -------
1- Login
2- Register
0- Quit


-------USER MENU -------
1- Publish New Ad
2- Show My Ads
3- Show My Favorite Ads
4- Show All Ads
0- Log Out


--------------------------------------------------------------------------------------------------
| Id | Seller     | Title           | Price   | Description                    | Category        |
--------------------------------------------------------------------------------------------------
| 1  | emiresen   | MACBOOK PRO M1  | 1200    | 2022 brand new                 | ELECTRONICS     |
| 2  | emiresen   | TREK ROAD BIKE  | 12500   | 250km used                     | OUTDOORSPORTS   |
| 4  | emreyildiz | SS KNIFE SET    | 250     | 6 piece knife set              | HOME            |
| 5  | emiresen   | 25M SLACKLINE   | 250     | 25m yula slackline brand new   | OUTDOORSPORTS   |
--------------------------------------------------------------------------------------------------
[1]- Add To Favorites [2]- Send Message [3]- Show Messages [0]- Back
```

![Alt text](/Users/emiresen/Documents/BOOST24/LetGoProject/Database-ERD.png)