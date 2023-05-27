package semp.nnpia.be.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import semp.nnpia.be.domains.State;
import semp.nnpia.be.repositories.IStateRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StateService {
    private final IStateRepository stateRepository;

    public State createState(State state) {
        return stateRepository.save(state);
    }

    public State getStateById(final Long id) throws ResourceNotFoundException {
        Optional<State> result = stateRepository.findById(id);

        if (result.isEmpty()) throw new ResourceNotFoundException();

        return result.get();
    }
}