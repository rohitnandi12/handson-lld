package case_studies.task_management_system.repositories.specification;

public interface Specification<T> {
    static <T> Specification<T> getEmptySpecification() {
        return t -> true;
    }

    boolean isSatisfiedBy(T item);
}
