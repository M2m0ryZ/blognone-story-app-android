package com.benzneststudios.greendao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.benzneststudios.blognonestory");

        schema = setSchemaBlognoneTags(schema);
        schema = setSchemaBlognoneTopicCache(schema);
        schema = setSchemaBlognoneFavoriteTopic(schema);
        schema = setSchemaBlognoneHistoryTopic(schema);

        new DaoGenerator().generateAll(schema, args[0]);
    }

    private static Schema setSchemaBlognoneTags(Schema schema) {
        Entity entity = schema.addEntity("BlognoneTags");

        entity.addIdProperty();
        entity.addStringProperty("name");
        entity.addStringProperty("icon");
        entity.addBooleanProperty("favorite");
        entity.addStringProperty("endpoint");
        return schema;
    }

    private static Schema setSchemaBlognoneTopicCache(Schema schema) {
        Entity entity = schema.addEntity("BlognoneTopicCache");

        entity.addIdProperty();
        entity.addStringProperty("data");
        entity.addLongProperty("datetime");
        return schema;
    }

    private static Schema setSchemaBlognoneFavoriteTopic(Schema schema) {
        Entity entity = schema.addEntity("BlognoneFavoriteTopic");

        entity.addIdProperty();
        entity.addStringProperty("data");
        entity.addLongProperty("datetime");
        return schema;
    }

    private static Schema setSchemaBlognoneHistoryTopic(Schema schema) {
        Entity entity = schema.addEntity("BlognoneHistoryTopic");

        entity.addIdProperty();
        entity.addLongProperty("nodeId");
        entity.addStringProperty("data");
        entity.addLongProperty("datetime");
        entity.addIntProperty("countComment");
        return schema;
    }

}
