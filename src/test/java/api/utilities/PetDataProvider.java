package api.utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import api.payloads.Pet;
import api.payloads.User;

public class PetDataProvider {
	
	@DataProvider(name="PetDP")
	public Object[][] getAddNewPetData() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		
		String cwd = System.getProperty("user.dir");
		String jsonFile = cwd+"//testData//AddNewPet.json";
		
		JsonElement addPetJsonData = JsonParser.parseReader( new FileReader(jsonFile));
		JsonElement dataSet = addPetJsonData.getAsJsonObject().get("PetData");
		
		List<Pet> testAddPetdata = new Gson().fromJson(dataSet, new TypeToken<List<Pet>>() {
		}.getType());	
		
		Object[][] returnData = new Object[testAddPetdata.size()][1];
		
		int index = 0;
        for (Object[] each : returnData) {
            each[0] = testAddPetdata.get(index++);
        }
		
		return returnData;
	}
	
}
