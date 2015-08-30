package com.example.qinq.hello.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qinq.hello.R;

/**
 * Created by qinqiang on 2015/8/27.
 */
public class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_index, container, false);
        Controller selector = (Controller)clazz.getAnnotation(Controller.class);
        try
        {
            if (selector != null) {
                layoutId = selector.layoutId();
            } else {
                layoutId = getResources().getIdentifier(
                        clazz.getSimpleName().toLowerCase().replace("fragment", ""),
                        "layout", getActivity().getPackageName());
            }
        }
        catch (Exception e)
        {
            ZWLogger.printLog(this, "Fragment????:" + clazz.getSimpleName() +
                    "????????????!");
            e.printStackTrace();
        }
        if (layoutId == 0)
        {
            ZWLogger.printLog(this, "Fragment????:" + clazz.getSimpleName() +
                    "????????????!");
            throw new InstantiationError("????Fragment??????????");
        }
        this.rootView = inflater.inflate(layoutId, container, false);
        Controller selector = (Controller)clazz.getAnnotation(Controller.class);
        try
        {
            if (selector != null) {
                layoutId = selector.layoutId();
            } else {
                layoutId = getResources().getIdentifier(
                        clazz.getSimpleName().toLowerCase().replace("fragment", ""),
                        "layout", getActivity().getPackageName());
            }
        }
        catch (Exception e)
        {
            ZWLogger.printLog(this, "Fragment????:" + clazz.getSimpleName() +
                    "????????????!");
            e.printStackTrace();
        }
        if (layoutId == 0)
        {
            ZWLogger.printLog(this, "Fragment????:" + clazz.getSimpleName() +
                    "????????????!");
            throw new InstantiationError("????Fragment??????????");
        }
        this.rootView = inflater.inflate(layoutId, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
