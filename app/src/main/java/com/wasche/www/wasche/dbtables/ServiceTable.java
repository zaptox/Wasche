package com.wasche.www.wasche.dbtables;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name="ServiceTable")
public class ServiceTable extends Model
{
    @Column(name="serviceId" ,unique = true)
    private int  serviceId;

    @Column(name="name" ,notNull = false)
    private String name;

    @Column(name="image" ,notNull = false)
    private String image;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ServiceTable{" +
                "serviceId=" + serviceId +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}

