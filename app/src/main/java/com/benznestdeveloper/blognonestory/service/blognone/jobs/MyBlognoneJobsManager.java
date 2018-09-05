package com.benznestdeveloper.blognonestory.service.blognone.jobs;

import com.benznestdeveloper.blognonestory.dao.jobs.JobDataDao;
import com.benznestdeveloper.blognonestory.dao.jobs.JobsDao;
import com.benznestdeveloper.blognonestory.dao.jobs.MessageBlognoneJobsDao;
import com.benznestdeveloper.blognonestory.dao.jobs.MessageJobDetailDao;
import com.benznestdeveloper.blognonestory.search.job.JobsFilterDao;
import com.benznestdeveloper.blognonestory.search.job.SearchJobsDao;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by benznest on 28-Aug-18.
 */

public class MyBlognoneJobsManager {

    public static String convertToUrlArray(ArrayList<JobsFilterDao> arr) {
        String str = "";
        if (arr != null && !arr.isEmpty()) {
            for (int i = 0; i < arr.size(); i++) {
                str = str + arr.get(i).getValue();
                if (i < arr.size() - 1) {
                    str = str + ",";
                }
            }
        }
        return str;
    }

    public static void loadJobsList(int page, SearchJobsDao sj, final OnLoadJobsListListener listener) {
        loadJobsList(page, sj.getKeyword(), sj.getJobType(), sj.getJobLevel(), sj.getJobFunction(), listener);
    }

    public static void loadJobsList(int page, String keyword, final OnLoadJobsListListener listener) {
        loadJobsList(page, keyword, null, null, null, listener);
    }

    public static void loadJobsList(int page,
                                    String keyword,
                                    ArrayList<JobsFilterDao> type,
                                    ArrayList<JobsFilterDao> level,
                                    ArrayList<JobsFilterDao> jobFunction,
                                    final OnLoadJobsListListener listener) {

        String arrType = convertToUrlArray(type);
        String arrLevel = convertToUrlArray(level);
        String arrJobFunction = convertToUrlArray(jobFunction);

        Call call = MyBlognoneJobsService.getService().getJobsList(page, keyword, arrType, arrLevel, arrJobFunction);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                MessageBlognoneJobsDao msg = (MessageBlognoneJobsDao) response.body();
                if (msg != null) {
                    ArrayList<JobsDao> list = msg.getJobs();
                    if (list != null) {
                        if (listener != null) {
                            listener.onLoaded(list);
                        }
                        return;
                    }
                }

                if (listener != null) {
                    listener.onFail();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
                if (listener != null) {
                    listener.onFail();
                }
            }
        });
    }

    public static void loadJobBody(JobsDao job,
                                   final OnLoadJobsDataListListener listener) {
        loadJobBody(job.getCompany().getSlug(), job.getSlug(), listener);
    }

    public static void loadJobBody(String companySlug, String jobSlug,
                                   final OnLoadJobsDataListListener listener) {
        Call call = MyBlognoneJobsService.getService(
                MyBlognoneJobsService.CONVERTER_SCALAR,
                MyBlognoneJobsService.MODE_BASE_URL_WEB).getJobBody(companySlug, jobSlug);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String html = (String) response.body();
//                MyUtils.log("HTML = "+html);
                if (html != null) {
                    MessageJobDetailDao msg = MyBlognoneJobScraping.getJobBody(html);
                    try {
                        if (msg != null) {
                            if (listener != null) {
                                listener.onLoaded(msg.getR().getData());
                            }
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (listener != null) {
                    listener.onFail();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
                if (listener != null) {
                    listener.onFail();
                }
            }
        });
    }

    public interface OnLoadJobsListListener {
        void onLoaded(ArrayList<JobsDao> listNode);

        void onFail();
    }

    public interface OnLoadJobsDataListListener {
        void onLoaded(JobDataDao jobData);

        void onFail();
    }
}
