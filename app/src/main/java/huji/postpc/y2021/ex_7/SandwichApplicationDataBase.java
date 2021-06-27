package huji.postpc.y2021.ex_7;

import java.io.Serializable;
import java.util.UUID;

public class SandwichApplicationDataBase implements Serializable {
    public String order_name;
    public String order_id;
    public String order_status;
    public int num_pickles;
    public boolean put_hummus;
    public boolean put_tahini;
    public String order_specifications;


    public SandwichApplicationDataBase()
    {}


    public SandwichApplicationDataBase(String order_name_string, int num_pickles, boolean put_hummus, boolean put_tahini, String order_specifications_string) {
        this.order_id = UUID.randomUUID().toString();
        this.order_name = order_name_string;
        this.num_pickles = num_pickles;
        this.put_hummus = put_hummus;
        this.put_tahini = put_tahini;
        this.order_specifications = order_specifications_string;
        this.order_status = "edit";
    }

    public String getId() {
        return null;
    }

    public String getOrderName() {
        return this.order_name;
    }

    public int getNumPickles() {
        return num_pickles;
    }

    public boolean getBoolHummus() {
        return put_hummus;
    }

    public boolean getBoolTahini() {
        return put_tahini;
    }

    public String getOrderSpecifications() {
        return order_specifications;
    }
}
