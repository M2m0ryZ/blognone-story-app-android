package com.benznestdeveloper.blognonestory.service.blognone.web;

import com.benznestdeveloper.blognonestory.dao.BlognoneNodeDao;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by benznest on 29-Sep-17.
 */

public class MyBlognoneManager {

    public static String convertToEndPoint(String tag) {
        return tag.trim().toLowerCase().replace(" ", "-");
    }

    public static void loadNodeList(int page, final OnLoadNodeListListener listener) {
        Call call = MyBlognoneService.getService(MyBlognoneService.CONVERTER_SCALAR).getNodeList(page);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String html = (String) response.body();
                ArrayList<BlognoneNodeDao> list = MyBlognoneScraping.getNodeList(html);
                if (list != null) {
                    if (listener != null) {
                        listener.onLoaded(list);
                    }
                    return;
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

    public static void loadNodeInterview(int page, final OnLoadNodeListListener listener) {
        Call call = MyBlognoneService.getService(MyBlognoneService.CONVERTER_SCALAR).getNodeInterview(page);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String html = (String) response.body();
                ArrayList<BlognoneNodeDao> list = MyBlognoneScraping.getNodeList(html);
                if (list != null) {
                    if (listener != null) {
                        listener.onLoaded(list);
                    }
                    return;
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

    public static void loadNodeUpcoming(final OnLoadNodeListListener listener) {
        Call call = MyBlognoneService.getService(MyBlognoneService.CONVERTER_SCALAR).getNodeUpcoming();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String html = (String) response.body();

                ArrayList<BlognoneNodeDao> list = MyBlognoneScraping.getNodeList(html);

                if (list != null) {
                    if (listener != null) {
                        listener.onLoaded(list);
                    }
                    return;
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

    public static void loadNodeTag(String endpointTag, int page, final OnLoadNodeListListener listener) {
        Call call = MyBlognoneService.getService(MyBlognoneService.CONVERTER_SCALAR).getNodeTag(endpointTag, page);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String html = (String) response.body();
                if (html != null) {
                    ArrayList<BlognoneNodeDao> list = MyBlognoneScraping.getNodeList(html);
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

    public static void loadNodeForum(String endpoint, int page, final OnLoadNodeListListener listener) {
        Call call = MyBlognoneService.getService(MyBlognoneService.CONVERTER_SCALAR).getNodeForum(endpoint, page);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String html = (String) response.body();
                ArrayList<BlognoneNodeDao> list = MyBlognoneScraping.getNodeForumList(html);
                if (list != null) {
                    if (listener != null) {
                        listener.onLoaded(list);
                    }
                    return;
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

    public static void loadNodeWorkplace(final OnLoadNodeListListener listener) {
        Call call = MyBlognoneService.getService(MyBlognoneService.CONVERTER_SCALAR).getNodeWorkplace();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String html = (String) response.body();
                ArrayList<BlognoneNodeDao> list = MyBlognoneScraping.getNodeList(html);
                if (list != null) {
                    if (listener != null) {
                        listener.onLoaded(list);
                    }
                    return;
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

    public static void loadNodeFeature(final OnLoadNodeListListener listener) {
        Call call = MyBlognoneService.getService(MyBlognoneService.CONVERTER_SCALAR).getNodeFeature();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String html = (String) response.body();
                ArrayList<BlognoneNodeDao> list = MyBlognoneScraping.getNodeList(html);
                if (list != null) {
                    if (listener != null) {
                        listener.onLoaded(list);
                    }
                    return;
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

    public static void loadNodeContent(int id, final OnLoadNodeContentListener listener) {
        Call call = MyBlognoneService.getService(MyBlognoneService.CONVERTER_SCALAR).getNodeContent(id);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String html = (String) response.body();
                if (html != null) {
                    BlognoneNodeDao node = MyBlognoneScraping.getNodeContent(html);
                    if (node != null) {
                        if (listener != null) {
                            listener.onLoaded(node);
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

    public interface OnLoadNodeListListener {
        void onLoaded(ArrayList<BlognoneNodeDao> listNode);

        void onFail();
    }

    public interface OnLoadNodeContentListener {
        void onLoaded(BlognoneNodeDao n);

        void onFail();
    }
}
