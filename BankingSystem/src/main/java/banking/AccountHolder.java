package banking;

import java.util.Objects;

/**
 * Abstract Account Holder.
 */
public abstract class AccountHolder {
    private int idNumber;
    
    /**
     * @param idNumber The holder unique ID.
     */
    protected AccountHolder(int idNumber) {
        this.idNumber = idNumber;
    }

    public int getIdNumber() {
        return this.idNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountHolder that = (AccountHolder) o;
        return idNumber == that.idNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNumber);
    }
}
