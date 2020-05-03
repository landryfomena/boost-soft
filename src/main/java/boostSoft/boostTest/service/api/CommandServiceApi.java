package boostSoft.boostTest.service.api;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import boostSoft.boostTest.data.Command;

@Service
public interface CommandServiceApi {

	public abstract HttpEntity<? extends Object> createCommand(Command command);
}
