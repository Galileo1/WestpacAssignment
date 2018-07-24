package org.galileo1.bdd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.galileo1.bdd.datamodel.*;

import com.google.gson.Gson;

import org.springframework.stereotype.Component;

@Component
public class JsonDataProvider {


    private List<InfoMessageDataModel> messageList;

    public JsonDataProvider() throws FileNotFoundException {
       messageList = getInfoMessageData();
    }

	private List<InfoMessageDataModel> getInfoMessageData() {
		Gson gson = new Gson();
		BufferedReader bufferReader = null;
		try {
			bufferReader = new BufferedReader(new FileReader("src/test/resources/Infomessage.json"));
			InfoMessageDataModel[] InfoMessages = gson.fromJson(bufferReader, InfoMessageDataModel[].class);
			return Arrays.asList(InfoMessages);
		}catch(FileNotFoundException e) {
			throw new RuntimeException("Json file not found at path : ");
		}finally {
			try { if(bufferReader != null) bufferReader.close();}
			catch (IOException ignore) {}
		}
	}

    public final InfoMessageDataModel getMessageData(String field) {

        //List<InfoMessageDataModel> list 
        //ArrayList<InfoMessageDataModel> list = new ArrayList(Arrays.asList(messageList));
        // List<InfoMessageDataModel> list = Arrays.stream(messageList)  //'array' is two-dimensional
        //                         .flatMap(Arrays::asList)
        //                         .collect(Collectors.toList());
        System.out.println("=--------------000--=-==--="+messageList.get(0));
        return messageList.stream().filter(p-> p.getField().equalsIgnoreCase(field)).findAny().get();
        
        

    }

    


}
	