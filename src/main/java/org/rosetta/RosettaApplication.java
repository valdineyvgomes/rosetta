/**
 * Copyright 2020 Valdiney V GOMES
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rosetta;

import java.util.List;
import org.rosetta.enumeration.Resource;
import org.rosetta.model.Column;
import org.rosetta.model.Comment;
import org.rosetta.model.Definition;
import org.rosetta.model.ExternalReference;
import org.rosetta.model.Rating;
import org.rosetta.model.Relationship;
import org.rosetta.model.Table;
import org.rosetta.model.User;
import org.rosetta.repository.TableRepository;
import org.rosetta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.query.TextCriteria;

/**
 *
 * @author Valdiney V GOMES
 */
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

        Definition definition = new Definition();
        definition.setDefinition("Total de produtos vendidos");
        definition.addRating(rating);

        Column field1 = new Column();
        field1.setName("id");
        field1.setType("String");
        field1.addDefinition(definition);

        Column field2 = new Column();
        field2.setName("xxx");
        field2.setType("String");
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

        Definition definition1 = new Definition();
        definition1.setDefinition("Escola");

        Column field3 = new Column();
        field3.setName("dsadsadsa");
        field3.setType("String");
        field3.addDefinition(definition1);
        field3.setEnabled(false);
        field3.addRelationships(xx);

        Table table1 = new Table();
        table1.setName("tttt");
        //table1.addField(field2);
        table1.addField(field3);

        personRepository.save(table1);

        List<Table> findAll = personRepository.findAllBy(TextCriteria.forDefaultLanguage().matching("Escola"));

        for (Table ttt : findAll) {
            for (Column column : ttt.getColumns()) {
                System.out.println(column.getRelationships());
            }
        }
    }
}
