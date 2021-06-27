package huji.postpc.y2021.ex_7;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firestore.v1.StructuredQuery;

import javax.annotation.Nullable;

public class OrderPreparingActivity extends AppCompatActivity{

        ListenerRegistration dataBase;
        OrderSandwichApplication orderSandwichApplication;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_prepare_order);
            FirebaseFirestore firestore = FirebaseFirestore.getInstance();
            this.orderSandwichApplication = new OrderSandwichApplication(this);
            DocumentReference documentReference = firestore.collection("orders").document(this.orderSandwichApplication.getSPid());
            dataBase = documentReference.addSnapshotListener((v_1,v_2)->
            {
                boolean bool = (v_2 ==null && v_1!= null);
                if (bool)
                {
                    String order_status = v_1.getString("order_status");
                    if(order_status != null)
                    {
                        boolean ready_status = order_status.equals("ready");
                        if(ready_status)
                        {
                            Intent intent_ready = new Intent(this, OrderReadyActivity.class);
                            this.startActivity(intent_ready);
                            finish();
                        }
                    }
                }
            });

        }
        @Override
        protected void onDestroy() {
            super.onDestroy();
            dataBase.remove();
        }
}

