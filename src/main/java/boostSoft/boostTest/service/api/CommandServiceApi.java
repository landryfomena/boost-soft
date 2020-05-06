package boostSoft.boostTest.service.api;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import boostSoft.boostTest.data.Command;

@Service
public interface CommandServiceApi {

	public abstract HttpEntity<? extends Object> createCommand(Command command);
	public abstract HttpEntity<? extends Object> confirmCommand(Long id);
	public abstract HttpEntity<? extends Object> cancelCommand(Long id);
	public abstract HttpEntity<? extends Object> deleverCommand(Long id);
}
