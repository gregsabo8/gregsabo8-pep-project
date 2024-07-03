package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    /**
     * TODO: retrieve all messages from the message table.
     * @return all messages.
     */
    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"), 
                    rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }

    //create message
    public Message createMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try{
            //post
            //id should be auto generated so just need posted_by, message_text, time_posted_epoch
            String sql = "INSERT INTO book (posted_by, message_text,time_posted_epoch) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1,message.getPosted_by());
            preparedStatement.setString(2,message.getMessage_text());
            preparedStatement.setLong(3,message.getTime_posted_epoch());

            preparedStatement.executeUpdate();
            ResultSet pKeyResultSet = preparedStatement.getGeneratedKeys();
            if(pKeyResultSet.next()){
                int generated_message_id = (int) pKeyResultSet.getLong(1);
                return new Message(generated_message_id, 
                message.getPosted_by(),
                message.getMessage_text(),
                message.getTime_posted_epoch());
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    //delete message by id
    public void deleteMessage(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try{
            //delete
            String sql = "DELETE FROM message WHERE message_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,message_id);
            //execute the prepared statement
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    //retrieve all messages for user
    public List<Message> retrieveMessageForUser(int posted_by){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try{
            //get
            String sql = "SELECT * FROM message WHERE posted_by = ?";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,posted_by);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch"));
                messages.add(message);

            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }

    public Message getMessageById(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try{
            //get
            String sql = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,message_id);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch"));
                return message;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateMessage(String message_text){
        Connection connection = ConnectionUtil.getConnection();
        try{
            //put
            String sql = "UPDATE message SET message_text = ?";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,message_text);

            preparedStatement.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    
}
//getAllMessages()
//createMessage()
//deleteMessage()
//retrieveMessageForUser()
//getMessageById()
//updateMessage()