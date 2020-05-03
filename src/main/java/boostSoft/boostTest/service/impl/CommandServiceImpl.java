package boostSoft.boostTest.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import boostSoft.boostTest.data.Command;
import boostSoft.boostTest.data.CommandStatus;
import boostSoft.boostTest.data.Product;
import boostSoft.boostTest.repository.CommandRepository;
import boostSoft.boostTest.repository.ProductRepository;
import boostSoft.boostTest.service.api.CommandServiceApi;

@Service
public class CommandServiceImpl implements CommandServiceApi {
	
	@Autowired CommandRepository commandRepository;
	@Autowired ProductRepository productRepository;

	@Override
	public HttpEntity<? extends Object> createCommand(Command command) {
		try {
			Command currentCommand = commandRepository.save(command);
			Date date = new Date();
			command.setDateCreation(date);
			command.setStatus(CommandStatus.EN_ATTENTE.getStatut());
			commandRepository.save(currentCommand);
			
			if (currentCommand.getProducts() != null) {
				List<Product> currentProducts = currentCommand.getProducts();
				for (Product product : currentProducts) {
					product.getCommands().add(currentCommand);
					productRepository.saveAndFlush(product);
				}
				commandRepository.saveAndFlush(currentCommand);
			}
			
			return new ResponseEntity<Command>(currentCommand, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
