package info.adavis.topsy.turvey;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TopsyTurveyApplication extends Application
{
    public static final int SCHEMA_VERSION = 1;
    @Override
    public void onCreate ()
    {
        super.onCreate();

        Realm.init(this);

        //Stetho initalization allows adb through google chrome devtools
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
        //IF YOU DONT USE MIGRATION USE .deleteRealmIfMigrationNeeded()
        // AND
        //DELETE REALM EVERYTIME THE APPLICATION IS CREATED. ONLY FOR DEVELOPMENT.
//        Realm.deleteRealm(configuration);
        //configure Realm, such as add name, change behaviours etc.
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .schemaVersion(SCHEMA_VERSION)
                .migration(new Migration())
                .name("topsy_turvey.realm")
                .build();

        Realm.setDefaultConfiguration(configuration);
    }
}
