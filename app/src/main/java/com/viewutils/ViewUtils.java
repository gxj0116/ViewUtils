package com.viewutils;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by gxj on 2019/2/19.
 */

public class ViewUtils {
    public static void inject(Activity activity) {
        bindView(activity);
        bindOnClick(activity);
    }

    public static void inject(Fragment fragment) {
        bindView(fragment);
        bindOnClick(fragment);
    }

    /**
     * 绑定activity中添加注解的View
     *
     * @param activity
     */
    private static void bindView(Activity activity) {
        if (activity == null) return;

        Class<? extends Activity> clazz = activity.getClass();
        if (clazz != null) {
            Field[] declaredFields = clazz.getDeclaredFields();

            for (Field field : declaredFields) {
                ViewInject viewInject = field.getAnnotation(ViewInject.class);
                if (viewInject != null) {
                    int resId = viewInject.value();
                    if (resId != -1) {
                        View view = activity.findViewById(resId);
                        field.setAccessible(true);
                        try {
                            field.set(activity, view);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * 绑定activity中添加注解的View并绑定点击事件
     *
     * @param activity
     */
    private static void bindOnClick(final Activity activity) {
        if (activity == null) return;

        Class<? extends Activity> clazz = activity.getClass();
        if (clazz != null) {
            Method[] declaredMethods = clazz.getDeclaredMethods();
            for (final Method method : declaredMethods) {
                if (method != null) {
                    method.setAccessible(true);
                    OnClick onClick = method.getAnnotation(OnClick.class);
                    if (onClick != null) {
                        int resId = onClick.value();
                        View view = activity.findViewById(resId);
                        if (view != null) {
                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    try {
                                        method.invoke(activity, view);
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
    }

    /**
     * 绑定fragment中添加注解的View
     *
     * @param fragment
     */
    private static void bindView(Fragment fragment) {
        if (fragment == null) return;

        Class<? extends Fragment> clazz = fragment.getClass();

        if (clazz != null) {
            Field[] declaredFields = clazz.getDeclaredFields();

            for (Field field : declaredFields) {
                ViewInject viewInject = field.getAnnotation(ViewInject.class);
                if (viewInject != null) {
                    int resId = viewInject.value();
                    if (resId != -1) {
                        View rootView = fragment.getView();
                        if (rootView != null) {
                            View view = rootView.findViewById(resId);
                            field.setAccessible(true);
                            try {
                                field.set(fragment, view);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 绑定activity中添加注解的View并绑定点击事件
     *
     * @param fragment
     */
    private static void bindOnClick(final Fragment fragment) {
        if (fragment == null) return;

        Class<? extends Fragment> clazz = fragment.getClass();

        if (clazz != null) {
            Method[] declaredMethods = clazz.getDeclaredMethods();

            for (final Method method : declaredMethods) {

                if (method != null) {
                    method.setAccessible(true);
                    OnClick onClick = method.getAnnotation(OnClick.class);
                    if (onClick != null) {
                        int resId = onClick.value();
                        View rootView = fragment.getView();
                        if (rootView != null) {
                            View view = rootView.findViewById(resId);
                            if (view != null) {
                                view.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        try {
                                            method.invoke(fragment, view);
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
        }
    }
}
