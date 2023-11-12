package com.JEEProject.TableStore.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "import_details")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImportDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "import_id")
    private ImportHistory importHistory;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product productId;
    @Column(name = "qty_import")
    private Integer qty;

}
