package Menu;

import java.text.SimpleDateFormat;
import java.util.*;
import Camp.*;
import Controller.*;
import Program.*;
import Users.*;

public class CampMenu {
    public static Camp selectCamp(ArrayList<Camp> campArrayList) {
        if (campArrayList.isEmpty()) {
            System.out.println("No camps to choose to! Returning to main menu...");
            return null;
        }

        System.out.println("\nSelect camp:");
        while (true) {
            int i = 1;
            i = printCamps(campArrayList, i);
            System.out.printf("%d ->\tExit\n", i);
            System.out.print("\nChoice: ");
            int choice = MainProgram.sc.nextInt();
            MainProgram.sc.nextLine();
            if (choice == i) {
                return null;
            }
            while (choice > campArrayList.size() || choice <= 0) {
                System.out.print("Invalid input\nChoice: ");
                choice = MainProgram.sc.nextInt();
                MainProgram.sc.nextLine();
            }
            Camp targetCamp = campArrayList.get(choice - 1);
            System.out.printf("\n%s selected\n", targetCamp.getCampInformation().getName());
            System.out.println(targetCamp.getBasicCampDetail());
            return targetCamp;
        }
    }

    public static int printCamps(ArrayList<Camp> campArrayList, int i) {
        for (Camp camp : campArrayList) {
            CampInformation campInformation = camp.getCampInformation();
            if (campInformation == null) {
                continue;
            }
            System.out.printf("\n%d ->\t%s\n", i, campInformation.getName());
            i += 1;
        }
        return i;
    }

