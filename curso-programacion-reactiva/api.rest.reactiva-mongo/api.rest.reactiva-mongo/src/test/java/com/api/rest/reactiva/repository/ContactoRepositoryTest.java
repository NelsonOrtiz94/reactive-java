package com.api.rest.reactiva.repository;

import com.api.rest.reactiva.documents.Contacto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.test.StepVerifier;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContactoRepositoryTest {

    @Autowired
    private ContactoRepository contactoRepository;

    private ReactiveMongoOperations mongoOperations;

    @BeforeAll
    public void insertarDatos() {
        Contacto contacto1 = new Contacto();
        contacto1.setNombre("Test1");
        contacto1.setEmail("c1@gmail.com");
        contacto1.setTelefono("133222");

        Contacto contacto2 = new Contacto();
        contacto2.setNombre("Test2");
        contacto2.setEmail("c2@gmail.com");
        contacto2.setTelefono("233222");

        Contacto contacto3 = new Contacto();
        contacto3.setNombre("Test3");
        contacto3.setEmail("c3@gmail.com");
        contacto3.setTelefono("333222");

        //Guardar contactos
        StepVerifier.create(contactoRepository.insert(contacto1).log())
                .expectNextCount(1)
                .verifyComplete();
    }
}
