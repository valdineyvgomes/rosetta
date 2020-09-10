package org.rosetta;

import org.rosetta.model.Column;
import org.rosetta.model.Rating;
import org.rosetta.model.Relationship;
import org.rosetta.model.Table;
import org.rosetta.model.User;
import org.rosetta.repository.TableRepository;
import org.rosetta.repository.UserRepository;
import java.util.List;
import org.rosetta.enumeration.Resource;
import org.rosetta.model.Comment;
import org.rosetta.model.ExternalReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.query.TextCriteria;

@SpringBootApplication
public class RosettaApplication implements CommandLineRunner {

    @Autowired
    private TableRepository personRepository;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(RosettaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        personRepository.deleteAll();
        userRepository.deleteAll();

        User user = new User();
        user.setUsername("Valdiney");

        user = userRepository.save(user);

        Rating rating = new Rating();
        rating.setUser(user);
        rating.setRate(3);
        rating.addComment(new Comment(user, "Muito bom!"));
        rating.addComment(new Comment(user, "Falta alguma coisa ainda!"));

        Column field1 = new Column();
        field1.setName("id");
        field1.setType("String");
        field1.setDescription("Pato Igreja");

        Column field2 = new Column();
        field2.setName("xxx");
        field2.setType("String");
        field2.setDescription("Pato Bola Escola");
        field2.setEnabled(false);

        Table table = new Table();
        table.setName("customer");
        table.addField(field1);
        table.addField(field2);

        table.addRating(rating);

        ExternalReference eee = new ExternalReference();
        eee.setAddress("http://github.com.br/rosetta");
        eee.setResource(Resource.GITHUB);

        table.addExternalReferences(eee);

        personRepository.save(table);

        Relationship xx = new Relationship();
        xx.setTable(table);

        Column field3 = new Column();
        field3.setName("dsadsadsa");
        field3.setType("String");
        field3.setDescription("Pato Bola Escola");
        field3.setEnabled(false);
        field3.addRelationships(xx);

        Table table1 = new Table();
        table1.setName("tttt");
        table1.addField(field2);
        table1.addField(field3);

        personRepository.save(table1);

        List<Table> findAll = personRepository.findAllBy(TextCriteria.forDefaultLanguage().matching("Igreja"));
    }
}
