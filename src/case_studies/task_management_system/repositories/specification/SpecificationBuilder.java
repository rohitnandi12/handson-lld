package case_studies.task_management_system.repositories.specification;

public class SpecificationBuilder<T>  {

    Specification<T> rootSpecification = x -> true;// Specification.getEmptySpecification();

    public static <T> SpecificationBuilder<T> builder() {
        return new SpecificationBuilder<>();
    }

    public SpecificationBuilder<T> and(Specification<T> spec) {
        this.rootSpecification = new AndSpecification<>(rootSpecification, spec);
        return this;
    }

    public SpecificationBuilder<T> or(Specification<T> spec) {
        this.rootSpecification = new OrSpecification<>(rootSpecification, spec);
        return this;
    }

    public Specification<T> build() {
        return rootSpecification;
    }
}
