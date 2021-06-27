package huji.postpc.y2021.ex_7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.annotation.Nullable;

public class OrderReadyActivity extends AppCompatActivity {
    Button end_order_button;
    OrderSandwichApplication orderSandwichApplication;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_is_ready);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        this.end_order_button = findViewById(R.id.end_order_button);
        this.orderSandwichApplication = new OrderSandwichApplication(this);
        this.end_order_button.setOnClickListener(v->
        {
//            String sPid = OrderSandwichApplication.SP_id;
            DocumentReference documentReference = firestore.collection("orders").document(orderSandwichApplication.getSPid());
            documentReference.update("order_status", "done");
            Intent start_main_activity_intent = new Intent(this, MainActivity.class);
            this.startActivity(start_main_activity_intent);
            finish();
        });
    }
}
