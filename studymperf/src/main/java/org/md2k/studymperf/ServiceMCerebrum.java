package org.md2k.studymperf;

import android.content.Intent;
import android.os.Bundle;

import org.md2k.mcerebrum.core.access.AbstractServiceMCerebrum;

public class ServiceMCerebrum extends AbstractServiceMCerebrum {
    public ServiceMCerebrum() {
    }

    @Override
    protected boolean hasClear() {
        return false;
    }

    @Override
    public void initialize(Bundle bundle) {
/*
        Permission.requestPermission(this, new PermissionCallback() {
            @Override
            public void OnResponse(boolean isGranted) {
                if (isGranted) {
                }
            }
        });
*/
    }

    @Override
    public void launch(Bundle bundle) {
        Intent intent=new Intent(this, ActivityMain.class);
//        this.startActivity(intent);
/*
        bundle.setClassLoader(Info.class.getClassLoader());
        Info info = bundle.getParcelable("info");
        Log.d("abc","length="+info.getAppInfo().length);
        intent.putExtras(bundle);
*/

/*
        bundle.setClassLoader(StudyInfo.class.getClassLoader());
        StudyInfo s = bundle.getParcelable("study_info");
        bundle.setClassLoader(UserInfo.class.getClassLoader());
        UserInfo u = bundle.getParcelable("user_info");
        bundle.setClassLoader(AppInfo.class.getClassLoader());
        Parcelable[] a = bundle.getParcelableArray("app_info");
*/

/*
        Bundle b = new Bundle();
        b.putParcelable("study_info",s);
        b.putParcelable("user_info",u);
        b.putParcelableArray("app_info",a);
        intent.putExtras(b);
*/
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void startBackground(Bundle bundle) {

        Intent intent =new Intent(this, ServiceStudy.class);
        startService(intent);
    }

    @Override
    public void stopBackground(Bundle bundle) {
        Intent intent =new Intent(this, ServiceStudy.class);
        stopService(intent);
    }

    @Override
    public void report(Bundle bundle) {
    }

    @Override
    public void clear(Bundle bundle) {

    }

    @Override
    public boolean hasReport() {
        return false;
    }

    @Override
    public boolean isRunInBackground() {
        return true;
    }

    @Override
    public long getRunningTime() {
        return -1;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isConfigured() {
        return true;
    }

    @Override
    public boolean isConfigurable() {
        return false;
    }

    @Override
    public boolean hasInitialize() {
        return false;
    }

    @Override
    public void configure(Bundle bundle) {
    }

    @Override
    public boolean isEqualDefault() {
        return false;
    }

}
