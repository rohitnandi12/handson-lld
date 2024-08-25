package case_studies.task_management_system.repositories;

import case_studies.task_management_system.models.TaskHistory;
import case_studies.task_management_system.models.User;

import java.util.ArrayList;
import java.util.List;

public class TaskHistoryRepository {

    private final TaskManagementDatabase db;

    private Integer nextId;
    private TaskHistoryRepository() {
        this.db = TaskManagementDatabase.getInstance();
        this.nextId = 0;
    }

    public List<TaskHistory> findByUser(User user) {
        return db.getTaskHistoryTable()
                .values()
                .stream().filter(th -> th.getUpdatedBy().equals(user))
                .toList();
    }

    public Integer getNextId() {
        this.nextId += 1;
        return this.nextId;
    }

    public void save(TaskHistory taskHistory) {
        this.db.getTaskHistoryTable().put(taskHistory.getId(), taskHistory);

        this.db.getTaskHistoryIndex().putIfAbsent(taskHistory.getTask().getId(), new ArrayList<>());
        this.db.getTaskHistoryIndex().get(taskHistory.getTask().getId()).add(taskHistory);
    }

    private static final class InstanceHolder {
        private static final TaskHistoryRepository instance = new TaskHistoryRepository();
    }

    public static TaskHistoryRepository getInstance() {
        return TaskHistoryRepository.InstanceHolder.instance;
    }
}
