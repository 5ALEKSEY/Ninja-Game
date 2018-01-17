import java.io.*;
import java.util.Scanner;

/**
 * Class is required for memory management
 */
public class InformationControl {
    private File RulesFile = new File("D:/Алексей/Программы/work_space/JavaGameProject/src/GameRules.txt");
    private File RecordTableFile = new File("D:/Алексей/Программы/work_space/JavaGameProject/src/RecordTable.txt");

    /**
     * A method is required to read game rules from a file
     * @return rules of the game
     * @throws IOException Expected exceptions
     */
    public java.lang.String GetRules() throws IOException {
        java.lang.String RulesString = new java.lang.String();
        Scanner scanner = new Scanner(new FileInputStream(RulesFile), "UTF-8");
        while (scanner.hasNextLine()) {
            RulesString = RulesString + scanner.nextLine() + "\n";
        }
        return RulesString;
    }

    /**
     * The method is necessary to get all the names in the table of records
     * @return list of names of the table of records
     * @throws IOException Expected exceptions
     */
    public java.lang.String GetRecordName() throws IOException {
        java.lang.String RulesString = new java.lang.String();
        Scanner scanner = new Scanner(new FileInputStream(RecordTableFile), "UTF-8");
        while (scanner.hasNextLine()) {
            RulesString = RulesString + GetNameFromRecord(scanner.nextLine()) + "\n";
        }
        return RulesString;
    }

    /**
     * The method is necessary to get all the times in the table of records
     * @return list of times of the table of records
     * @throws IOException Expected exceptions
     */
    public java.lang.String GetRecordTime() throws IOException {
        java.lang.String RulesString = new java.lang.String();
        Scanner scanner = new Scanner(new FileInputStream(RecordTableFile), "UTF-8");
        while (scanner.hasNextLine()) {
            RulesString = RulesString + GetTimeFromRecord(scanner.nextLine()) + "\n";
        }
        return RulesString;
    }

    /**
     * Method for obtaining a name from a record
     * @param temprecord record
     * @return name from record
     */
    public java.lang.String GetNameFromRecord(java.lang.String temprecord) {
        java.lang.String[] RecordName =  temprecord.split(" ");
        return RecordName[0];
    }

    /**
     * Method for obtaining a time from a record
     * @param temprecord record
     * @return time from record
     */
    public Integer GetTimeFromRecord(java.lang.String temprecord) {
        java.lang.String[] RecordTime =  temprecord.split(" ");
        return Integer.parseInt(RecordTime[1]);
    }

    /**
     * Method for finding the time of the record by nickname
     * @param nickNameForSeach nickname for time search
     * @return time of the record by nickname from record table
     * @throws IOException Expected exceptions
     */
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

    /**
     * The method is necessary to save the record
     * @param recordname record nickname
     * @param timecount record time
     * @throws IOException Expected exceptions
     */
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
