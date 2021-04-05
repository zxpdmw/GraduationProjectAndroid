package top.zxpdmw.graduationproject.ui.fragment;


import top.zxpdmw.graduationproject.ui.BaseView;

public interface BaseFragment extends BaseView {
    void initToolBar(String title);

    void destroyToolBar(String title);
}
