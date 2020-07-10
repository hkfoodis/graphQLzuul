package org.zerock.shoppingcart.domain;

import java.io.Serializable;
import java.util.Date;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.leangen.graphql.annotations.GraphQLQuery;

@Entity
@Table(name = "PRODUCTS")
public class Product implements Serializable {
	
	private static final long serialVersionUID = -1000119078147252957L;
	
	@Id
	@GraphQLQuery(name = "code")
	@Column(name = "Code", length = 20, nullable = false)
	private String code;
	
	@GraphQLQuery(name = "name")
	@Column(name = "Name", length = 255, nullable = false)
    private String name;
	
	@GraphQLQuery(name = "price")
	@Column(name = "Price", nullable = false)
    private double price;
	
	@Lob
	@GraphQLQuery(name = "image")
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;
	
	@Temporal(TemporalType.TIMESTAMP)
	@GraphQLQuery(name = "Create_date")
    @Column(name = "Create_Date", nullable = false)
    private Date createDate;
	
	public Product() {
    }
	
	public String getCode() {
		return code;
	}
	 
	public void setCode(String code) {
	    this.code = code;
	}
	 
	public String getName() {
	    return name;
	}
	 
	public void setName(String name) {
	    this.name = name;
	}
	 
	public double getPrice() {
	    return price;
	}
	 
	public void setPrice(double price) {
	    this.price = price;
	}
	 
	public Date getCreateDate() {
	    return createDate;
	}
	 
	public void setCreateDate(Date createDate) {
	    this.createDate = createDate;
	}
	 
	public byte[] getImage() {
	    return image;
	}
	 
	public void setImage(byte[] image) {
	    this.image = image;
	}
}
