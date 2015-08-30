package com.example.qinq.hello.ioc;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.example.qinq.hello.ioc.view.ClickMethod;
import com.example.qinq.hello.ioc.view.ContentView;
import com.example.qinq.hello.ioc.view.ViewInject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Created by qinqiang on 2015/8/24.
 */
public class ViewInjectUtils {

    private static final String METHOD_SET_CONTENTVIEW = "setContentView";

    private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";


    public static void inject(Object object) {
        if (object instanceof Activity) {
            injectContentView((Activity) object);
        }
        injectViews(object);
        injectClick(object);

    }

    /**
     * 注入主布局文件
     *
     * @param activity
     */
    private static void injectContentView(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();

        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) {
            int contentViewLayoutId = contentView.value();
            try {

                Method method = clazz.getMethod(METHOD_SET_CONTENTVIEW,
                        int.class);
                method.setAccessible(true);
                method.invoke(activity, contentViewLayoutId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 注入所有的控件
     *
     * @param object
     */
    private static void injectViews(Object object) {
        final Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {

            ViewInject viewInjectAnnotation = field.getAnnotation(ViewInject.class);
            if (viewInjectAnnotation != null) {
                int ViewId = viewInjectAnnotation.value();
                if (ViewId != ViewInject.DEFAULT_ID) {
                    try {
                        Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID, int.class);
                        Object resView = method.invoke(object, ViewId);
                        field.setAccessible(true);
                        field.set(object, resView);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }

        }

    }

    /**
     * 注入click事件
     *
     * @param object
     */
    private static void injectClick(final Object object) {
        Class clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        for (Method temp : methods) {
            final Method method = temp;
            ClickMethod clickMethodAnnotation = method.getAnnotation(ClickMethod.class);
            if (clickMethodAnnotation != null) {
                int[] ViewIds = clickMethodAnnotation.id();
                for (int i = 0; i < ViewIds.length; i++) {
                    View view;
                    if (object instanceof Activity) {
                        view = ((Activity) object).findViewById(ViewIds[i]);
                    } else {
                        view = ((View) object).findViewById(ViewIds[i]);
                    }
                    view.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            try {
                                method.invoke(object, v);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }

            }

        }

    }


}



