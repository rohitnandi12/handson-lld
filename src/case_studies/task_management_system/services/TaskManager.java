package case_studies.task_management_system.services;

import case_studies.task_management_system.enums.TaskPriorityEnum;
import case_studies.task_management_system.enums.TaskStatusEnum;
import case_studies.task_management_system.models.Task;
import case_studies.task_management_system.models.TaskHistory;
import case_studies.task_management_system.models.User;
import case_studies.task_management_system.repositories.TaskRepository;
import case_studies.task_management_system.repositories.specification.SpecificationBuilder;
import case_studies.task_management_system.repositories.TaskHistoryRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public final class TaskManager {

    private static final TaskManager INSTANCE = new TaskManager(); // Eager Initialization
    private final TaskRepository taskRepository;
    private final TaskHistoryRepository taskHistoryRepository;

    private TaskManager() {
        this.taskHistoryRepository = TaskHistoryRepository.getInstance();
        this.taskRepository = TaskRepository.getInstance();
    }

    public static TaskManager getInstance() {
        return INSTANCE;
    }

    public void saveTask(Task task) {
        validateTaskIsAbsent(task.getId());
        taskRepository.save(task);
        updateTaskHistory(task);
    }

    private void updateTaskHistory(Task task) {
        System.out.println(task.getCreatedBy());
        System.out.println(task.clone().getCreatedBy());
        taskHistoryRepository.save(
                TaskHistory
                        .builder(taskHistoryRepository.getNextId())
                        .task(task.clone())
                        .updatedBy(task.getCreatedBy())
                        .updatedOn(LocalDateTime.now())
                        .build()
        );
    }

    public void update(Task task) {
        validateTaskIsPresent(task.getId());
        taskRepository.update(task);
        updateTaskHistory(task);
    }

    public Task getTaskById(Integer id) {
        validateTaskIsPresent(id);
        return taskRepository.findById(id).get();
    }

    private void validateTaskIsPresent(Integer id) {
        boolean isPresent = taskRepository.isTaskPresent(id);
        if (!isPresent)
            throw new IllegalArgumentException("Task doesn't exist!!");
    }

    private void validateTaskIsAbsent(Integer id) {
        boolean isPresent = taskRepository.isTaskPresent(id);
        if (isPresent)
            throw new IllegalArgumentException("Task already exist!!");
    }

    public List<Task> searchTasks(String query) {
        String lowerCaseQuery = query.toLowerCase();
        List<Task> tasks = taskRepository.findBySpecification(
                SpecificationBuilder.<Task>builder()
                        .and(t -> t.getTitle() != null && t.getTitle().toLowerCase().contains(lowerCaseQuery))
                        .or(t -> t.getDescription() != null && t.getDescription().toLowerCase().contains(lowerCaseQuery))
                        .build()
        );
        System.out.printf("LOG_DEBUG: There are %d tasks for query: %s%n", tasks.size(), query);
        return tasks;
    }

    public List<Task> filterTasks(TaskStatusEnum taskStatusEnum, TaskPriorityEnum taskPriorityEnum,
                                  LocalDate from, LocalDate to) {
        List<Task> tasks = taskRepository.findBySpecification(
                SpecificationBuilder.<Task>builder()
                        .and(SpecificationBuilder.<Task>builder()
                                .and(t -> t.getStatus() != null && t.getStatus().equals(taskStatusEnum))
                                .or(t -> t.getPriority() != null && t.getPriority().equals(taskPriorityEnum))
                                .build())
                        .and(SpecificationBuilder.<Task>builder()
                                .and(t -> t.getCreatedDate() != null && t.getCreatedDate().isAfter(from))
                                .and(t -> t.getCreatedDate() != null && t.getCreatedDate().isBefore(to))
                                .build())
                        .build()
        );
        System.out.printf("LOG_DEBUG: There are %d tasks for filter: %s, %s, %s%n"
                , tasks.size(), taskStatusEnum, from, to);
        return tasks;
    }

    public List<Task> filterTasks(TaskStatusEnum taskStatusEnum, TaskPriorityEnum taskPriorityEnum) {
        return filterTasks(taskStatusEnum, taskPriorityEnum, LocalDate.MIN, LocalDate.MAX);
    }

    public List<Task> filterTasks(TaskStatusEnum taskStatusEnum, LocalDate from) {
        return filterTasks(taskStatusEnum, TaskPriorityEnum.LOW, from, LocalDate.MAX);
    }

    public void markTaskAsCompleted(Integer id) {
        Task task = taskRepository.findById(id).get();
        task.setStatus(TaskStatusEnum.COMPLETED);
        taskRepository.update(task);
        updateTaskHistory(task);
    }

    public void deleteTask(Integer id) {
        validateTaskIsPresent(id);
        taskRepository.deleteById(id);
    }

    public List<TaskHistory> getTaskHistory(User user) {
        List<TaskHistory> histories = taskHistoryRepository.findByUser(user);
        System.out.printf("LOG_DEBUG: Fetched %d histories%n", histories.size());
        return histories;
    }
}