    public static void createCamp(AllUser allUser, AllCamp allCamp, Staff staff) {

        System.out.println("Let's create a camp!");

        System.out.print("Camp name: ");
        String name = MainProgram.sc.nextLine();

        System.out.print("Camp description: ");
        String description = MainProgram.sc.nextLine();

        int totalSlot = 0;
        boolean validTotalSlots = false;
        while (!validTotalSlots) {
            try {
                System.out.print("Total slots: ");
                totalSlot = MainProgram.sc.nextInt();
                MainProgram.sc.nextLine();
                if (totalSlot < 0) {
                    continue;
                }
                validTotalSlots = true;
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a valid integer.");
                MainProgram.sc.nextLine();
            }
        }

        System.out.print("Location: ");
        String location = MainProgram.sc.nextLine();

        ArrayList<Faculty.FACULTY_TYPE> facultyArrayList = new ArrayList<>();
        System.out.println("Choose the faculties that can participate:");
        System.out.println(
                "1.SCSE, 2.EEE, 3.SSS, 4.NBS, 5.CCEB, 6.CEE, 7.MSE, 8.MAE, 9.ADM, 10.SOH, 11.WKWSCI, 12.SBS, 13.SPMS, 14.ASE, 15.LKC, 16.NIE, 17.All Faculties, 18.Exit");
        int facultyChoice;
        int faculty_counter = 1;
        do {
            System.out.printf("Faculty %d: ", faculty_counter);
            faculty_counter += 1;
            facultyChoice = MainProgram.sc.nextInt();
            MainProgram.sc.nextLine();

            if (facultyChoice == 17) {
                facultyArrayList.clear();
                facultyArrayList.add(Faculty.FACULTY_TYPE.UNIVERSE);
                break;
            } else {
                Faculty.FACULTY_TYPE choice = Faculty.getFacultyFromChoice(facultyChoice);
                if (choice == null) {
                    continue;
                }
                facultyArrayList.add(choice);
            }
        } while (facultyChoice != 18);

        int visibilityChoice = 0;
        boolean validVisibilityChoice = false;
        while (!validVisibilityChoice) {
            try {
                System.out.print("Visibility:\n1. Visible to students\t2. Not visible to students\nChoice: ");
                visibilityChoice = MainProgram.sc.nextInt();
                MainProgram.sc.nextLine();
                validVisibilityChoice = true;
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter 1 for visible or 2 for not visible.");
                MainProgram.sc.nextLine();
            }
        }
        boolean visible = (visibilityChoice == 1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        Date registrationDate = null;

        while (true) {
            try {
                System.out.print("Camp commencement date in yyyy-MM-dd format: ");
                String dateString = MainProgram.sc.nextLine();
                startDate = sdf.parse(dateString);
                break;
            } catch (Exception e) {
                System.err.println("Error parsing the date. Please enter a valid date in yyyy-MM-dd format.");
            }
        }

        while (true) {
            try {
                System.out.print("Camp end date in yyyy-MM-dd format: ");
                String dateString = MainProgram.sc.nextLine();
                endDate = sdf.parse(dateString);

                if (endDate.before(startDate)) {
                    System.err.println("End date must be after the commencement date.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.err.println("Error parsing the date. Please enter a valid date in yyyy-MM-dd format.");
            }
        }

        while (true) {
            try {
                System.out.print("Camp registration deadline in yyyy-MM-dd format: ");
                String dateString = MainProgram.sc.nextLine();
                registrationDate = sdf.parse(dateString);

                if (registrationDate.after(startDate)) {
                    System.err.println("Registration deadline must be before the commencement date.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.err.println("Error parsing the date. Please enter a valid date in yyyy-MM-dd format.");
            }

        }
        Camp newCamp = new Camp(name, startDate, endDate, totalSlot, location, registrationDate, staff,
                facultyArrayList, visible, description);
        staff.addCamp(newCamp);
        allCamp.addCamp(newCamp);
    }

    public static void editCamps(Staff staff) {
        System.out.println("Which camp would you like to edit?");
        Camp editCamp = CampMenu.selectCamp(staff.getCampsCreated());
        if (editCamp == null) {
            return;
        }
        CampInformation campInformation = editCamp.getCampInformation();

        System.out.println(campInformation.getCampInformation());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        while (true) {
            System.out.print(
                    "Choose what aspect you would like to edit:\n(1)\tCamp Name\n(2)\tCamp Dates\n(3)\tTotal Slots\n(4)\tLocation\n(5)\tRegistration Deadline\n(6)\tFaculties\n(7)\tVisibility\n(8)\tDescription\n(9)\tExit\nChoice: ");
            int choice = MainProgram.sc.nextInt();
            MainProgram.sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter new camp name: ");
                    String newName = MainProgram.sc.nextLine();
                    campInformation.setName(newName);
                    break;
                case 2:

                    Date startDate = null;
                    Date endDate = null;

                    while (true) {
                        try {
                            System.out.print("Camp new commencement date in yyyy-MM-dd format: ");
                            String dateString = MainProgram.sc.nextLine();
                            startDate = sdf.parse(dateString);
                            break;
                        } catch (Exception e) {
                            System.err
                                    .println("Error parsing the date. Please enter a valid date in yyyy-MM-dd format.");
                        }
                    }

                    while (true) {
                        try {
                            System.out.print("Camp new end date in yyyy-MM-dd format: ");
                            String dateString = MainProgram.sc.nextLine();
                            endDate = sdf.parse(dateString);

                            if (endDate.before(startDate)) {
                                System.err.println("End date must be after the commencement date.");
                                continue;
                            }
                            break;
                        } catch (Exception e) {
                            System.err
                                    .println("Error parsing the date. Please enter a valid date in yyyy-MM-dd format.");
                        }
                    }
                    Date[] dateArray = { startDate, endDate };
                    campInformation.setCampDate(dateArray);
                    break;
                case 3:

                    int newTotalSlots = campInformation.getTotalSlot();
                    boolean validTotalSlots = false;
                    while (!validTotalSlots) {
                        try {
                            System.out.print("Enter new total slots: ");
                            newTotalSlots = MainProgram.sc.nextInt();
                            MainProgram.sc.nextLine();
                            if (newTotalSlots < 0) {
                                continue;
                            }
                            validTotalSlots = true;
                        } catch (InputMismatchException e) {
                            System.err.println("Invalid input. Please enter a valid integer.");
                            MainProgram.sc.nextLine();
                        }
                    }
                    campInformation.setTotalSlot(newTotalSlots);
                    break;
                case 4:
                    System.out.print("Enter new location: ");
                    String newLocation = MainProgram.sc.nextLine();
                    campInformation.setLocation(newLocation);
                    break;
                case 5:
                    Date newRegistrationDate = null;
                    Date currentStartDate = campInformation.getCampDate()[0];
                    while (true) {
                        try {
                            System.out.print("Camp commencement date in yyyy-MM-dd format: ");
                            String dateString = MainProgram.sc.nextLine();
                            newRegistrationDate = sdf.parse(dateString);
                            if (currentStartDate.before(newRegistrationDate)) {
                                continue;
                            }
                            campInformation.setRegistrationDeadline(newRegistrationDate);
                            break;
                        } catch (Exception e) {
                            System.err
                                    .println("Error parsing the date. Please enter a valid date in yyyy-MM-dd format.");
                        }
                    }
                    break;

                case 6:
                    System.out.println("\nCurrent faculties:");

                    int facultyEditChoice = 0;
                    do {
                        int i = 1;
                        for (Faculty.FACULTY_TYPE faculty : campInformation.getFaculties()) {
                            System.out.printf("%d->\t%s\n", i, faculty);
                            i += 1;
                        }
                        System.out.print(
                                "\nDo you want to: \n(1)\tAdd a faculty\n(2)\tRemove a faculty\n(3)\tExit\nChoice: ");
                        facultyEditChoice = MainProgram.sc.nextInt();
                        MainProgram.sc.nextLine();

                        switch (facultyEditChoice) {
                            case 1:
                                System.out.println("Choose the faculties that can participate:");
                                System.out.println(
                                        "1.SCSE, 2.EEE, 3.SSS, 4.NBS, 5.CCEB, 6.CEE, 7.MSE, 8.MAE, 9.ADM, 10.SOH, 11.WKWSCI, 12.SBS, 13.SPMS, 14.ASE, 15.LKC, 16.NIE, 17.All Faculties, 18.Exit");
                                System.out.print("Faculty: ");
                                int addFacultyChoice = MainProgram.sc.nextInt();
                                MainProgram.sc.nextLine();
                                if (Faculty.getFacultyFromChoice(addFacultyChoice) == Faculty.FACULTY_TYPE.UNIVERSE) {
                                    ArrayList<Faculty.FACULTY_TYPE> facultyArrayList = campInformation.getFaculties();
                                    facultyArrayList.clear();
                                    facultyArrayList.add(Faculty.FACULTY_TYPE.UNIVERSE);
                                }
                                if (!campInformation.getFaculties()
                                        .contains(Faculty.getFacultyFromChoice(addFacultyChoice))) {
                                    campInformation.getFaculties().add(Faculty.getFacultyFromChoice(addFacultyChoice));
                                } else {
                                    System.err.println("Faculty already added.");
                                }
                                break;

                            case 2:
                                System.out.println("Choose the faculties to remove:");
                                System.out.println(
                                        "1.SCSE, 2.EEE, 3.SSS, 4.NBS, 5.CCEB, 6.CEE, 7.MSE, 8.MAE, 9.ADM, 10.SOH, 11.WKWSCI, 12.SBS, 13.SPMS, 14.ASE, 15.LKC, 16.NIE, 17.All Faculties, 18.Exit");
                                int removeFacultyChoice = MainProgram.sc.nextInt();
                                MainProgram.sc.nextLine();
                                Faculty.FACULTY_TYPE targetFaculty = Faculty.getFacultyFromChoice(removeFacultyChoice);
                                if (campInformation.getFaculties().contains(targetFaculty)) {
                                    System.out.println("Removing " + targetFaculty);
                                    campInformation.getFaculties().remove(targetFaculty);
                                } else {
                                    System.err.println("Faculty not found in the list.");
                                }
                                break;

                            case 3:
                                return;

                            default:
                                System.err.println("Invalid choice.");
                                break;
                        }
                    } while (facultyEditChoice < 3 && facultyEditChoice > 0);
                    break;

                case 7:
                    System.out.print("Change visibility (1 for visible, 2 for not visible): ");
                    int visibilityChoice = MainProgram.sc.nextInt();
                    editCamp.setVisibility(visibilityChoice == 1);
                    break;
                case 8:
                    System.out.print("Enter new description: ");
                    String newDescription = MainProgram.sc.nextLine();
                    campInformation.setDescription(newDescription);
                    break;
                case 9:
                    return;
                default:
                    System.err.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }

    }

    public static void deleteCamp(AllUser allUser, AllCamp allCamp, Staff staff) {

        Camp deleteCamp = selectCamp(staff.getCampsCreated());
        if (deleteCamp == null) {
            return;
        }
        CampInformation campInformation = deleteCamp.getCampInformation();
        String campName;
        if (campInformation == null) {
            campName = "";
        } else {
            campName = " " + campInformation.getName();
        }
        System.out.printf("Are you sure you want to delete%s camp? Y/N\tChoice: ", campName);
        String choice = MainProgram.sc.nextLine();
        if (choice.toLowerCase().equals("y")) {
            allCamp.deleteCamp(deleteCamp);
        } else {
            System.out.println("Camp deletion failed. Returning to main menu...");
        }
    }

    public static void joinCamp(AllUser allUser, AllCamp allCamp, Student student) {

        ArrayList<Camp> availCampArrayList = FilterCamp.getSignUpCamp(allCamp, student);
        Camp selectedCamp = selectCamp(availCampArrayList);
        if (selectedCamp == null) {
            return;
        }

        Date currentDate = SystemSimulatedDateManager.getSystemSimulatedDate();
        Date registrationDeadLine = selectedCamp.getCampInformation().getRegistrationDeadline();
        if (currentDate.after(registrationDeadLine)) {
            System.out.println("Sorry, the registration deadline for this camp has passed.");
            return;
        }

        boolean attendee = true;
        if (student.getCommittee() == null) {
            System.out.print("Do you want to join as a committee member or attendee? C/A\nChoice: ");
            if (MainProgram.sc.nextLine().toLowerCase().equals("c")) {
                attendee = false;
            }
        }
        student.registerCamp(selectedCamp, attendee);
    }

    public static void viewCampMembers(AllUser allUser, AllCamp allCamp) {
        Camp targetCamp = selectCamp(allCamp.getCamps());
        if (targetCamp == null) {
            return;
        }
        System.out.println(targetCamp.getCampStudents());
    }

    public static void viewJoinedCamps(Student student) {
        if (student == null) {
            return;
        }
        if (student.getCommittee() != null) {
            Camp targetCamp = student.getCommittee().getCamp();
            if (targetCamp == null) {
                return;
            }
            CampInformation targetCampInformation = targetCamp.getCampInformation();
            if (targetCampInformation == null) {
                return;
            }
            System.out.printf("Committee member of: %s\n", targetCampInformation.getName());
        }
        if (student.getAttendee() != null) {
            ArrayList<Camp> campArrayList = student.getAttendee().getCamps();
            if (campArrayList == null) {
                return;
            }
            int i = 1;
            i = printCamps(campArrayList, i);
        }
    }

    public static void viewAvailableCamps(AllUser allUser, AllCamp allCamp, Student student) {

        ArrayList<Camp> availCampArrayList = FilterCamp.getAvailableCamps(allCamp, student);
        if (availCampArrayList.isEmpty()) {
            System.out.println("No camp available to view");
            return;
        }
        // change order of availCampArrayList by different filter options & print it

        Camp selectedCamp = selectCamp(availCampArrayList);
        if (selectedCamp == null)
            return;
        System.out.println(selectedCamp.getBasicCampDetail());
        return;
    }

}