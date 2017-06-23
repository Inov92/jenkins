package org.protasov.ii.testbase;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProviders {
    @DataProvider(name = "dataFromMethod")
    public Object[][] getUsers() {
        return new Object[][]{{"epam", "1234"}};
    }

    @DataProvider(name = "dataFromCSV")
    public Object[][] getCsvFile(){
        TestSettings csvLink = new TestSettings();
        return loadDataFromCsv(csvLink.getCssPath());
    }

    private Object[][] loadDataFromCsv(String path){
        String name = "";
        String pass = "";
        int linesCount = 0;
        Object[][] csvData = null;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        List<Object []> profiles = new ArrayList<Object []>();

        try {
            br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] login = line.split(cvsSplitBy);
                profiles.add(new Object[]{login[0], login[1]});
                linesCount++;
            }
            csvData = new Object[linesCount][2];
            for (int j = 0; j < profiles.size(); j++) {
                csvData[j] = profiles.get(j);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return csvData;
    }
}
