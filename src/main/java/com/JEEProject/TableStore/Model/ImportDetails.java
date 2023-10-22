package com.JEEProject.TableStore.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "import_details")

public class ImportDetails {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "import_id")
    private Integer importId;

}
