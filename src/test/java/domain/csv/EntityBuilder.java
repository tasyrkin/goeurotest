package domain.csv;

import java.util.Random;

public class EntityBuilder {

    private Entity entity;
    private Random random = new Random();

    public EntityBuilder() {
        initialize();
    }

    private void initialize() {
        entity = new Entity();
        entity.setId(String.valueOf(random.nextInt()));
        entity.setName("testName");
        entity.setType("testType");
        entity.setLatitude(String.valueOf(random.nextDouble()));
        entity.setLongitude(String.valueOf(random.nextDouble()));
    }

    public EntityBuilder withName(final String name) {
        entity.setName(name);
        return this;
    }

    public EntityBuilder withType(final String type) {
        entity.setType(type);
        return this;
    }

    public Entity build() {
        Entity toReturn = entity;
        initialize();
        return toReturn;
    }
}
