package ar.com.gestor.stats;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import ar.com.gestor.stats.model.City;
import ar.com.gestor.stats.util.ParserUtil;

@SpringBootApplication
@EnableAutoConfiguration
public class Aplication implements CommandLineRunner {

	protected final Log logger = LogFactory.getLog(getClass());

	public static void main(String[] args) {
		SpringApplication.run(Aplication.class, args);
	}

	@Bean("cities")
	public List<City> initCitiesBean() throws IOException {

		try (Stream<Path> paths = Files.walk(Paths.get("src/main/resources/input"))) {
			List<City> result = paths.filter(Files::isRegularFile)
					.map(x -> x.getFileName()).map(fileName -> {
						ClassLoader cl = getClass().getClassLoader();
						try {
							File file = ResourceUtils.getFile(cl.getResource("input/"+fileName).getFile());
							return ParserUtil.parser(file);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return new City();
						}
					})
					.collect(Collectors.toList());
			return result;
		}
	}

	public void run(String... args) throws JsonParseException, JsonMappingException, IOException {
		logger.info("Application Started !!");
		
		this.initCitiesBean().stream().forEach(System.out::println);

	}

}
