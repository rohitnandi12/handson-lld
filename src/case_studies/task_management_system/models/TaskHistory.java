package case_studies.task_management_system.models;

import java.time.LocalDateTime;

public class TaskHistory {

    private final Integer id;
    private LocalDateTime updatedOn;
    private Task task;
    private User updatedBy;

    public TaskHistory(Integer id, LocalDateTime updatedOn, Task task, User updatedBy) {
        this.id = id;
        this.updatedOn = updatedOn;
        this.task = task;
        this.updatedBy = updatedBy;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public Task getTask() {
        return task;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    private TaskHistory(Builder builder) {
        this.id = builder.id;
        this.updatedOn = builder.updatedOn;
        this.task = builder.task;
        this.updatedBy = builder.updatedBy;
    }

    public static Builder builder(Integer id) {
        return new Builder(id);
    }

    public static class Builder {
        private final Integer id;
        private LocalDateTime updatedOn;
        private Task task;
        private User updatedBy;

        public Builder(Integer id) {
            this.id = id;
        }

        public Builder updatedOn(LocalDateTime updatedOn) {
            this.updatedOn = updatedOn;
            return this;
        }

        public Builder task(Task task) {
            this.task = task;
            return this;
        }

        public Builder updatedBy(User updatedBy) {
            this.updatedBy = updatedBy;
            return this;
        }

        public TaskHistory build() {
            return new TaskHistory(this);
        }
    }
}
