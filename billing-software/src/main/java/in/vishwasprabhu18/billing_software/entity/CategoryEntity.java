package in.vishwasprabhu18.billing_software.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "tbl_category")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = true)
    private String categoryId;

    @Column(updatable = true)
    private String name;

    private String description;
    private String bgColor;
    private String imgUrl;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}
