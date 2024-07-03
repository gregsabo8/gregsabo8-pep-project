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
        if(messageDAO.getMessageById(message.getMessage_id())!=null){
            return null;
        }
        return messageDAO.createMessage(message);
    }

    public void deleteMessage(int message_id){
        messageDAO.deleteMessage(message_id);
    }

    public List<Message> retrieveMessagesFromUser(int posted_by){
        return messageDAO.retrieveMessageForUser(posted_by);
    }

    public Message getMessageById(int posted_by){
        return messageDAO.getMessageById(posted_by);
    }

    public void updateMessage(int message_id){
        messageDAO.updateMessage(null);
    }
}
