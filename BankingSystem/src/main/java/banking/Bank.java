package banking;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The Bank implementation.
 */
public class Bank implements BankInterface {
    private final LinkedHashMap<Long, Account> accounts;

    public Bank() {
        this.accounts = new LinkedHashMap<>();
    }

    private Account getAccount(Long accountNumber) {
        return this.accounts.get(accountNumber);
    }

    public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
        final long accountNumber = accounts.size() + 1;
        this.accounts.put(accountNumber, new CommercialAccount(company, accountNumber, pin, startingDeposit));
        return accountNumber;
    }

    public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
        final long accountNumber = accounts.size() + 1;
        this.accounts.put(accountNumber, new ConsumerAccount(person, accountNumber, pin, startingDeposit));
        return accountNumber;
    }

    public double getBalance(Long accountNumber) {
        final Account account = getAccount(accountNumber);
        return Objects.nonNull(account) ? account.getBalance() : -1.0;
    }

    public void credit(Long accountNumber, double amount) {
        Optional.ofNullable(getAccount(accountNumber))
                .ifPresent(account -> account.creditAccount(amount));
    }

    public boolean debit(Long accountNumber, double amount) {
        final Account account = getAccount(accountNumber);
        return Objects.nonNull(account) && account.debitAccount(amount);
    }

    public boolean authenticateUser(Long accountNumber, int pin) {
        final Account account = getAccount(accountNumber);
        return Objects.nonNull(account) && account.validatePin(pin);
    }
    
    public void addAuthorizedUser(Long accountNumber, Person authorizedPerson) {
        Optional.ofNullable(getAccount(accountNumber))
                .filter(account -> account.getClass() == CommercialAccount.class)
                .map(CommercialAccount.class::cast)
                .ifPresent(account -> account.addAuthorizedUser(authorizedPerson));
    }

    public boolean checkAuthorizedUser(Long accountNumber, Person authorizedPerson) {
        return Optional.ofNullable(getAccount(accountNumber))
                .filter(account -> account.getClass() == CommercialAccount.class)
                .map(CommercialAccount.class::cast)
                .map(account -> account.isAuthorizedUser(authorizedPerson))
                .orElse(false);
    }

    public Map<String, Double> getAverageBalanceReport() {
        final Map<String, Double> averageBalances = new LinkedHashMap<>();
        final Map<String, List<Account>> groupedAccounts = this.accounts.values()
                .stream()
                .collect(Collectors.groupingBy(account -> account.getClass().getSimpleName()));
        groupedAccounts.keySet()
                .forEach(accountType -> averageBalances.put(accountType,
                        groupedAccounts.get(accountType)
                        .stream()
                        .mapToDouble(Account::getBalance)
                        .average()
                        .orElse(0))
                );
        return averageBalances;
    }
}
