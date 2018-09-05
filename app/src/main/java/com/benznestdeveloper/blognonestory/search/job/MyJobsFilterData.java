package com.benznestdeveloper.blognonestory.search.job;

import java.util.ArrayList;

/**
 * Created by benznest on 29-Aug-18.
 */

public class MyJobsFilterData {

    private static ArrayList<JobsFilterDao> listFilterJobType;
    private static ArrayList<JobsFilterDao> listFilterJobLevel;
    private static ArrayList<JobsFilterDao> listFilterJobFunction;

    public static void init() {
        listFilterJobType = new ArrayList<>();
        listFilterJobLevel = new ArrayList<>();
        listFilterJobFunction = new ArrayList<>();

        listFilterJobType.add(new JobsFilterDao("Full time", "FULL_TIME"));
        listFilterJobType.add(new JobsFilterDao("Part time", "PART_TIME"));
        listFilterJobType.add(new JobsFilterDao("Contract", "CONTRACT"));
        listFilterJobType.add(new JobsFilterDao("Internship", "INTERNSHIP"));
        listFilterJobType.add(new JobsFilterDao("Freelance", "FREELANCE"));

        // level
        listFilterJobLevel.add(new JobsFilterDao("Entry", "ENTRY"));
        listFilterJobLevel.add(new JobsFilterDao("Middle", "MIDDLE"));
        listFilterJobLevel.add(new JobsFilterDao("Senior", "SENIOR"));

        // job func
        listFilterJobFunction.add(new JobsFilterDao("Software Developer", "DEVELOPER"));;
        listFilterJobFunction.add(new JobsFilterDao("System Administrator", "SYSADMIN"));
        listFilterJobFunction.add(new JobsFilterDao("Data & Analytics", "DATA"));
        listFilterJobFunction.add(new JobsFilterDao("Content creator", "CONTENT"));
        listFilterJobFunction.add(new JobsFilterDao("UI/UX Designer", "UX"));
        listFilterJobFunction.add(new JobsFilterDao("Graphic Designer", "DESIGNER"));
        listFilterJobFunction.add(new JobsFilterDao("QA & Tester", "QA"));
        listFilterJobFunction.add(new JobsFilterDao("Sales & Marketing", "SALES"));
        listFilterJobFunction.add(new JobsFilterDao("Business Development", "BIZDEV"));
        listFilterJobFunction.add(new JobsFilterDao("Project & Product Manager", "PM"));
        listFilterJobFunction.add(new JobsFilterDao("Others", "OTHER"));
    }

    public static ArrayList<JobsFilterDao> getListFilterJobType() {
        return listFilterJobType;
    }

    public static ArrayList<JobsFilterDao> getListFilterJobLevel() {
        return listFilterJobLevel;
    }

    public static ArrayList<JobsFilterDao> getListFilterJobFunction() {
        return listFilterJobFunction;
    }
}
