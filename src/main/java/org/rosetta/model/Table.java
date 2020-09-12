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
package org.rosetta.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Valdiney V GOMES
 */
@Document
public class Table {

    @Id
    private String id;

    @DBRef
    private Database database;

    @Indexed(unique = true)
    private String name;

    @TextIndexed
    private List<ExternalReference> externalReferences = new ArrayList();

    @TextIndexed
    private List<Column> columns = new ArrayList();

    @TextIndexed
    private List<Definition> definitions = new ArrayList();
    private List<Rating> ratings = new ArrayList();
    private List<User> owners = new ArrayList();
    private boolean enabled = true;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<Definition> columns) {
        this.definitions = columns;
    }

    public void addDefinition(Definition definition) {
        this.definitions.add(definition);
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public void addField(Column field) {
        this.columns.add(field);
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void addRating(Rating rating) {
        this.ratings.add(rating);
    }

    public List<User> getOwners() {
        return owners;
    }

    public void setOwners(List<User> owners) {
        this.owners = owners;
    }

    public void addOwner(User owner) {
        this.owners.add(owner);
    }

    public List<ExternalReference> getExternalReferences() {
        return externalReferences;
    }

    public void setExternalReferences(List<ExternalReference> externalReferences) {
        this.externalReferences = externalReferences;
    }

    public void addExternalReferences(ExternalReference externalReference) {
        this.externalReferences.add(externalReference);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        final Table other = (Table) obj;

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        if (!Objects.equals(this.id, other.id)) {
            return false;
        }

        return Objects.equals(this.name, other.name);
    }
}
