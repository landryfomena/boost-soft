package boostSoft.boostTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import boostSoft.boostTest.data.CustomField;

public interface CustomFieldRepository extends JpaRepository<CustomField, Long> {

}
