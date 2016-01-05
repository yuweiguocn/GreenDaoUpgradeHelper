package com.github.yuweiguocn.greendao;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(6, "com.github.yuweiguocn.demo.greendao.db");
        addTest(schema);
        addTest2(schema);
        addTest3(schema);
        new DaoGenerator().generateAll(schema, "../GreenDaoUpgradeHelper/app/src/main/java-gen");
    }


    private static void addTest(Schema schema) {
        Entity testData = schema.addEntity("TestData");
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
