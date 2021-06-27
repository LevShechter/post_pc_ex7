package huji.postpc.y2021.ex_7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

import java.io.Serializable;

import javax.annotation.Nullable;

public class OrderPrepareNewActivity extends AppCompatActivity {
    EditText ordering_name;
    CheckBox hummus_checkbox;
    CheckBox tahini_checkbox;
    SeekBar pickles_seek_bar;
    EditText order_specifications;
    Button order_button;
    OrderSandwichApplication orderSandwichApplication;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        this.ordering_name = findViewById(R.id.ordering_name);
        this.hummus_checkbox = findViewById(R.id.checkbox_hummus);
        this.tahini_checkbox = findViewById(R.id.checkbox_tahini);
        this.pickles_seek_bar = findViewById(R.id.num_pickles);
        this.order_specifications = findViewById(R.id.specifications);
        this.order_button = findViewById(R.id.make_order_button);
        this.orderSandwichApplication = new OrderSandwichApplication(this);

       order_button.setOnClickListener(v->
       {
           String order_name_string = ordering_name.getText().toString();
           boolean put_hummus = hummus_checkbox.isChecked();
           boolean put_tahini = tahini_checkbox.isChecked();
           int num_pickles = pickles_seek_bar.getProgress();
           String order_specifications_string = order_specifications.getText().toString();
           SandwichApplicationDataBase sandwich_order = new SandwichApplicationDataBase(order_name_string, num_pickles, put_hummus, put_tahini, order_specifications_string);
           CollectionReference collectionReference = firestore.collection("orders");
           DocumentReference documentReference = collectionReference.document(sandwich_order.order_id);
           documentReference.set(sandwich_order);
           this.orderSandwichApplication.save_order(sandwich_order.order_id);
           Intent edit_order_intent = new Intent(this, OrderEditActivity.class);
           edit_order_intent.putExtra("text", sandwich_order);
           this.startActivity(edit_order_intent);
           finish();
       });
    }


}
