package boostSoft.boostTest.service.impl;

import java.util.ArrayList;
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
import boostSoft.boostTest.data.ProductCommand;
import boostSoft.boostTest.repository.CommandRepository;
import boostSoft.boostTest.repository.ProductCommandrepository;
import boostSoft.boostTest.repository.ProductRepository;
import boostSoft.boostTest.service.api.CommandServiceApi;

@Service
public class CommandServiceImpl implements CommandServiceApi {

	@Autowired
	CommandRepository commandRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ProductCommandrepository productCommandrepository;

	@Override
	public HttpEntity<? extends Object> createCommand(Command command) {
		try {
			System.out.println("command " + command.toString());
			List<ProductCommand> realState = new ArrayList<ProductCommand>();
			List<ProductCommand> orders = command.getProductCommand();
			float totalprice=0;
			for (ProductCommand productCommand : orders) {
				System.out.println("command " + productCommand.toString());
				if (productCommand.getQuantityOrdered() <= productRepository
						.findById(productCommand.getProduct().getProductId()).get().getQuantity()) {
					productCommand.setAvailableQuantity(productCommand.getQuantityOrdered());
					realState.add(productCommand);
					
				} else {
					productCommand.setAvailableQuantity(
							productRepository.findByTitle(productCommand.getProduct().getTitle()).getQuantity());
					realState.add(productCommand);
			
				}
				totalprice=+(productCommand.getAvailableQuantity()*productCommand.getProduct().getPrice());

			}
			command.setTotalPrice(totalprice);
			command.setDateCreation(new Date());
			command.setStatus(CommandStatus.EN_ATTENTE.getStatut());
			command.setProductCommand(new ArrayList<ProductCommand>());
			//command.setProductCommand(realState);
			command = commandRepository.save(command);
			
			for (ProductCommand commandproduct : realState) {
				commandproduct.setCommand(command);
				command.getProductCommand().add(productCommandrepository.save(commandproduct));
				commandRepository.saveAndFlush(command);
				

			}
			return new ResponseEntity<Command>(commandRepository.findById(command.getCommandId()).get(), HttpStatus.CREATED);

			
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
	}
	@Override
	public HttpEntity<? extends Object> confirmCommand(Long id) {
		try {
			Command currentCommand= commandRepository.findById(id).get();
			currentCommand.setStatus(CommandStatus.VALIDE.getStatut());
			currentCommand=commandRepository.saveAndFlush(currentCommand);
			return new ResponseEntity<Command>(currentCommand, HttpStatus.ACCEPTED);

		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@Override
	public HttpEntity<? extends Object> cancelCommand(Long id) {
		try {
			Command currentCommand= commandRepository.findById(id).get();
			currentCommand.setStatus(CommandStatus.CANCELED.getStatut());
			currentCommand=commandRepository.saveAndFlush(currentCommand);
			return new ResponseEntity<Command>(currentCommand, HttpStatus.ACCEPTED);

		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
			}
	@Override
	public HttpEntity<? extends Object> deleverCommand(Long id) {
		try {
			Command currentCommand= commandRepository.findById(id).get();
			currentCommand.setStatus(CommandStatus.TERMINE.getStatut());
			currentCommand=commandRepository.saveAndFlush(currentCommand);
			return new ResponseEntity<Command>(currentCommand, HttpStatus.ACCEPTED);

		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
