package com.github.yuweiguocn.greendao;


import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(11, "com.github.yuweiguocn.demo.greendao.db");
        addTest(schema);
        addTest2(schema);
        addTest3(schema);
        new DaoGenerator().generateAll(schema, "../GreenDaoUpgradeHelper/app/src/main/java-gen");
    }


    private static void addTest(Schema schema) {
        Entity testData = schema.addEntity("Group");
        testData.addIdProperty().primaryKey().autoincrement();
        testData.addStringProperty("testString");
        testData.addLongProperty("testLong");
        testData.addDateProperty("testDate");
        testData.addIntProperty("testInt");
        testData.addBooleanProperty("testBoolean");
    }
    private static void addTest2(Schema schema) {
        Entity testData = schema.addEntity("TestData2");
        testData.addIdProperty().primaryKey().autoincrement();
        testData.addStringProperty("testString");
        testData.addLongProperty("testLong");
        testData.addDateProperty("testDate");
        testData.addIntProperty("testInt");
        testData.addBooleanProperty("testBoolean");
    }
    private static void addTest3(Schema schema) {
        Entity testData = schema.addEntity("TestData3");
        testData.addIdProperty().primaryKey().autoincrement();
        testData.addStringProperty("testString");
        testData.addLongProperty("testLong");
        testData.addDateProperty("testDate");
        testData.addIntProperty("testInt");
        testData.addBooleanProperty("testBoolean");
    }
}
