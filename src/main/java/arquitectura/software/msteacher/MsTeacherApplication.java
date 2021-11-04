package arquitectura.software.msteacher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsTeacherApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTeacherApplication.class, args);
	}

}
