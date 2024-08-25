package case_studies.task_management_system.models;

import case_studies.task_management_system.enums.TaskPriorityEnum;
import case_studies.task_management_system.enums.TaskStatusEnum;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Task implements Cloneable{

    private final Integer id;
    private final User createdBy;
    private final LocalDate createdDate;

    private String title;
    private String description;
    private LocalDate dueDate;
    private LocalDate lastUpdatedOn;
    private TaskStatusEnum status;
    private TaskPriorityEnum priority;
    private Map<Integer, Task> subTasks;
    private User assignedTo;

    public Task(
            Integer id, User createdBy, LocalDate createdDate, String title,
            String description, LocalDate dueDate, LocalDate lastUpdatedOn,
            TaskStatusEnum status, TaskPriorityEnum priority, Map<Integer, Task> subTasks,
            User assignedTo
    ) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.lastUpdatedOn = lastUpdatedOn;
        this.status = status;
        this.priority = priority;
        this.subTasks = subTasks;
        this.assignedTo = assignedTo;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setLastUpdatedOn(LocalDate lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public void setStatus(TaskStatusEnum status) {
        this.status = status;
    }

    public void setPriority(TaskPriorityEnum priority) {
        this.priority = priority;
    }

    public void setSubTasks(HashMap<Integer, Task> subTasks) {
        this.subTasks = subTasks;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Integer getId() {
        return id;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public TaskStatusEnum getStatus() {
        return status;
    }

    public TaskPriorityEnum getPriority() {
        return priority;
    }

    public Map<Integer, Task> getSubTasks() {
        return subTasks;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }

    @Override
    public Task clone() {
        try {
            Task clonedTask = (Task) super.clone();

            // Cloning mutable objects deeply to ensure no shared references
            clonedTask.subTasks = new HashMap<>();
            for (Map.Entry<Integer, Task> entry : this.subTasks.entrySet()) {
                clonedTask.subTasks.put(entry.getKey(), entry.getValue().clone());
            }

            // If User and Enums are mutable, they should be cloned as well
            clonedTask.assignedTo = (this.assignedTo != null) ? this.assignedTo.clone() : null;

            return clonedTask;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // This should never happen, since we are Cloneable
        }
    }

    public static class TaskBuilder {
        private Integer id;
        private User createdBy;
        private LocalDate createdDate;

        private String title;
        private String description;
        private LocalDate dueDate;
        private LocalDate lastUpdatedOn;
        private TaskStatusEnum status = TaskStatusEnum.DRAFT;
        private TaskPriorityEnum priority = TaskPriorityEnum.LOW;
        private Map<Integer, Task> subTasks = new ConcurrentHashMap<>();
        private User assignedTo;

        public TaskBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public TaskBuilder createdBy(User createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public TaskBuilder createdDate(LocalDate createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public TaskBuilder title(String title) {
            this.title = title;
            return this;
        }

        public TaskBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TaskBuilder dueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public TaskBuilder lastUpdatedOn(LocalDate lastUpdatedOn) {
            this.lastUpdatedOn = lastUpdatedOn;
            return this;
        }

        public TaskBuilder status(TaskStatusEnum status) {
            this.status = status;
            return this;
        }

        public TaskBuilder priority(TaskPriorityEnum priority) {
            this.priority = priority;
            return this;
        }

        public TaskBuilder subTasks(HashMap<Integer, Task> subTasks) {
            this.subTasks = subTasks;
            return this;
        }

        public TaskBuilder assignedTo(User assignedTo) {
            this.assignedTo = assignedTo;
            return this;
        }

        public Task build() {
            if (id == null || createdBy == null || createdDate == null)
                throw new IllegalArgumentException("Mandatory fields are missing");

            return new Task(
                    id, createdBy, createdDate, title,
                    description, dueDate, lastUpdatedOn,
                    status, priority, subTasks,
                    assignedTo
            );
        }
    }
}
