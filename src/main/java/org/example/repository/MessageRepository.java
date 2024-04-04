package org.example.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entity.Ad;
import org.example.entity.Message;
import org.example.entity.User;
import org.example.util.Util;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class MessageRepository extends RepositoryManager<Message, Long>{
    public MessageRepository() {
        super(Message.class);
    }


    public List<Message> showMyAdMessages(User loggedInUser, Ad ad){
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Message> cr = criteriaBuilder.createQuery(Message.class);
        Root<Message> root = cr.from(Message.class);

        cr.select(root).where(criteriaBuilder.equal(root.get("ad"), ad));
//        criteriaBuilder.equal(root.get("sender"), loggedInUser),

        List<Message> adMessages = getEntityManager().createQuery(cr).getResultList();
        System.out.printf("-----------------------------------MESSAGES FOR AD-------------------------------------%n");
        System.out.printf("| %-2s | %-15s | %-7s | %-30s | %-15s |%n", "Id", "Ad Title", "Price", "Description", "Category");
        System.out.printf("| %-2s | %-15s | %-7s | %-30s | %-15s |%n",
                ad.getId(), ad.getTitle(), ad.getPrice(), ad.getDescription(), ad.getCategory().getCategory().name());
        System.out.printf("--------------------------------------------------------------------------------------%n");
        for (Message m : adMessages) {
            String senderMessage = """
                    FROM: %s
                    Date: %s
                   -----------------------
                    %s \n
                   """.formatted(m.getSender().getUsername(),
                    m.getCreatedAt().toLocalDateTime().format(DateTimeFormatter.ofPattern("MMM dd, yyyy")),
                    m .getContent());
            String adOwnerMessage = """
                    %45s FROM: %s
                    %45s Date: %s
                  %45s-----------------------
                    %45s %s \n
                   """.formatted("", m.getSender().getUsername(),
                    "", m.getCreatedAt().toLocalDateTime().format(DateTimeFormatter.ofPattern("MMM dd, yyyy")),"",
                    "", m .getContent());
            System.out.println(m.getSender().getId().equals(loggedInUser.getId()) ? adOwnerMessage : senderMessage );
        }
        System.out.printf("--------------------------------------------------------------------------------------%n");
        return  adMessages;
    }
}
