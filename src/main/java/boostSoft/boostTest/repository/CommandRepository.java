package boostSoft.boostTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import boostSoft.boostTest.data.Command;

public interface CommandRepository extends JpaRepository<Command, Long> {

}
