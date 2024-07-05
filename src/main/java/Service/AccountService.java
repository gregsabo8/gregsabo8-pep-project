package Service;

import DAO.AccountDAO;
import Model.Account;

import java.util.List;

public class AccountService {
    public AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO=accountDAO;
    }

    public List<Account> getAllAccount(){
        
        return accountDAO.getAllAccounts();
    }

    public Account insertAccount(Account account){
        if(account.getUsername()==""||account.getPassword().length()<5){
            return null;
        }
        return accountDAO.insertAccount(account);
    }

    public Account login(String username, String password){

        if(accountDAO!=null){
            return accountDAO.login(username,password);

        }
        else{
            return null;
        }
    }
}
