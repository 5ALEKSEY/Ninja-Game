import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.Scanner;

public class InformationControl {
    private File RulesFile = new File("D:/Алексей/Программы/work_space/JavaGameProject/src/GameRules.txt");
    private File RecordTableFile = new File("D:/Алексей/Программы/work_space/JavaGameProject/src/RecordTable.txt");

    public java.lang.String GetRules() throws IOException {
        java.lang.String RulesString = new java.lang.String();
        Scanner scanner = new Scanner(new FileInputStream(RulesFile), "UTF-8");
        while (scanner.hasNextLine()) {
            RulesString = RulesString + scanner.nextLine() + "\n";
        }
        return RulesString;
    }

    public ObservableList<Records> getRecordsList() throws IOException {
        java.lang.String TempRecord = new java.lang.String();
        ObservableList<Records> records = FXCollections.observableArrayList();
        Scanner scanner = new Scanner(new FileInputStream(RecordTableFile), "UTF-8");
        while (scanner.hasNextLine()) {
            TempRecord = scanner.nextLine();
            records.add(new Records(GetNameFromRecord(TempRecord), GetTimeFromRecord(TempRecord)));
        }
        return records;
    }

    public java.lang.String GetNameFromRecord(java.lang.String temprecord) {
        java.lang.String[] RecordName =  temprecord.split(" ");
        return RecordName[0];
    }

    public Double GetTimeFromRecord(java.lang.String temprecord) {
        java.lang.String[] RecordTime =  temprecord.split(" ");
        return Double.parseDouble(RecordTime[1]);
    }
}
