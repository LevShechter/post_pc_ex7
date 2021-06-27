package huji.postpc.y2021.ex_7;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class OrderSandwichApplication extends Application {
    public String SP_id;
    SharedPreferences sp;
//    public String SP_id;
    public OrderSandwichApplication(Context context) {
        this.SP_id = "";
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        SP_id = sp.getString("text", "");
    }

//    public String getSP() {
//        return this.SP_id;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

//    public String getSPid() {
//        return this.SP_id;
//    }
    public void save_order(String id){
        this.SP_id = id;
        sp.edit().putString("text", this.SP_id).apply();
    }

    public void data()
    {
        String null_string = "";
        this.SP_id = sp.getString("text", null_string);
    }

    public String getSPid() {
        return this.SP_id;
    }
}
