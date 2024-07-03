package Controller;

import Model.Message;
import Service.AccountService;
import Service.MessageService;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {

        Javalin app = Javalin.create();
        app.get("/messages", this::getAllMessagesHandler);
        app.post("/newMessage",this::createMessageHandler);
        app.get("/messages/{user}", this::retriveMessageFromUserHandler);
        app.get("/message/{id}", this::getMessageByIdHandler);
        app.put("/message/{text}",this::updateMessageHandler);
        app.delete("/delete", this::deleteMessageHandler);
        app.get("/account", this::getAllAccountsHandler);
        app.post("/registerUser",this::createAccountHandler);


        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getAllMessagesHandler(Context context) {
        List<Message> messages= messageService.getAllMessages();
        context.json(messages);
    }

    private void createMessageHandler(Context context)throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message newMessage = messageService.createMessage(message);
        if(newMessage!=null){
            context.json(mapper.writeValueAsString(newMessage));
        }else{
            context.status(400);
        }
    }   

    private void retriveMessageFromUserHandler(Context context) {
        context.json(messageService.retrieveMessagesFromUser(Integer.parseInt(context.pathParam(("posted_by")))));

    }

    private void getMessageByIdHandler(Context context) {
        context.json(messageService.getMessageById(Integer.parseInt(context.pathParam(("message_id")))));

    }

    private void updateMessageHandler(Context context) throws JsonMappingException, JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        int message_id = Integer.parseInt(context.pathParam("flight_id"));
        Message updtdMessage = messageService.updateMessage(message_id,message);
        System.out.println(updtdMessage);
        if(updtdMessage==null){
            context.status(400);
        }else{
            context.json(mapper.writeValueAsString(updtdMessage));
        }
    }
    
    private void deleteMessageHandler(Context context){
        messageService.deleteMessage(Integer.parseInt(context.pathParam(("message_id"))));

    }

    private void getAllAccountsHandler(Context context){
        
    }
    private void createAccountHandler(Context context){
        
    }
}


//getAllMessages()-get
//createMessage()-post
//deleteMessage()-delete
//retrieveMessageForUser(posted_by)-get
//getMessageById()-get
//updateMessage()-put

//getAllAccounts()-get
//insertAccount()-post