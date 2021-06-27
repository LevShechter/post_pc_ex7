package huji.postpc.y2021.ex_7;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.Objects;

public class OrderEditActivity extends AppCompatActivity {
    OrderSandwichApplication orderSandwichApplication;
    SandwichApplicationDataBase sandwichApplicationDataBase;
    ListenerRegistration DBSandwich;
    String order_id;
    EditText ordering_name;
    CheckBox hummus_checkbox;
    CheckBox tahini_checkbox;
    EditText order_specifications;
    SeekBar num_pickles;
    Button save_order_button;
    Button delete_order_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_edit);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        this.ordering_name = findViewById(R.id.ordering_name);
        this.hummus_checkbox = findViewById(R.id.checkbox_hummus);
        this.tahini_checkbox = findViewById(R.id.checkbox_tahini);
        this.order_specifications = findViewById(R.id.specifications);
        this.num_pickles = findViewById(R.id.num_pickles);
        this.save_order_button = findViewById(R.id.save_order_button);
        this.delete_order_button = findViewById(R.id.delete_button);
        this.orderSandwichApplication = new OrderSandwichApplication(this);
        this.orderSandwichApplication.data();
        this.order_id = this.orderSandwichApplication.getSPid();
        CollectionReference orders = firestore.collection("orders");
        DocumentReference documentReference = orders.document(this.order_id);
        Task<DocumentSnapshot> documentSnapshotTask = documentReference.get();
        documentSnapshotTask.addOnSuccessListener(result->
        {
            this.sandwichApplicationDataBase = result.toObject(SandwichApplicationDataBase.class);
            assert this.sandwichApplicationDataBase != null;

//            if(this.sandwichApplicationDataBase != null)
//            {
                this.ordering_name.setText(this.sandwichApplicationDataBase.getOrderName());
                this.num_pickles.setProgress(this.sandwichApplicationDataBase.getNumPickles());
                this.hummus_checkbox.setChecked(this.sandwichApplicationDataBase.getBoolHummus());
                this.tahini_checkbox.setChecked(this.sandwichApplicationDataBase.getBoolTahini());
                this.order_specifications.setText(this.sandwichApplicationDataBase.getOrderSpecifications());
//            }
        });
        this.DBSandwich = documentReference.addSnapshotListener((v,e)->
        {
            boolean bool = (e == null && v!= null);
            if (bool)
            {
                String order_status = v.getString("order_status");
                if (order_status!= null)
                {
                    if (order_status.equals( "preparing"))
                    {
                        Intent prepare_intent = new Intent(this, OrderPreparingActivity.class);
                        this.startActivity(prepare_intent);
                        finish();
                    }
                    else if(order_status.equals( "ready"))
                    {
                        Intent ready_intent = new Intent(this, OrderReadyActivity.class);
                        this.startActivity(ready_intent);
                        finish();
                    }
                }
            }
        });

        save_order_button.setOnClickListener(v->
                {
                    documentReference.update("order_name", this.ordering_name.getText().toString());
                    documentReference.update("num_pickles", this.num_pickles.getProgress());
                    documentReference.update("put_hummus", this.hummus_checkbox.isChecked());
                    documentReference.update("put_tahini", this.tahini_checkbox.isChecked());
                    documentReference.update("order_specifications", this.order_specifications.getText().toString());
                    Toast toast = Toast.makeText(this, "saved data changes", Toast.LENGTH_LONG);
                    toast.show();

                });
        delete_order_button.setOnClickListener(v->
        {
            documentReference.delete();
            this.orderSandwichApplication.save_order("");
            Toast toast = Toast.makeText(this, "delete order", Toast.LENGTH_LONG);
            toast.show();
            Intent main_activity_intent = new Intent(this, MainActivity.class);
            this.startActivity(main_activity_intent);
        });
    }

        @Override
        protected void onDestroy() {

            super.onDestroy();
            DBSandwich.remove();
    }
}

