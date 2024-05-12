package case_studies.splitwise;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class Expense {
    int id;
    String title;
    String description;
    User createdBy;

    Instant expenseDate;
    Instant createdOn;

    List<ExpensePaidByUser> paidByUsers;
    List<ExpenseSharedByUser> sharePerUser;
    BigDecimal amount;
}
