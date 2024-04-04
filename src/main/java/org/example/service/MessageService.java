package org.example.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entity.Ad;
import org.example.entity.Message;
import org.example.entity.User;
import org.example.repository.MessageRepository;
import org.example.util.Util;

import java.util.List;

public class MessageService {

    MessageRepository messageRepository = new MessageRepository();


    public User sendMessageViaAd(User loggedInUser, Ad ad){
        String content = Util.stringScanner("Message: ");
        Message message = Message.builder()
                .content(content)
                .ad(ad)
                .sender(loggedInUser)
                .build();
        messageRepository.save(message);
        return ad.getUser();
    }

public List<Message> showMessagesViaAd(User sender, Ad ad){
       return messageRepository.showMyAdMessages(sender, ad);
}


}
