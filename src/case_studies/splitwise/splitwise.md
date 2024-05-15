#### Requirements:Scaler

- Users can register and update their profiles
- A user's profile should contain at least their name, phone number and password
- Users can participate in groups.
- Only some participants of the group can add/remove members to the group
- Users can participate in expenses with other users
- To add an expense
  - along with who paid what and
  - who owes what.
  - a user must specify either the group,
  - or the other users involved in the expense,
  - They must also specify a description for the expense.
- A user can see their total owed amount
- A user can see a history of the expenses they're involved in
- A user can see a history of the expenses made in a group that they're participating in
Users shouldn't be able to query about groups they are not a member of -> RBAC (Role
Based Access Control)
- Users can request a settle-up for any group they're participating in. The application
should show a list of transactions, which if executed, will ensure that everyone
participating in the group is settled up (owes a net of 0 Rs). Note that it will only deal with
the expenses made inside that group. Expenses outside the group need not be settled.
- Users can request a settle-up for himself/herself. The application should show a list of
transactions, which when executed will ensure that the user no longer owes or receives
money from any other user. Note that this need not settle any other user.
- When settling a user or a group, we should try to minimize the number of transactions
required to settle up the user or the group. (Algorithm for minimizing transactions)