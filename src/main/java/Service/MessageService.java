package Service;

//create message
//delete message by id
//retrieve all messages for user
//retrieve all messages
//get message by message id
//update message

import Model.Message;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {
    private MessageDAO messageDAO;

    //constructor
    public MessageService(){
        messageDAO = new MessageDAO();
    }

    //when messageservice exist
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    public Message createMessage(Message message){
         if(message.getMessage_text()==""){
             return null;
        }
        return messageDAO.createMessage(message);
    }

    public boolean deleteMessage(int message_id){
        return messageDAO.deleteMessage(message_id);
    }

    public List<Message> retrieveMessagesFromUser(int posted_by){
        return messageDAO.retrieveMessageForUser(posted_by);
    }

    public Message getMessageById(int message_id){
        if(messageDAO.getMessageById(message_id) != null){
            return messageDAO.getMessageById(message_id);
        }else{
            return null;
        }

        
        
    }

    public Message updateMessage(int message_id, Message message){
        Message updatedMessage = messageDAO.getMessageById(message_id);

        if(updatedMessage==null){
            return null;
        }else{
            updatedMessage.setMessage_text(message.getMessage_text());
            return updatedMessage;
        }
        // if(messageDAO.getMessageById(message_id)==null){
        //     return null;
        // }
        // //update
        // return messageDAO.getMessageById(message_id);
    }
}


