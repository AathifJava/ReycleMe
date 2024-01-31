package com.nova.recycle.recycleme.domain.product;

import com.nova.recycle.recycleme.domain.product.category.Category;
import com.nova.recycle.recycleme.domain.product.time.Time;
import com.nova.recycle.recycleme.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "time_id")
    private Long timeId;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "time_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Time time;
}
