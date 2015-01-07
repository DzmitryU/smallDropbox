package com.sprhib.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Павел on 03.01.15.
 */

@Entity
@Table(name="file")
public class FileMeta {

    public FileMeta() {
        this.id = UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Id
    private String id;

    private String name;
    private String size;
    private Date date;

    public String getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}
