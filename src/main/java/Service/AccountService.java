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
        return accountDAO.insertAccount(account);
    }
}
