package vttp.paf.day26workshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.paf.day26workshop.repositories.GameRepository;

@SpringBootApplication
public class Day26workshopApplication implements CommandLineRunner {

	@Autowired
	GameRepository gameRepo;

	@Override
	public void run(String... args){
		// gameRepo.getGame(100, 5);
		// System.exit(0);

	}

	public static void main(String[] args) {
		SpringApplication.run(Day26workshopApplication.class, args);
	}

}
