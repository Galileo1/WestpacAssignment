package org.galileo1.bdd;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.galileo1.bdd.pageobj.Kiwisaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.galileo1.bdd.datamodel.*;

public class SampleTestClass {

  @Autowired
  private Kiwisaver kiwisavePage;

    @Test(dataProvider = "getData")
    public void testMethod(DataModel data) {
        System.out.println(data);
        kiwisavePage.enterDetails3(data); 
    }

    @DataProvider
    public Object[][] getData() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("src/test/resources/data.json"));
        JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet");
        List<DataModel> testData = new Gson().fromJson(dataSet, new TypeToken<List<DataModel>>() {
        }.getType());
        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for (Object[] each : returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;
    }
}