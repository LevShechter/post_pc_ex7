package huji.postpc.y2021.ex_7;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("huji.postpc.y2021.ex_7", appContext.getPackageName());
    }
    @Test
    public void appSavesDataOfBoolHummus()
    {
        SandwichApplicationDataBase sandwichApplicationDataBase = new SandwichApplicationDataBase("user1", 0, true, true, "none");
        String id = sandwichApplicationDataBase.order_id;
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FirebaseApp.initializeApp(context);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("orders").document(id).get().addOnSuccessListener(result->
        {
         assertEquals(true, result.get("put_hummus"));
        }).addOnFailureListener(documentSnapshot->
        {
            fail();
        });
    }


    @Test
    public void appSavesDataOfBoolTahini() {
        SandwichApplicationDataBase sandwichApplicationDataBase = new SandwichApplicationDataBase("user1", 0, true, true, "none");
        String id = sandwichApplicationDataBase.order_id;
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FirebaseApp.initializeApp(context);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("orders").document(id).get().addOnSuccessListener(result ->
        {
            assertEquals(true, result.get("put_tahini"));
        }).addOnFailureListener(documentSnapshot ->
        {
            fail();
        });
    }


        @Test
        public void appSavesDataOfNumPickles()
        {
            SandwichApplicationDataBase sandwichApplicationDataBase = new SandwichApplicationDataBase("user1", 0, true, true, "none");
            String id = sandwichApplicationDataBase.order_id;
            Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
            FirebaseApp.initializeApp(context);
            FirebaseFirestore firestore = FirebaseFirestore.getInstance();
            firestore.collection("orders").document(id).get().addOnSuccessListener(result->
            {
                assertEquals("0", result.get("num_pickles"));
            }).addOnFailureListener(documentSnapshot->
            {
                fail();
            });
        }

    @Test
    public void appSavesDataOfOrderName()
    {
        SandwichApplicationDataBase sandwichApplicationDataBase = new SandwichApplicationDataBase("user1", 0, true, true, "none");
        String id = sandwichApplicationDataBase.order_id;
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FirebaseApp.initializeApp(context);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("orders").document(id).get().addOnSuccessListener(result->
        {
            assertEquals("user1", result.get("order_name"));
        }).addOnFailureListener(documentSnapshot->
        {
            fail();
        });
    }

}