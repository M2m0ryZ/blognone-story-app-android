package com.panuwatjan.blognonestory.service.blognone.web;

import com.panuwatjan.blognonestory.dao.BlognoneForumDao;

/**
 * Created by benznest on 18-Mar-18.
 */

public class MyBlognoneForum {

    private static BlognoneForumDao forumJob;
    private static BlognoneForumDao forumBlognoneTh;
    private static BlognoneForumDao forumBlognoneEng;
    private static BlognoneForumDao forumPython;
    private static BlognoneForumDao forumRuby;
    private static BlognoneForumDao forumProblemProgramming;
    private static BlognoneForumDao forumProject;

    public static final String ENDPOINT_JOB = "blognone-jobs";
    public static final String ENDPOINT_BLOGNONE_TH = "blognone-forum";
    public static final String ENDPOINT_BLOGNONE_ENG = "discussion-forum/english-forum";
    public static final String ENDPOINT_PYTHON = "codenone/python";
    public static final String ENDPOINT_RUBY = "codenone/ruby";
    public static final String ENDPOINT_PROBLEM_PROGRAMMING = "codenone/programming-problems";
    public static final String ENDPOINT_PROJECT = "codenone/projects";

    public static void init() {
        forumJob = new BlognoneForumDao("Job", ENDPOINT_JOB);
        forumBlognoneTh = new BlognoneForumDao("Forum TH", ENDPOINT_BLOGNONE_TH);
        forumBlognoneEng = new BlognoneForumDao("Forum Eng", ENDPOINT_BLOGNONE_ENG);
        forumPython = new BlognoneForumDao("Python", ENDPOINT_PYTHON);
        forumRuby = new BlognoneForumDao("Ruby", ENDPOINT_RUBY);
        forumProblemProgramming = new BlognoneForumDao("Programming Problems", ENDPOINT_PROBLEM_PROGRAMMING);
        forumProject = new BlognoneForumDao("Projects", ENDPOINT_PROJECT);
    }

    public static BlognoneForumDao getForumJob() {
        return forumJob;
    }

    public static BlognoneForumDao getForumBlognoneTh() {
        return forumBlognoneTh;
    }

    public static BlognoneForumDao getForumBlognoneEng() {
        return forumBlognoneEng;
    }

    public static BlognoneForumDao getForumPython() {
        return forumPython;
    }

    public static BlognoneForumDao getForumRuby() {
        return forumRuby;
    }

    public static BlognoneForumDao getForumProblemProgramming() {
        return forumProblemProgramming;
    }

    public static BlognoneForumDao getForumProject() {
        return forumProject;
    }
}
