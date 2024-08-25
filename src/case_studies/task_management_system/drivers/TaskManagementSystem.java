package case_studies.task_management_system.drivers;

import case_studies.task_management_system.enums.TaskPriorityEnum;
import case_studies.task_management_system.enums.TaskStatusEnum;
import case_studies.task_management_system.models.Task;
import case_studies.task_management_system.models.TaskHistory;
import case_studies.task_management_system.models.User;
import case_studies.task_management_system.services.TaskManager;

import java.time.LocalDate;
import java.util.List;

public class TaskManagementSystem {


    public static void main(String[] args) {

        TaskManager taskManager = TaskManager.getInstance();

        User user1 = new User(1, "User 1");
        User user2 = new User(2, "User 2");

        Task task1 = Task.builder()
                .id(1)
                .createdBy(user1)
                .createdDate(LocalDate.now())
                .title("Task1 title")
                .status(TaskStatusEnum.IN_PROGRESS)
                .build();

        Task task2 = Task.builder()
                .id(2)
                .createdBy(user2)
                .title("Task 2 High")
                .createdDate(LocalDate.now())
                .description("Task2 description")
                .priority(TaskPriorityEnum.HIGH)
                .build();

        // Add tasks to the task manager
        taskManager.saveTask(task1);
        taskManager.saveTask(task2);

//        // Search tasks
        List<Task> searchResults = taskManager.searchTasks("Task");
        System.out.println("Search Results:");
        for (Task task : searchResults) {
            System.out.println(task.getTitle());
        }

        //        // Update a task
        Task task2FromDb = taskManager.getTaskById(task2.getId());
        task2.setDescription("Updated description");
        taskManager.update(task2);

        searchResults = taskManager.searchTasks("Task");
        System.out.println("Search Results After Update:");
        for (Task task : searchResults) {
            System.out.println(task.getTitle());
        }
        System.out.println("=========================================================");
        // Filter tasks
        List<Task> filteredTasks = taskManager.filterTasks(TaskStatusEnum.IN_PROGRESS, TaskPriorityEnum.HIGH);
        System.out.println("Filtered Tasks:");
        for (Task task : filteredTasks) {
            System.out.println(task.getTitle());
        }

        // Mark a task as completed
        taskManager.markTaskAsCompleted(task1.getId());

        System.out.println("=========================================================");

        // Get task history for a user
        List<TaskHistory> taskHistories = taskManager.getTaskHistory(user1);
        System.out.println("Task History for " + user1.getName() + ":");
        for (TaskHistory taskHistory : taskHistories) {
            System.out.println(taskHistory.getTask().getTitle());
        }

        // Delete a task
        taskManager.deleteTask(task2.getId());
    }
}
