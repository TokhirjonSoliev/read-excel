import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSalary {
    private String fio;
    private String accountNumberCard;
    private Integer cardType;
    private Integer amountTransferToCard;

    public EmployeeSalary(String fio, String accountNumberCard, Integer amountTransferToCard) {
        this.fio = fio;
        this.accountNumberCard = accountNumberCard;
        this.amountTransferToCard = amountTransferToCard;
        this.cardType = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeSalary salary = (EmployeeSalary) o;
        return Objects.equals(getFio(), salary.getFio()) && Objects.equals(getAccountNumberCard(), salary.getAccountNumberCard()) && Objects.equals(getCardType(), salary.getCardType()) && Objects.equals(getAmountTransferToCard(), salary.getAmountTransferToCard());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFio(), getAccountNumberCard(), getCardType(), getAmountTransferToCard());
    }
}
