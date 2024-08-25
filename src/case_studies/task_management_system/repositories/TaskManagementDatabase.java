package case_studies.task_management_system.repositories;

import case_studies.task_management_system.models.Task;
import case_studies.task_management_system.models.TaskHistory;
import case_studies.task_management_system.models.User;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TaskManagementDatabase {
    private final Map<Integer, Task> taskTable;
    private final Map<Integer, List<Task>> userTaskIndex;
    private final Map<Integer, User> userTable;
    private final Map<Integer, TaskHistory> taskHistoryTable;
    private final Map<Integer, List<TaskHistory>> taskHistoryIndex;

    private TaskManagementDatabase() {
        this.taskHistoryTable = new ConcurrentHashMap<>();
        this.userTable = new ConcurrentHashMap<>();
        this.taskTable = new ConcurrentHashMap<>();
        this.taskHistoryIndex = new ConcurrentHashMap<>();
        this.userTaskIndex = new ConcurrentHashMap<>();
    }

    private static final class InstanceHolder {
        private static final TaskManagementDatabase instance = new TaskManagementDatabase();
    }

    public static TaskManagementDatabase getInstance() {
        return InstanceHolder.instance;
    }

    public Map<Integer, Task> getTaskTable() {
        return taskTable;
    }

    public Map<Integer, User> getUserTable() {
        return userTable;
    }
    public Map<Integer, TaskHistory> getTaskHistoryTable() {
        return taskHistoryTable;
    }
    public Map<Integer, List<Task>> getUserTaskIndex() {
        return userTaskIndex;
    }

    public Map<Integer, List<TaskHistory>> getTaskHistoryIndex() {
        return taskHistoryIndex;
    }
}
