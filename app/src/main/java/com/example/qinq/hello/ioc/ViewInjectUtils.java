package com.example.qinq.hello.ioc;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
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

    public static ViewInjectUtils instance;

    public static ViewInjectUtils instance() {
        if (instance == null)
            instance = new ViewInjectUtils();
        return instance;
    }

    /**
     * @param activity
     */
    public void inject(Activity activity) {
        injectContentView(activity);
        injectViews(activity);
    }


    /**
     * @param activity
     */
    private void injectContentView(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();

        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) {
            int contentViewLayoutId = contentView.value();
            try {
                Method method = clazz.getMethod(METHOD_SET_CONTENTVIEW, int.class);
                method.setAccessible(true);
                method.invoke(activity, contentViewLayoutId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param activity
     */
    private void injectViews(Activity activity) {
        final Class<? extends Activity> clazz = activity.getClass();
        Field[] fields = clazz.getClass().getDeclaredFields();

        for (Field field : fields) {

            ViewInject viewInjectAnnotation = field.getAnnotation(ViewInject.class);
            if (viewInjectAnnotation != null) {
                int ViewId = viewInjectAnnotation.value();
                if (ViewId != ViewInject.DEFAULT_ID) {
                    try {
                        Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID, int.class);
                        Object resView = method.invoke(clazz, ViewId);
                        field.setAccessible(true);
                        field.set(clazz, resView);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }

        }

    }


}



