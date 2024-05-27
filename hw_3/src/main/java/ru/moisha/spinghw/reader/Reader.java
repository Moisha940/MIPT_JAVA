package ru.moisha.spinghw.reader;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import ru.moisha.spinghw.checker.Checker;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Reader {
    private final Checker checker;

    public  void getData(String PATH) {
        File dir = new File(PATH);
        File[] files = dir.listFiles();
        List<String> inputData = new ArrayList<>();

        if (dir.isDirectory() && files != null) {
            for (File file : files) {

                String filePath = PATH + "/" + file.getName();
                StringBuilder currentInfo = new StringBuilder();

                try(FileReader reader = new FileReader(filePath))  {
                    int c;
                    while((c=reader.read()) != -1){
                        currentInfo.append((char) c);
                    }
                    inputData.add(currentInfo.toString());
                }
                catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
            }
            checker.check(inputData);
        }
    }
}
