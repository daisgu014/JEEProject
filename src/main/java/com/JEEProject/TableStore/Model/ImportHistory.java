package com.JEEProject.TableStore.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.Timer;

@Entity
@Table(name = "import_history")
public class ImportHistory {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "time_import")
    private Date timeImport;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public ImportHistory(Integer userId, Date timeImport) {
        this.userId = userId;
        this.timeImport = timeImport;
    }

    public ImportHistory() {
    }

    public Date getTimeImport() {
        return timeImport;
    }

    public void setTimeImport(Date timeImport) {
        this.timeImport = timeImport;
    }
}
