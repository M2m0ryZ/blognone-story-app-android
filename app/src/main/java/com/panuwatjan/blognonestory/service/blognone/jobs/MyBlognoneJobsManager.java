package com.panuwatjan.blognonestory.service.blognone.jobs;

import com.panuwatjan.blognonestory.dao.jobs.CompanyDao;
import com.panuwatjan.blognonestory.dao.jobs.CompanyDataDao;
import com.panuwatjan.blognonestory.dao.jobs.JobDataDao;
import com.panuwatjan.blognonestory.dao.jobs.JobsDao;
import com.panuwatjan.blognonestory.dao.jobs.MessageBlognoneCompaniesDao;
import com.panuwatjan.blognonestory.dao.jobs.MessageBlognoneJobsDao;
import com.panuwatjan.blognonestory.dao.jobs.MessageCompanyDetailDao;
import com.panuwatjan.blognonestory.dao.jobs.MessageJobDetailDao;
import com.panuwatjan.blognonestory.search.job.JobsFilterDao;
import com.panuwatjan.blognonestory.search.job.SearchJobsDao;

import java.util.ArrayList;
import java.util.List;

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

    public static void loadCompaniesList(int page,
                                         final OnLoadCompaniesDataListListener listener) {

        Call call = MyBlognoneJobsService.getService().getCompaniesList(page);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                MessageBlognoneCompaniesDao msg = (MessageBlognoneCompaniesDao) response.body();
                if (msg != null) {
                    ArrayList<CompanyDao> list = msg.getCompanies();
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
                                   final OnLoadJobsDataListener listener) {
        loadJobBody(job.getCompany().getSlug(), job.getSlug(), listener);
    }

    public static void loadJobBody(String companySlug, String jobSlug,
                                   final OnLoadJobsDataListener listener) {
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

    public static void loadCompanyBody(String companySlug,
                                       final OnLoadCompanyDataListener listener) {
        Call call = MyBlognoneJobsService.getService(
                MyBlognoneJobsService.CONVERTER_SCALAR,
                MyBlognoneJobsService.MODE_BASE_URL_WEB).getCompanyBody(companySlug);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String html = (String) response.body();
//                MyUtils.log("HTML = "+html);
                if (html != null) {
                    MessageCompanyDetailDao msg = MyBlognoneJobScraping.getCompanyBody(html);
                    try {
                        if (msg != null) {
                            if (listener != null) {
                                listener.onLoaded(msg.getR().getData().get(0),
                                        msg.getR().getData().get(1).getJobs());
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

    public interface OnLoadJobsDataListener {
        void onLoaded(JobDataDao jobData);

        void onFail();
    }

    public interface OnLoadCompaniesDataListListener {
        void onLoaded(ArrayList<CompanyDao> list);

        void onFail();
    }

    public interface OnLoadCompanyDataListener {
        void onLoaded(CompanyDataDao companyData,List<JobsDao> listJob);

        void onFail();
    }
}
