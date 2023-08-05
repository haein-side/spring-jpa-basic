package jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
public class ItemCategory {

    @Id @GeneratedValue
    @Column(name = "ITEM_CATEGORY_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
}
