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

    public java.lang.String GetRecordName() throws IOException {
        java.lang.String RulesString = new java.lang.String();
        Scanner scanner = new Scanner(new FileInputStream(RecordTableFile), "UTF-8");
        while (scanner.hasNextLine()) {
            RulesString = RulesString + GetNameFromRecord(scanner.nextLine()) + "\n";
        }
        return RulesString;
    }

    public java.lang.String GetRecordTime() throws IOException {
        java.lang.String RulesString = new java.lang.String();
        Scanner scanner = new Scanner(new FileInputStream(RecordTableFile), "UTF-8");
        while (scanner.hasNextLine()) {
            RulesString = RulesString + GetTimeFromRecord(scanner.nextLine()) + "\n";
        }
        return RulesString;
    }

    public java.lang.String GetNameFromRecord(java.lang.String temprecord) {
        java.lang.String[] RecordName =  temprecord.split(" ");
        return RecordName[0];
    }

    public Integer GetTimeFromRecord(java.lang.String temprecord) {
        java.lang.String[] RecordTime =  temprecord.split(" ");
        return Integer.parseInt(RecordTime[1]);
    }

    public java.lang.String GetTimeFromNickName(java.lang.String nickNameForSeach) throws IOException {
        java.lang.String TempRecord = new java.lang.String();
        Scanner scanner = new Scanner(new FileInputStream(RecordTableFile), "UTF-8");
        while (scanner.hasNextLine()) {
            TempRecord = scanner.nextLine();
            if (GetNameFromRecord(TempRecord).equals(nickNameForSeach))
                return GetTimeFromRecord(TempRecord).toString();
        }
        return null;
    }

    public void SaveRecord(java.lang.String recordname,Integer timecount) throws IOException {
        java.lang.String TempRecordTable = new java.lang.String();
        java.lang.String TempRecord = new java.lang.String();
        boolean writeFlag = false;
        java.lang.String Temp = GetTimeFromNickName(recordname);
        if (GetTimeFromNickName(recordname) != null) {
            Scanner scanner = new Scanner(new FileInputStream(RecordTableFile), "UTF-8");
            while (scanner.hasNextLine()) {
                TempRecord = scanner.nextLine();
                if (recordname.equals(GetNameFromRecord(TempRecord)))
                    continue;
                else if (timecount <= GetTimeFromRecord(TempRecord) && writeFlag == false){
                    TempRecordTable += recordname + " " + timecount.toString() + "\n" + TempRecord + "\n";
                    writeFlag = true;
                } else
                    TempRecordTable += TempRecord + "\n";
            }
        } else {
            Scanner scanner = new Scanner(new FileInputStream(RecordTableFile), "UTF-8");
            while (scanner.hasNextLine()) {
                TempRecord = scanner.nextLine();
                if (timecount <= GetTimeFromRecord(TempRecord) && writeFlag == false) {
                    TempRecordTable += recordname + " " + timecount.toString() + "\n" + TempRecord + "\n";
                    writeFlag = true;
                } else
                    TempRecordTable += TempRecord + "\n";
            }
        }
        FileWriter fileWriter = new FileWriter(RecordTableFile);
        fileWriter.write(TempRecordTable);
        fileWriter.close();
    }
}
