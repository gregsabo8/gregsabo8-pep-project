package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;

import static org.mockito.ArgumentMatchers.eq;

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
        app.post("/messages",this::createMessageHandler);
        app.get("/accounts/{account_id}/messages", this::retriveMessageFromUserHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.patch("/messages/{message_id}",this::updateMessageHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.post("/login", this::loginHandler);
        app.post("/register",this::createAccountHandler);


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

        if(newMessage==null){
            context.status(400);
            
            
        }else{
            context.status(200);
            context.json(mapper.writeValueAsString(newMessage));
            
        }
    }   

    private void retriveMessageFromUserHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        
        //1
        //look what to grab from request... path or body
        //2
        //call relevant service layer (might need to pass in from body into service method)
        //3
        //service layer shoould return value...based on this value 
            //return context.json
            //status
            //base on the response!!!

        int posted_by = Integer.parseInt(context.pathParam("account_id"));

        List<Message> userMessages = messageService.retrieveMessagesFromUser(posted_by);

        context.json(mapper.writeValueAsString(userMessages));

    }

    private void getMessageByIdHandler(Context context) {
        
        //if it is null dont send
        if(messageService.getMessageById(Integer.parseInt(context.pathParam("message_id"))) != null){
            context.json(messageService.getMessageById(Integer.parseInt(context.pathParam("message_id"))));
        }

        
    }

    private void updateMessageHandler(Context context) throws JsonMappingException, JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message updtdMessage = messageService.updateMessage(message_id,message);
        // System.out.println(updtdMessage);
        if(updtdMessage==null||updtdMessage.getMessage_text().length()>=255||updtdMessage.getMessage_text().isEmpty()){
            context.status(400);
        }else{
            context.json(mapper.writeValueAsString(updtdMessage));
        }
    }
    
    private void deleteMessageHandler(Context context){
        int id = Integer.parseInt(context.pathParam("message_id"));

        if(messageService.getMessageById(id) != null){
            
            context.json(messageService.getMessageById(id));
        }else{
            context.status(200);
        }

    }

    private void loginHandler(Context context) throws JsonMappingException, JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(),Account.class);
        Account accounts = accountService.login(account.getUsername(),account.getPassword());

        if(accounts!=null){
            context.json(accounts);
        }
        else{
            context.status(401);
        }
    }
    private void createAccountHandler(Context context) throws JsonMappingException, JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(),Account.class);
        Account newAccount = accountService.insertAccount(account);

        if(newAccount==null){
            context.status(400);
            
            
        }else{
            context.status(200);
            context.json(mapper.writeValueAsString(newAccount));
            
        }
    }
}

