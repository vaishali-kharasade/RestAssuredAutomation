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

import api.payloads.User;

public class UserDataProvider {
	
	@DataProvider(name="UserDP")
	public Object[][] getUserData() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		
		String cwd = System.getProperty("user.dir");
		String jsonFile = cwd+"//testData//UserDetails.json";
		
		JsonElement userJsonData = JsonParser.parseReader( new FileReader(jsonFile));
		JsonElement dataSet = userJsonData.getAsJsonObject().get("UserData");
		
		List<User> testUserdata = new Gson().fromJson(dataSet, new TypeToken<List<User>>() {
		}.getType());	
		
		Object[][] returnData = new Object[testUserdata.size()][1];
		
		int index = 0;
        for (Object[] each : returnData) {
            each[0] = testUserdata.get(index++);
        }
		
		return returnData;
	}

}
