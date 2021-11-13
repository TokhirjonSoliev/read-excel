import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesItem {
    private String fio;
    private String accountNumberCard;
    private Integer cardType;
    private Integer amountTransferToCard;

    public EmployeesItem(String fio, String accountNumberCard, Integer amountTransferToCard) {
        this.fio = fio;
        this.accountNumberCard = accountNumberCard;
        this.amountTransferToCard = amountTransferToCard;
        this.cardType = 1;
    }
}
