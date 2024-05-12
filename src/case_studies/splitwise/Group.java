package case_studies.splitwise;

import case_studies.interviewready.splitwise.dto.BalanceMap;

import java.util.List;

public class Group {
    int id;
    User createdBy;

    List<GroupParticipant> participants;
    List<Expense> expenses;
}
