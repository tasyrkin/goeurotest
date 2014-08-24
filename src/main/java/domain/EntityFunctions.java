package domain;

import com.google.common.base.Function;

import domain.json.Entity;

public class EntityFunctions {

    public static final Function<Entity, domain.csv.Entity> ENTITY_JSON_TO_CSV_NULL_IF_DATA_MISSING =
        new Function<Entity, domain.csv.Entity>() {
            @Override
            public domain.csv.Entity apply(final Entity entity) {

                if (oneOfFieldsMissing(entity)) {
                    return null;
                }

                domain.csv.Entity result = new domain.csv.Entity();

                result.setId(entity.get_id());
                result.setName(entity.getName());
                result.setType(entity.getType());
                result.setLatitude(entity.getGeo_position().getLatitude());
                result.setLongitude(entity.getGeo_position().getLongitude());

                return result;
            }

            private boolean oneOfFieldsMissing(final Entity entity) {
                //J-
                return entity == null
                        || entity.get_id() == null
                        || entity.getName() == null
                        || entity.getType() == null
                        || entity.getGeo_position() == null
                        || entity.getGeo_position().getLatitude() == null
                        || entity.getGeo_position().getLongitude() == null;
                //J+
            }
        };

}
