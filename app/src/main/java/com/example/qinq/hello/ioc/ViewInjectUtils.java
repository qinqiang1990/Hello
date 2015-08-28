package com.example.qinq.hello.ioc;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.example.qinq.hello.ioc.view.ContentView;
import com.example.qinq.hello.ioc.view.ViewInject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * Created by qinqiang on 2015/8/24.
 */
public class ViewInjectUtils {

    private static final String METHOD_SET_CONTENTVIEW = "setContentView";

    private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";


    public static void inject(Activity activity) {

        injectContentView(activity);
        injectViews(activity);

    }

    /**
     * ע���������ļ�
     *
     * @param activity
     */
    private static void injectContentView(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        // ��ѯ�����Ƿ����ContentViewע��
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null)// ����
        {
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
     * ע�����еĿؼ�
     *
     * @param activity
     */
    private static void injectViews(final Activity activity) {
        final Class<? extends Activity> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        // �������г�Ա����
        for (Field field : fields) {

            ViewInject viewInjectAnnotation = field
                    .getAnnotation(ViewInject.class);
            if (viewInjectAnnotation != null) {
                int viewId = viewInjectAnnotation.value();
                if (viewId != ViewInject.DEFAULT_ID) {
                    // ��ʼ��View
                    try {
                        Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID,int.class);
                        Object resView = method.invoke(activity, viewId);
                        field.setAccessible(true);
                        field.set(activity, resView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
        /*        String methodname = viewInjectAnnotation.click();
                if (!methodname.equals(ViewInject.DEFAULT_METHOD)) {
                    try {
                        final Object obj = field.get(activity);
                        final Method method = clazz.getMethod(methodname, View.class);
                        if (obj instanceof View) {
                            ((View) obj).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {
                                        method.invoke(activity, v);
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    } catch (InvocationTargetException e) {
                                        e.printStackTrace();
                                    }
                                    Toast.makeText(v.getContext(),"Hello",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
*/
            }

        }

    }
}

