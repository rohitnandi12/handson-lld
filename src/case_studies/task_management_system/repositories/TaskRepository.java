package case_studies.task_management_system.repositories;

import case_studies.task_management_system.models.Task;
import case_studies.task_management_system.repositories.specification.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class TaskRepository {

    private final TaskManagementDatabase db;
    private TaskRepository() {
        this.db = TaskManagementDatabase.getInstance();
    }

    public void save(Task task) {
        // check for constraints if any
        if(db.getTaskTable().containsKey(task.getId()))
            throw new IllegalArgumentException("DuplicateKey exception!!");

        this.db.getTaskTable().put(task.getId(), task);

        // update indexes
        this.db.getUserTaskIndex().putIfAbsent(task.getCreatedBy().getId(), new ArrayList<>());
        this.db.getUserTaskIndex().get(task.getCreatedBy().getId()).add(task);
    }

    public void update(Task task) {
        this.db.getTaskTable().put(task.getId(), task);
    }

    public Optional<Task> findById(Integer id) {
        if(id == null)
            throw new IllegalArgumentException("Id cannot be null.");

        Task task = db.getTaskTable().getOrDefault(id, null);

        return task == null ? Optional.empty() : Optional.of(task);
    }

    public boolean isTaskPresent(Integer id) {
        return db.getTaskTable().containsKey(id);
    }

    public List<Task> findBySpecification(Specification<Task> spec) {
        return db.getTaskTable().values().stream().filter(spec::isSatisfiedBy).toList();
    }

    public void deleteById(Integer id) {
        db.getTaskTable().remove(id);
    }


    // singleton code
    private static final class InstanceHolder {
        private static final TaskRepository instance = new TaskRepository();
    }

    public static TaskRepository getInstance() {
        return TaskRepository.InstanceHolder.instance;
    }
}
