package cl.chl.rs.project.earthquake.earthquake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"cl.chl.rs.project.earthquake"})
public class EarthquakeApplication{

	public static void main(String[] args) {
		SpringApplication.run(EarthquakeApplication.class, args);
	}
}
