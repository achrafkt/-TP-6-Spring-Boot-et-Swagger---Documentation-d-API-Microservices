package com.example.studentmanagement;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// Test d'integration : il demarre tout le contexte Spring, donc a besoin d'un MySQL actif sur localhost:3306.
// Desactive pour que "mvn test" passe sans base de donnees ; les tests unitaires (StudentControllerTest) n'en dependent pas.
@Disabled("Nécessite une base MySQL locale (studentdb) en fonctionnement")
@SpringBootTest
class StudentManagementApplicationTests {

	@Test
	void contextLoads() {
	}

}
