package br.com.charlesedu.demoajax.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "sales")
public class Sale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "sale_link", nullable = false)
    private String saleLink;

    @Column(name = "sale_site", nullable = false)
    private String site;

    @Column(name = "description")
    private String description;

    @Column(name = "image_link", nullable = false)
    private String imageLink;

    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    @Column(name = "sale_price", nullable = false)
    private BigDecimal price;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "register_date", nullable = false)
    private LocalDateTime registerDate;

    @ManyToOne
    @JoinColumn(name = "category_fk")
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSaleLink() {
        return saleLink;
    }

    public void setSaleLink(String saleLink) {
        this.saleLink = saleLink;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Sale [id=" + id + ", title=" + title + ", saleLink=" + saleLink + ", site=" + site + ", description="
                + description + ", imageLink=" + imageLink + ", price=" + price + ", likes=" + likes + ", registerDate="
                + registerDate + ", category=" + category + "]";
    }
}
