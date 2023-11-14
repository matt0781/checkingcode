package Users;

import java.io.Serializable;

public class Faculty implements Serializable {
    public enum FACULTY_TYPE {
        SCSE, EEE, SSS, NBS, CCEB, CEE, MSE, MAE, ADM, SOH, WKWSCI, SBS, SPMS, ASE, LKC, NIE, UNIVERSE
    };

    public static FACULTY_TYPE getFacultyType(String facultyName) {
        try {
            return FACULTY_TYPE.valueOf(facultyName.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Unrecognised faculty... Try again");
            return null;
        }
    }

    public static Faculty.FACULTY_TYPE getFacultyFromChoice(int choice) {
        switch (choice) {
            case 1:
                return Faculty.FACULTY_TYPE.SCSE;
            case 2:
                return Faculty.FACULTY_TYPE.EEE;
            case 3:
                return Faculty.FACULTY_TYPE.SSS;
            case 4:
                return Faculty.FACULTY_TYPE.NBS;
            case 5:
                return Faculty.FACULTY_TYPE.CCEB;
            case 6:
                return Faculty.FACULTY_TYPE.CEE;
            case 7:
                return Faculty.FACULTY_TYPE.MSE;
            case 8:
                return Faculty.FACULTY_TYPE.MAE;
            case 9:
                return Faculty.FACULTY_TYPE.ADM;
            case 10:
                return Faculty.FACULTY_TYPE.SOH;
            case 11:
                return Faculty.FACULTY_TYPE.WKWSCI;
            case 12:
                return Faculty.FACULTY_TYPE.SBS;
            case 13:
                return Faculty.FACULTY_TYPE.SPMS;
            case 14:
                return Faculty.FACULTY_TYPE.ASE;
            case 15:
                return Faculty.FACULTY_TYPE.LKC;
            case 16:
                return Faculty.FACULTY_TYPE.NIE;
            case 17:
                return Faculty.FACULTY_TYPE.UNIVERSE;
            default:
                return null;

        }
    }

}
