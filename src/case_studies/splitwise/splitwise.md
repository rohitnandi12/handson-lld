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



### Requirements [Awesome LLD](https://github.com/ashishps1/awesome-low-level-design/blob/main/problems/splitwise.md)
- The system should allow users to create accounts and manage their profile information.
- Users should be able to create groups and add other users to the groups.
- Users should be able to add expenses within a group, specifying the amount, description, and participants.
- The system should automatically split the expenses among the participants based on their share.
- Users should be able to view their individual balances with other users and settle up the balances.
- The system should support different split methods, such as equal split, percentage split, and exact amounts.
- Users should be able to view their transaction history and group expenses.
- The system should handle concurrent transactions and ensure data consistency.

Classes, Interfaces and Enumerations
- The User class represents a user in the Splitwise system, with properties such as ID, name, email, and a map to store balances with other users.
- The Group class represents a group in Splitwise, containing a list of member users and a list of expenses.
- The Expense class represents an expense within a group, with properties such as ID, amount, description, the user who paid, and a list of splits.
- The Split class is an abstract class representing the split of an expense. It is extended by EqualSplit, PercentSplit, and ExactSplit classes to handle different split methods.
- The Transaction class represents a transaction between two users, with properties such as ID, sender, receiver, and amount.
- The SplitwiseService class is the main class that manages the Splitwise system. It follows the Singleton pattern to ensure only one instance of the service exists.
- The SplitwiseService class provides methods for adding users, groups, and expenses, splitting expenses, updating balances, settling balances, and creating transactions.
- Multi-threading is achieved using concurrent data structures such as ConcurrentHashMap and CopyOnWriteArrayList to handle concurrent access to shared resources.
- The SplitwiseDemo class demonstrates the usage of the Splitwise system by creating users, a group, adding an expense, settling balances, and printing user balances.
