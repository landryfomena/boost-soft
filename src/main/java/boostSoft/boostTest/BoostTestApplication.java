package boostSoft.boostTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import boostSoft.boostTest.service.impl.UserServiceImpl;

@SpringBootApplication
public class BoostTestApplication implements CommandLineRunner {
	
	@Autowired UserServiceImpl userServiceImpl;
	
	private final static String ACCOUNT_SID = "ACd1667c4efdd5e5790fe7c3254046776b";
	private final static String AUTH_TOKEN = "7b31db8ba18a32eaff173d42b673e960";
	private final static String TWILIO_NUMBER = "+12052935591";

	public static void main(String[] args) {
		SpringApplication.run(BoostTestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		/*Message.creator(new PhoneNumber("+237693836208"), new PhoneNumber(TWILIO_NUMBER), "Your validation code is : ").create();*/
	}

}
