package com.JEEProject.TableStore.Model;

import com.JEEProject.TableStore.Auth.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Timer;

@Entity
@Table(name = "import_history")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImportHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "time_import")
    @Temporal(TemporalType.DATE)
    private Date timeImport;
    public String getTimeImport(){
        return timeImport.toString();
    }

}
