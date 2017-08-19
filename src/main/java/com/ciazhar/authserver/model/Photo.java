package com.ciazhar.authserver.model;



import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by ciazhar on 6/21/17.
 */

@Entity
public class Photo {

    @Id
    private String id;
    private String accountId;
    private Date createdDate;
    private String url;

    public Photo(){
        this.setCreatedDate(new Date());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
