package huji.postpc.y2021.ex_7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    String SP_id;

    public void chooseDestActivity(String order_status)
    {
        switch (order_status) {
            case "preparing": {
                Intent intent = new Intent(this, OrderPreparingActivity.class);
                startActivity(intent);
                finish();

                break;
            }
            case "edit": {
                Intent intent = new Intent(this, OrderEditActivity.class);
                startActivity(intent);
                finish();


                break;
            }
            case "ready": {
                Intent intent = new Intent(this, OrderReadyActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            default: {
                Intent intent = new Intent(new Intent(this, OrderPrepareNewActivity.class));
                this.startActivity(intent);
                finish();
                break;
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
        OrderSandwichApplication orderSandwitchApplication = new OrderSandwichApplication(this);
        this.SP_id = orderSandwitchApplication.getSPid();

        String null_order = "";
        if(this.SP_id.equals(null_order))
        {
            Intent intent = new Intent(new Intent(this, OrderPrepareNewActivity.class));
            this.startActivity(intent);
            finish();
        }
        else
        {
            Task<DocumentSnapshot> orders = fireStore.collection("orders").document(this.SP_id).get();
            orders.addOnSuccessListener(result->{
                String order_status = result.getString("order_status");
                if(order_status == null)
                {
                    Intent intent = new Intent(new Intent(this, OrderPrepareNewActivity.class));
                    this.startActivity(intent);
                    finish();
                }
                else
                {
                    chooseDestActivity(order_status);
                }
            });
        }
    }
}