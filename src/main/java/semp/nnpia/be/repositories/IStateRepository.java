package semp.nnpia.be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import semp.nnpia.be.domains.State;

public interface IStateRepository extends JpaRepository<State, Long> {
    State findStateByShortcut(String shortcut);
}