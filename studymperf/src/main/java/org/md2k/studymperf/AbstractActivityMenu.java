package org.md2k.studymperf;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import org.md2k.mcerebrum.commons.app_info.AppInfo;
import org.md2k.studymperf.menu.MyMenu;
import org.md2k.studymperf.ui.main.FragmentContactUs;
import org.md2k.studymperf.ui.main.FragmentHelp;
import org.md2k.studymperf.ui.main.FragmentHome;
import org.md2k.studymperf.ui.main.FragmentWorkAnnotation;

public abstract class AbstractActivityMenu extends AbstractActivityBasics {
    private Drawer result = null;
    int selectedMenu = MyMenu.MENU_HOME;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void updateUI() {
        createDrawer();
        result.resetDrawerContent();
        result.getHeader().refreshDrawableState();
        result.setSelection(MyMenu.MENU_HOME);
    }

    void createDrawer() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.cover_image)
                .withCompactStyle(true)
                .addProfiles(new MyMenu().getHeaderContent(userInfo.getUserName(), responseCallBack))
                .build();
        boolean start =AppInfo.isServiceRunning(this, ServiceStudy.class.getName());

            result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(new MyMenu().getMenuContent(start, responseCallBack))
                .build();
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            if (selectedMenu != MyMenu.MENU_HOME) {
                responseCallBack.onResponse(null, MyMenu.MENU_HOME);
            } else {
                stopAll();
                super.onBackPressed();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
/*
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
*/
//        super.onSaveInstanceState(outState);
    }

    public ResponseCallBack responseCallBack = new ResponseCallBack() {
        @Override
        public void onResponse(IDrawerItem drawerItem, int responseId) {
            selectedMenu = responseId;
            if (drawerItem != null)
                toolbar.setTitle(getStudyName()+": " + ((Nameable) drawerItem).getName().getText(AbstractActivityMenu.this));
            else toolbar.setTitle(getStudyName());
            switch (responseId) {
                case MyMenu.MENU_HOME:
                    result.setSelection(MyMenu.MENU_HOME, false);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commitAllowingStateLoss();
                    break;
                case MyMenu.MENU_START_STOP:
                    boolean start =AppInfo.isServiceRunning(AbstractActivityMenu.this, ServiceStudy.class.getName());

                    if(start) {
                        stopDataCollection();
                    }
                    else {
                        startDataCollection();
                    }
                    toolbar.setTitle(getStudyName());
                    break;
                case MyMenu.MENU_RESET:
                    boolean startt =AppInfo.isServiceRunning(AbstractActivityMenu.this, ServiceStudy.class.getName());

                    if(startt) {
                        resetDataCollection();
                    }
                    else {
                        startDataCollection();
                    }
                    toolbar.setTitle(getStudyName());
                    break;

                case MyMenu.MENU_SETTINGS:
                    stopAndQuit();
                    break;
                case MyMenu.MENU_CONTACT_US:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentContactUs()).commit();
                    break;
                case MyMenu.MENU_WORK_ANNOTATION:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentWorkAnnotation()).commitAllowingStateLoss();
                    break;
                case MyMenu.MENU_HELP:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHelp()).commitAllowingStateLoss();
                    break;


/*
                case AbstractMenu.MENU_APP_ADD_REMOVE:
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new FragmentFoldingUIAppInstall()).commitAllowingStateLoss();
                    break;
                case AbstractMenu.MENU_APP_SETTINGS:
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new FragmentFoldingUIAppSettings()).commitAllowingStateLoss();
                    break;
                case AbstractMenu.MENU_JOIN:
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new FragmentJoinStudy()).commitAllowingStateLoss();
                    break;
                case AbstractMenu.MENU_LEAVE:
                    materialDialog= Dialog.simple(AbstractActivityMenu.this, "Leave Study", "Do you want to leave the study?", "Yes", "Cancel", new DialogCallback() {
                        @Override
                        public void onSelected(String value) {
                            if(value.equals("Yes")){
                                configManager.clear();
                                prepareConfig();
                            }
                        }
                    }).show();
                    break;
                case AbstractMenu.MENU_LOGIN:
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new FragmentLogin()).commitAllowingStateLoss();
//                Intent i = new Intent(this, ActivityLogin.class);
//                startActivityForResult(i, ID_JOIN_STUDY);
                    break;
                case AbstractMenu.MENU_LOGOUT:
//                ((UserServer) user).setLoggedIn(this,false);
                    userInfo.setLoggedIn(false);
                    Toasty.success(AbstractActivityMenu.this, "Logged out", Toast.LENGTH_SHORT, true).show();
                    updateUI();
                    break;
*/
                default:
            }
        }
    };
}

