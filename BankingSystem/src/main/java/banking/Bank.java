package banking;

import java.util.*;

/**
 * The Bank implementation.
 */
public class Bank implements BankInterface {
    private LinkedHashMap<Long, Account> accounts;

    public Bank() {
        this.accounts = new LinkedHashMap<>();
    }

    private Account getAccount(Long accountNumber) {
        return this.accounts.get(accountNumber);
    }

    public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
        long accountNumber = accounts.size() + 1;
        this.accounts.put(accountNumber, new CommercialAccount(company, accountNumber, pin, startingDeposit));
        return accountNumber;
    }

    public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
        long accountNumber = accounts.size() + 1;
        this.accounts.put(accountNumber, new ConsumerAccount(person, accountNumber, pin, startingDeposit));
        return accountNumber;
    }

    public double getBalance(Long accountNumber) {
        Account account = getAccount(accountNumber);
        return Objects.nonNull(account) ? account.getBalance() : 0;
    }

    public void credit(Long accountNumber, double amount) {
        Optional.ofNullable(getAccount(accountNumber))
                .ifPresent(account -> account.creditAccount(amount));
    }

    public boolean debit(Long accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        return Objects.nonNull(account) && account.debitAccount(amount);
    }

    public boolean authenticateUser(Long accountNumber, int pin) {
        Account account = getAccount(accountNumber);
        return Objects.nonNull(account) && account.validatePin(pin);
    }
    
    public void addAuthorizedUser(Long accountNumber, Person authorizedPerson) {
        // TODO: complete the method
    }

    public boolean checkAuthorizedUser(Long accountNumber, Person authorizedPerson) {
        // TODO: complete the method
        return false;
    }

    public Map<String, Double> getAverageBalanceReport() {
        // TODO: complete the method
        return new HashMap<>();
    }
}
